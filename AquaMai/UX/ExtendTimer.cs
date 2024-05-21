using HarmonyLib;

namespace AquaMai.UX
{
    public class ExtendTimer
    {
        [HarmonyPrefix]
        [HarmonyPatch(typeof(TimerController), "PrepareTimer")]
        public static void PrePrepareTimer(ref int second)
        {
            second = 200;
        }
    }
}