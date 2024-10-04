using HarmonyLib;
using Manager;
using Monitor.MusicSelect.ChainList;
using Net;
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

    [HarmonyPrefix]
    [HarmonyPatch(typeof(GameManager), "CalcSpecialNum")]
    private static bool CalcSpecialNum(ref int __result)
    {
        __result = 1024;
        return false;
    }

    [HarmonyPrefix]
    [HarmonyPatch(typeof(NetHttpClient), "CheckServerHash")]
    private static bool CheckServerHash(ref bool __result)
    {
        __result = true;
        return false;
    }
}
