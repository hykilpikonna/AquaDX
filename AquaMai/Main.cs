using System;
using AquaMai.Cheat;
using AquaMai.Fix;
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
        public const string Version = "1.0.0";
        public const string DownloadLink = null;
    }

    public class AquaMai : MelonMod
    {
        public static Config AppConfig { get; private set; }

        private void Patch(Type type)
        {
            MelonLogger.Msg($"> Patching {type}");
            HarmonyLib.Harmony.CreateAndPatchAll(type);
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

            if (AppConfig.UX.SkipWarningScreen)
                Patch(typeof(SkipWarningScreen));

            if (AppConfig.UX.SinglePlayer)
                Patch(typeof(SinglePlayer));

            if (AppConfig.Cheat.TicketUnlock)
                Patch(typeof(TicketUnlock));

            if (AppConfig.UX.SkipToMusicSelection)
            {
                Patch(typeof(SkipToMusicSelection));
            }

            // Fixes that does not have side effects
            // These don't need to be configurable
            Patch(typeof(FixCharaCrash));

            MelonLogger.Msg("Loaded!");
        }
    }
}
