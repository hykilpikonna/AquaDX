using AMDaemon;
using AMDaemon.Allnet;
using HarmonyLib;
using Manager;
using Manager.Operation;
using UnityEngine;

namespace AquaMai.Fix;

public class BasicFix
{
    [HarmonyPrefix]
    [HarmonyPatch(typeof(MAI2System.IniFile), "clear")]
    private static bool PreIniFileClear()
    {
        return false;
    }

    [HarmonyPrefix]
    [HarmonyPatch(typeof(LanInstall), "IsServer", MethodType.Getter)]
    private static bool PreIsServer(ref bool __result)
    {
        __result = true;
        return false;
    }

    [HarmonyPrefix]
    [HarmonyPatch(typeof(AMDaemon.Network), "IsLanAvailable", MethodType.Getter)]
    private static bool PreIsLanAvailable(ref bool __result)
    {
        __result = false;
        return false;
    }

    [HarmonyPostfix]
    [HarmonyPatch(typeof(OperationManager), "CheckAuth_Proc")]
    private static void PostCheckAuthProc(ref OperationData ____operationData)
    {
        if (Auth.GameServerUri.StartsWith("http://") || Auth.GameServerUri.StartsWith("https://"))
        {
            ____operationData.ServerUri = Auth.GameServerUri;
        }
    }

    [HarmonyPrefix]
    [HarmonyPatch(typeof(Manager.Credit), "IsFreePlay")]
    private static bool PreIsFreePlay(ref bool __result)
    {
        __result = true;
        return false;
    }

    [HarmonyPrefix]
    [HarmonyPatch(typeof(DebugInput), "GetKey")]
    private static bool GetKey(ref bool __result, KeyCode name)
    {
        __result = UnityEngine.Input.GetKey(name);
        return false;
    }

    [HarmonyPrefix]
    [HarmonyPatch(typeof(DebugInput), "GetKeyDown")]
    private static bool GetKeyDown(ref bool __result, KeyCode name)
    {
        __result = UnityEngine.Input.GetKeyDown(name);
        return false;
    }

    [HarmonyPrefix]
    [HarmonyPatch(typeof(DebugInput), "GetMouseButton")]
    private static bool GetMouseButton(ref bool __result, int button)
    {
        __result = UnityEngine.Input.GetMouseButton(button);
        return false;
    }

    [HarmonyPrefix]
    [HarmonyPatch(typeof(DebugInput), "GetMouseButtonDown")]
    private static bool GetMouseButtonDown(ref bool __result, int button)
    {
        __result = UnityEngine.Input.GetMouseButtonDown(button);
        return false;
    }
}
