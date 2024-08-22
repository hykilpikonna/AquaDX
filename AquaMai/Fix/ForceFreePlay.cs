using HarmonyLib;

namespace AquaMai.Fix;

public class ForceFreePlay
{
    [HarmonyPrefix]
    [HarmonyPatch(typeof(Manager.Credit), "IsFreePlay")]
    private static bool PreIsFreePlay(ref bool __result)
    {
        __result = true;
        return false;
    }
}
