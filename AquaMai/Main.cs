using System;
using System.Globalization;
using System.IO;
using System.Reflection;
using System.Runtime.InteropServices;
using System.Threading;
using AquaMai.Fix;
using AquaMai.Helpers;
using AquaMai.MaimaiDX2077;
using AquaMai.Resources;
using AquaMai.Utils;
using AquaMai.UX;
using MelonLoader;
using Monitor;
using Tomlet;
using UnityEngine;

namespace AquaMai
{
    public static class BuildInfo
    {
        public const string Name = "AquaMai";
        public const string Description = "Mod for Sinmai";
        public const string Author = "Aza";
        public const string Company = null;
        public const string Version = "1.2.1";
        public const string DownloadLink = null;
    }

    public class AquaMai : MelonMod
    {
        public static Config AppConfig { get; private set; }
        private static bool _hasErrors;

        private void Patch(Type type)
        {
            MelonLogger.Msg($"> Patching {type}");
            try
            {
                HarmonyInstance.PatchAll(type);
                foreach (var nested in type.GetNestedTypes())
                {
                    Patch(nested);
                }

                var customMethod = type.GetMethod("DoCustomPatch", BindingFlags.Public | BindingFlags.Static);
                customMethod?.Invoke(null, [HarmonyInstance]);
            }
            catch (Exception e)
            {
                MelonLogger.Error($"Failed to patch {type}: {e}");
                _hasErrors = true;
            }
        }

        /**
         * Apply patches using reflection, based on the settings
         */
        private void ApplyPatches()
        {
            // Iterate over all properties of AppConfig
            foreach (var categoryProp in AppConfig.GetType().GetProperties())
            {
                // Get the value of the category property (e.g., UX, Cheat)
                var categoryValue = categoryProp.GetValue(AppConfig);
                if (categoryValue == null) continue;
                var categoryType = categoryValue.GetType();

                // Iterate over properties in the category (e.g., SkipWarningScreen, SinglePlayer)
                foreach (var settingProp in categoryType.GetProperties())
                {
                    // The property should be a boolean
                    if (settingProp.PropertyType != typeof(bool)) continue;

                    // Check if the boolean value is true
                    if (!(bool)settingProp.GetValue(categoryValue)) continue;

                    // Get the Type from the config directive name
                    var directiveType = Type.GetType($"AquaMai.{categoryProp.Name}.{settingProp.Name}");

                    // If the type is found, call the Patch method
                    if (directiveType != null)
                    {
                        Patch(directiveType);
                    }
                }
            }
        }

        [DllImport("kernel32.dll", SetLastError = true)]
        private static extern bool SetConsoleOutputCP(uint wCodePageID);

        private void WriteEmbeddedResourceToFile(string resource, string file)
        {
            using var s = MelonAssembly.Assembly.GetManifestResourceStream(resource);
            using var fs = File.Open(file, FileMode.Create);
            s.CopyTo(fs);
        }

        private static void InitLocale()
        {
            if (!string.IsNullOrEmpty(AppConfig.UX.Locale))
            {
                Locale.Culture = CultureInfo.GetCultureInfo(AppConfig.UX.Locale);
                return;
            }

            Locale.Culture = Application.systemLanguage switch
            {
                SystemLanguage.Chinese or SystemLanguage.ChineseSimplified or SystemLanguage.ChineseTraditional => CultureInfo.GetCultureInfo("zh"),
                SystemLanguage.English => CultureInfo.GetCultureInfo("en"),
                _ => CultureInfo.InvariantCulture
            };
        }

        public override void OnInitializeMelon()
        {
            // Prevent Chinese characters from being garbled
            SetConsoleOutputCP(65001);

            MelonLogger.Msg("Loading mod settings...");

            // Check if AquaMai.toml exists
            if (!File.Exists("AquaMai.toml"))
            {
                WriteEmbeddedResourceToFile("AquaMai.AquaMai.toml", "AquaMai.example.toml");
                WriteEmbeddedResourceToFile("AquaMai.AquaMai.zh.toml", "AquaMai.example.zh.toml");
                MelonLogger.Error("======================================!!!");
                MelonLogger.Error("AquaMai.toml not found! Please create it.");
                MelonLogger.Error("找不到配置文件 AquaMai.toml！请创建。");
                MelonLogger.Error("Example copied to AquaMai.example.toml");
                MelonLogger.Error("示例已复制到 AquaMai.example.zh.toml");
                MelonLogger.Error("=========================================");
                return;
            }

            // Read AquaMai.toml to load settings
            AppConfig = TomletMain.To<Config>(File.ReadAllText("AquaMai.toml"));

            // Migrate old settings
            AppConfig.UX.LoadAssetsPng = AppConfig.UX.LoadAssetsPng || AppConfig.UX.LoadJacketPng;
            AppConfig.UX.LoadJacketPng = false;

            // Init locale with patching C# runtime
            // https://stackoverflow.com/questions/1952638/single-assembly-multi-language-windows-forms-deployment-ilmerge-and-satellite-a
            Patch(typeof(I18nSingleAssemblyHook));
            InitLocale();

            // Fixes that does not have side effects
            // These don't need to be configurable

            // Helpers
            Patch(typeof(MessageHelper));
            Patch(typeof(MusicDirHelper));
            Patch(typeof(SharedInstances));
            Patch(typeof(GuiSizes));
            // Fixes
            Patch(typeof(FixCharaCrash));
            Patch(typeof(BasicFix));
            Patch(typeof(DisableReboot));
            if (GameInfo.GameVersion >= 23000)
                Patch(typeof(ExtendNotesPool));
            Patch(typeof(FixCheckAuth));
            Patch(typeof(DebugFeature));
            if (GameInfo.GameVersion >= 23000)
                Patch(typeof(FixConnSlide));
            Patch(typeof(FixSlideAutoPlay)); // Rename: SlideAutoPlayTweak -> FixSlideAutoPlay, 不过这个应该无副作用所以不需要改配置文件
            if (GameInfo.GameVersion >= 24000)
                Patch(typeof(FixLevelDisplay));
            // UX
            Patch(typeof(CustomVersionString));
            Patch(typeof(CustomPlaceName));
            Patch(typeof(RunCommandOnEvents));
            Patch(typeof(CustomLogo));
            // Utils
            Patch(typeof(JudgeAdjust));
            Patch(typeof(TouchPanelBaudRate));

            // New Features & Changes
            // 现在自定义皮肤相关的功能应该有 CustomSkin, JudgeDisplay4B, CustomTrackStartDiff
            // 后续应该还会接着做, 所以也许可以考虑把自定义皮肤相关的部分单独分一类 ?
            Patch(typeof(CustomSkins)); // Rename: CustomNoteSkin -> CustomSkins
            Patch(typeof(JudgeDisplay4B));
            Patch(typeof(CustomTrackStartDiff));
            
            Patch(typeof(RealisticRandomJudge)); // 本来是用来调试判定显示4B的, 觉得还挺有趣就单独做成功能了
            
            Patch(typeof(DisableTrackStartTabs)); // 从 TrackStartProcessTweak 里单独拆出来了
            
            // 以下三项拆分自 SlideJudgeTweak
            Patch(typeof(FanJudgeFlip));
            Patch(typeof(BreakSlideJudgeBlink));
            Patch(typeof(FixCircleSlideJudge)); // 这个我觉得算无副作用, 可以常开
            
            // 这是一项往 Sinmai 里加各种新 note 的企划, 目前只完成了可高度自定义形状的星星
            // 未来还会缓慢更新, 我建议单开一个功能分类
            // 注意需要往 UserLib 里放入 System.Numeric.dll
            Patch(typeof(CustomNoteTypePatch));
            
# if DEBUG
            Patch(typeof(LogNetworkErrors));
# endif

            // Apply patches based on the settings
            ApplyPatches();

            if (_hasErrors)
            {
                MelonLogger.Warning("========================================================================!!!\n" + Locale.LoadError);
                MelonLogger.Warning("===========================================================================");
            }

            MelonLogger.Msg(Locale.Loaded);
        }

        public override void OnGUI()
        {
            GuiSizes.SetupStyles();
            base.OnGUI();
        }
    }
}
