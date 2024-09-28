using AquaMai.Fix;
using AquaMai.Helpers;
using HarmonyLib;
using MelonLoader;
using Monitor;
using Process;
using UnityEngine;
using UrGUI.GUIWindow;

namespace AquaMai.Utils;

public class PractiseMode
{
    private static double repeatStart = -1;
    private static double repeatEnd = -1;

    private static void SetRepeatEnd(double time)
    {
        if (repeatStart == -1)
        {
            MessageHelper.ShowMessage("Please set repeat start time first");
            return;
        }

        if (time < repeatStart)
        {
            MessageHelper.ShowMessage("Repeat end time cannot be less than repeat start time");
            return;
        }

        repeatEnd = time;
    }

    private static void ClearRepeat()
    {
        repeatStart = -1;
        repeatEnd = -1;
    }

    private class DebugWindow : MonoBehaviour
    {
        private GUIWindow window;

        public void Start()
        {
            window = GUIWindow.Begin("练习模式 测试");
            window.Button("暂停", () => DebugFeature.Pause = !DebugFeature.Pause);
            window.SameLine();
            window.Button("向前", () => DebugFeature.Seek(-1000));
            window.Button("向后", () => DebugFeature.Seek(1000));
            window.SameLine(1, 1, 1);
            window.Button("循环开始", () => repeatStart = DebugFeature.CurrentPlayMsec);
            window.Button("循环结束", () => SetRepeatEnd(DebugFeature.CurrentPlayMsec));
            window.Button("循环解除", ClearRepeat);
        }

        private void OnGUI()
        {
            window?.Draw();
        }
    }

    private static DebugWindow debugWin;

    [HarmonyPatch(typeof(GameProcess), "OnUpdate")]
    [HarmonyPostfix]
    public static void GameProcessPostUpdate(GameProcess __instance, GameMonitor[] ____monitors)
    {
        if (Input.GetKeyDown(KeyCode.F12))
        {
            if (debugWin is null)
            {
                debugWin = ____monitors[0].gameObject.AddComponent<DebugWindow>();
            }
            else
            {
                MelonLogger.Msg("[PractiseMode] 调试窗口作用中");
            }
        }

        if (repeatStart >= 0 && repeatEnd >= 0)
        {
            if (DebugFeature.CurrentPlayMsec >= repeatEnd)
            {
                DebugFeature.CurrentPlayMsec = repeatStart;
            }
        }
    }
}
