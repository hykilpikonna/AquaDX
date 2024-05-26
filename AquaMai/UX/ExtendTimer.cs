using HarmonyLib;
using Manager;
using Monitor;
using Process;

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

        [HarmonyPrefix]
        [HarmonyPatch(typeof(PhotoEditProcess), "MainMenuUpdate")]
        public static void PhotoEditProcess(PhotoEditMonitor[] ____monitors, ProcessDataContainer ___container)
        {
            if (InputManager.GetButtonDown(0, InputManager.ButtonSetting.Button04))
            {
                ___container.processManager.DecrementTime(0, 200);
                SoundManager.PlaySE(Mai2.Mai2Cue.Cue.SE_SYS_SKIP, 0);
                ____monitors[0].SetButtonPressed(InputManager.ButtonSetting.Button04);
            }
        }
    }
}