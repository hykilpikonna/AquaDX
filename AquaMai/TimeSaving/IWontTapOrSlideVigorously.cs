using HarmonyLib;
using Monitor;

namespace AquaMai.TimeSaving;

public class IWontTapOrSlideVigorously
{
    [HarmonyPrefix]
    [HarmonyPatch(typeof(PlInformationMonitor), "IsPlayPlInfoEnd")]
    public static bool Patch(ref bool __result)
    {
        __result = true;
        return false;
    }
}
