using System;
using AquaMai.Fix;
using AquaMai.Helpers;
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
        public const string Version = "1.0.5";
        public const string DownloadLink = null;
    }

    public class AquaMai : MelonMod
    {
        public static Config AppConfig { get; private set; }

        private void Patch(Type type)
        {
            MelonLogger.Msg($"> Patching {type}");
            HarmonyInstance.PatchAll(type);
            foreach (var nested in type.GetNestedTypes())
            {
                Patch(nested);
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
                    else MelonLogger.Error($"Type not found for {categoryProp.Name}.{settingProp.Name}");
                }
            }
        }

        public override void OnInitializeMelon()
        {
            MelonLogger.Msg("Loading mod settings...");

            // Check if AquaMai.toml exists
            if (!System.IO.File.Exists("AquaMai.toml"))
            {
                MelonLogger.Error("AquaMai.toml not found! Please create it.");
                return;
            }

            // Read AquaMai.toml to load settings
            AppConfig = TomletMain.To<Config>(System.IO.File.ReadAllText("AquaMai.toml"));

            // Migrate old settings
            AppConfig.UX.LoadAssetsPng = AppConfig.UX.LoadAssetsPng || AppConfig.UX.LoadJacketPng;
            AppConfig.UX.LoadJacketPng = false;

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
            // UX
            Patch(typeof(CustomVersionString));
            Patch(typeof(CustomPlaceName));
            Patch(typeof(RunCommandOnEvents));

            // Apply patches based on the settings
            ApplyPatches();

            MelonLogger.Msg("Loaded!");
        }
    }
}
