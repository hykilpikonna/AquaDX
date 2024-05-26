using HarmonyLib;
using Process.Information;

namespace AquaMai.UX
{
    public class SkipEventInfo
    {
        [HarmonyPostfix]
        [HarmonyPatch(typeof(InformationProcess), "OnStart")]
        public static void InformationProcessPreStart(ref uint ____state)
        {
            ____state = 3;
        }
    }
}