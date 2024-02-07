using MelonLoader;
using HarmonyLib;

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
        public override void OnInitializeMelon() 
        {
            MelonLogger.Msg("OnApplicationStart");
            HarmonyLib.Harmony.CreateAndPatchAll(typeof(CutsceneSkipping));
            HarmonyLib.Harmony.CreateAndPatchAll(typeof(SinglePlayer));
        }
    }
}