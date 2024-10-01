using System;
using System.Globalization;
using System.IO;
using System.Reflection;
using System.Runtime.InteropServices;
using System.Threading;
using AquaMai.Fix;
using AquaMai.Helpers;
using AquaMai.Resources;
using AquaMai.Utils;
using AquaMai.UX;
using MelonLoader;
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
        public const string Version = "1.1.3";
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
            // Fixes
            Patch(typeof(FixCharaCrash));
            Patch(typeof(BasicFix));
            Patch(typeof(DisableReboot));
            Patch(typeof(ExtendNotesPool));
            Patch(typeof(FixCheckAuth));
            Patch(typeof(DebugFeature));
            // UX
            Patch(typeof(CustomVersionString));
            Patch(typeof(CustomPlaceName));
            Patch(typeof(RunCommandOnEvents));
            // Utils
            Patch(typeof(JudgeAdjust));
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
    }
}
