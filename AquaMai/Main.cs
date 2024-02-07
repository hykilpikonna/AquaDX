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
            {
                MelonLogger.Msg("> Patching SkipWarningScreen");
                HarmonyLib.Harmony.CreateAndPatchAll(typeof(SkipWarningScreen));
            }
            
            if (AppConfig.UX.SinglePlayer)
            {
                MelonLogger.Msg("> Patching SinglePlayer");
                HarmonyLib.Harmony.CreateAndPatchAll(typeof(SinglePlayer));
            }
            
            MelonLogger.Msg("Loaded!");
        }
    }
}