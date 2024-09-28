using System;
using System.IO;
using System.Reflection;
using System.Runtime.InteropServices;
using AquaMai.Fix;
using AquaMai.Helpers;
using AquaMai.Utils;
using AquaMai.UX;
using MelonLoader;
using Tomlet;

namespace AquaMai
{
    public static class BuildInfo
    {
        public const string Name = "AquaMai";
        public const string Description = "Mod for Sinmai";
        public const string Author = "Aza";
        public const string Company = null;
        public const string Version = "1.1.0";
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

            // Fixes that does not have side effects
            // These don't need to be configurable

            WindowState.Execute();
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

            // Apply patches based on the settings
            ApplyPatches();

            if (_hasErrors)
            {
                MelonLogger.Warning("========================================================================!!!");
                MelonLogger.Warning("加载过程中检测到错误！");
                MelonLogger.Warning("- 请检查你是否安装了错误的 AquaMai 版本，比如在 SDGA 上使用了 SDEZ 的版本");
                MelonLogger.Warning("- 你是否正在使用魔改的 Assembly-CSharp.dll，这会导致函数不一致而无法找到需要修改的函数");
                MelonLogger.Warning("- 请检查是否有冲突的 Mod，或者开启了不兼容的选项");
                MelonLogger.Warning("===========================================================================");
                MelonLogger.Warning("Errors detected while loading!");
                MelonLogger.Warning("- Check if you have installed the wrong version of AquaMai, such as using SDEZ version on SDGA");
                MelonLogger.Warning("- Are you using a modified Assembly-CSharp.dll, which will cause inconsistent functions and cannot find the functions that need to be modified");
                MelonLogger.Warning("- Check for conflicting mods, or enabled incompatible options");
                MelonLogger.Warning("===========================================================================");
            }

            MelonLogger.Msg("Loaded!");
        }
    }
}
