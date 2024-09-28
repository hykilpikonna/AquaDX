using System;
using System.Collections.Generic;
using System.Diagnostics;
using AquaMai.Fix;
using AquaMai.Helpers;
using HarmonyLib;
using Manager;
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
    private static float speed = 1;
    private static CriAtomExPlayer player;
    private static MovieMaterialMai2 movie;

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

    private static void SetSpeed()
    {
        player.SetPitch((float)(1200 * Math.Log(speed, 2)));
        // player.SetDspTimeStretchRatio(1 / speed);
        player.UpdateAll();

        movie.player.SetSpeed(speed);
    }

    private static void SpeedUp()
    {
        speed += .05f;
        if (speed > 2)
        {
            speed = 2;
        }

        SetSpeed();
    }

    private static void SpeedDown()
    {
        speed -= .05f;
        if (speed < 0.5)
        {
            speed = 0.5f;
        }

        SetSpeed();
    }

    private static void SpeedReset()
    {
        speed = 1;
        SetSpeed();
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
            window.SameLine(1, 1, 1);
            window.Button("加速", SpeedUp);
            window.Button("减速", SpeedDown);
            window.Button("速度重置", SpeedReset);
        }

        private void OnGUI()
        {
            window?.Draw();
        }
    }

    private static DebugWindow debugWin;

    [HarmonyPatch(typeof(GameProcess), "OnStart")]
    [HarmonyPostfix]
    public static void GameProcessPostStart()
    {
        repeatStart = -1;
        repeatEnd = -1;
        speed = 1;
        SetSpeed();
    }

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

    [HarmonyPatch(typeof(NotesManager), "UpdateTimer")]
    [HarmonyPostfix]
    public static void NotesManagerPostUpdateTimer(bool ____isPlaying, Stopwatch ____stopwatch, ref float ____curMSec, ref float ____curMSecPre, float ____msecStartGap)
    {
        var num = 0d;
        if (____isPlaying && ____stopwatch != null)
        {
            num = (double)____stopwatch.ElapsedTicks / Stopwatch.Frequency * 1000.0 * speed;
        }

        ____curMSecPre = ____curMSec;
        ____curMSec = (float)num + ____msecStartGap;
    }

    [HarmonyPatch(typeof(SoundCtrl), "Initialize")]
    [HarmonyPostfix]
    public static void SoundCtrlPostInitialize(SoundCtrl.InitParam param, Dictionary<int, object> ____players)
    {
        var wrapper = ____players[2];
        player = (CriAtomExPlayer)wrapper.GetType().GetField("Player").GetValue(wrapper);
        // var pool = new CriAtomExStandardVoicePool(1, 8, 96000, true, 2);
        // pool.AttachDspTimeStretch();
        // player.SetVoicePoolIdentifier(pool.identifier);

        // debug
        // var wrapper1 = ____players[7];
        // var player1 = (CriAtomExPlayer)wrapper1.GetType().GetField("Player").GetValue(wrapper1);
        // var pool = new CriAtomExStandardVoicePool(1, 8, 96000, true, 2);
        // pool.AttachDspTimeStretch();
        // player1.SetVoicePoolIdentifier(pool.identifier);
        // player1.SetDspTimeStretchRatio(2);
    }

    [HarmonyPatch(typeof(MovieController), "Awake")]
    [HarmonyPostfix]
    public static void MovieControllerPostAwake(MovieMaterialMai2 ____moviePlayers)
    {
        movie = ____moviePlayers;
    }
}
