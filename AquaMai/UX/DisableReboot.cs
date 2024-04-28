using HarmonyLib;
using Manager.Operation;

namespace AquaMai.UX
{
    public class DisableReboot
    {
        // IsAutoRebootNeeded
        [HarmonyPrefix]
        [HarmonyPatch(typeof(MaintenanceTimer), "IsAutoRebootNeeded")]
        public static bool IsAutoRebootNeeded(ref bool __result)
        {
            __result = false;
            return false;
        }
        
        // IsUnderServerMaintenance
        [HarmonyPrefix]
        [HarmonyPatch(typeof(MaintenanceTimer), "IsUnderServerMaintenance")]
        public static bool IsUnderServerMaintenance(ref bool __result)
        {
            __result = false;
            return false;
        }
        
        // RemainingMinutes
        [HarmonyPrefix]
        [HarmonyPatch(typeof(MaintenanceTimer), "RemainingMinutes")]
        public static bool RemainingMinutes(ref int __result)
        {
            __result = 600;
            return false;
        }
        
        // GetAutoRebootSec
        [HarmonyPrefix]
        [HarmonyPatch(typeof(MaintenanceTimer), "GetAutoRebootSec")]
        public static bool GetAutoRebootSec(ref int __result)
        {
            __result = 60 * 60 * 10;
            return false;
        }
        
        // GetServerMaintenanceSec
        [HarmonyPrefix]
        [HarmonyPatch(typeof(MaintenanceTimer), "GetServerMaintenanceSec")]
        public static bool GetServerMaintenanceSec(ref int __result)
        {
            __result = 60 * 60 * 10;
            return false;
        }
        
        // Execute
        [HarmonyPrefix]
        [HarmonyPatch(typeof(MaintenanceTimer), "Execute")]
        public static bool Execute(MaintenanceTimer __instance) => false;
        
        // UpdateTimes
        [HarmonyPrefix]
        [HarmonyPatch(typeof(MaintenanceTimer), "UpdateTimes")]
        public static bool UpdateTimes(MaintenanceTimer __instance) => false;
    }
}