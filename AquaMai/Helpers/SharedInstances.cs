using HarmonyLib;
using Process;

namespace AquaMai.Helpers;

public class SharedInstances
{
    public static ProcessDataContainer ProcessDataContainer { get; private set; }

    [HarmonyPrefix]
    [HarmonyPatch(typeof(ProcessDataContainer), MethodType.Constructor)]
    public static void OnCreateProcessDataContainer(ProcessDataContainer __instance)
    {
        ProcessDataContainer = __instance;
    }
}
