using HarmonyLib;
using MelonLoader;
using Monitor.Information;
using UnityEngine.Assertions;

namespace AquaMai.UX
{
    public class SkipEventInfo
    {
        // public override void Initialize(int monIndex, bool active)
        // Postfix: Set this.state to DispState.End
        [HarmonyPostfix]
        [HarmonyPatch(typeof(InformationMonitor), "Initialize", new []{typeof(int), typeof(bool)})]
        public static void Initialize(InformationMonitor __instance, int monIndex, bool active)
        {
            // State is private, cannot access directly
            // __instance.state = InformationMonitor.DispState.End;
            
            // Use reflection to set the state
            // var stateField = typeof(InformationMonitor).GetField("state", System.Reflection.BindingFlags.NonPublic | System.Reflection.BindingFlags.Instance);
            // if (stateField == null)
            // {
            //     MelonLogger.Msg("Failed to get state field");
            //     return;
            // }
            // stateField.SetValue(__instance, InformationMonitor.DispState.End);
            
            // Use reflection to set _dispTime to 0
            var dispTimeField = typeof(InformationMonitor).GetField("_dispTime", System.Reflection.BindingFlags.NonPublic | System.Reflection.BindingFlags.Instance);
            if (dispTimeField == null)
            {
                MelonLogger.Msg("Failed to get _dispTime field");
                return;
            }
            dispTimeField.SetValue(__instance, 0);
        }
    }
}