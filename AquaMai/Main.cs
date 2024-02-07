using AquaMai.UX;
using MelonLoader;

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
            MelonLogger.Msg("OnApplicationStart");
            
            // Read AquaMai.yaml to load settings
            var yaml = new YamlDotNet.Serialization.Deserializer();
            AppConfig = yaml.Deserialize<Config>(System.IO.File.ReadAllText("AquaMai.yaml"));
            
            if (AppConfig.UX.SkipWarningScreen)
            {
                MelonLogger.Msg("Patching CutsceneSkipping");
                HarmonyLib.Harmony.CreateAndPatchAll(typeof(SkipWarningScreen));
            }
        }
    }
}