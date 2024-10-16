using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using AquaMai.Fix;
using AquaMai.Helpers;
using HarmonyLib;
using Manager;
using Monitor;
using Process;
using UnityEngine;

namespace AquaMai.Utils;

public class PractiseMode
{
    public static double repeatStart = -1;
    public static double repeatEnd = -1;
    public static float speed = 1;
    private static CriAtomExPlayer player;
    private static MovieMaterialMai2 movie;

    public static void SetRepeatEnd(double time)
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

    public static void ClearRepeat()
    {
        repeatStart = -1;
        repeatEnd = -1;
    }

    public static void SetSpeed()
    {
        player.SetPitch((float)(1200 * Math.Log(speed, 2)));
        // player.SetDspTimeStretchRatio(1 / speed);
        player.UpdateAll();

        movie.player.SetSpeed(speed);
    }

    private static IEnumerator SetSpeedCoroutineInner()
    {
        yield return null;
        SetSpeed();
    }

    public static void SetSpeedCoroutine()
    {
        SharedInstances.GameMainObject.StartCoroutine(SetSpeedCoroutineInner());
    }

    public static void SpeedUp()
    {
        speed += .05f;
        if (speed > 2)
        {
            speed = 2;
        }

        SetSpeed();
    }

    public static void SpeedDown()
    {
        speed -= .05f;
        if (speed < 0.05)
        {
            speed = 0.05f;
        }

        SetSpeed();
    }

    public static void SpeedReset()
    {
        speed = 1;
        SetSpeed();
    }

    public static PractiseModeUI ui;

    [HarmonyPatch(typeof(GameProcess), "OnStart")]
    [HarmonyPostfix]
    public static void GameProcessPostStart()
    {
        repeatStart = -1;
        repeatEnd = -1;
        speed = 1;
        ui = null;
        SetSpeed();
    }

# if DEBUG
    [HarmonyPrefix]
    [HarmonyPatch(typeof(GenericProcess), "OnUpdate")]
    public static void OnGenericProcessUpdate(GenericMonitor[] ____monitors)
    {
        if (Input.GetKeyDown(KeyCode.F11))
        {
            ____monitors[0].gameObject.AddComponent<PractiseModeUI>();
        }
    }
# endif

    [HarmonyPatch(typeof(GameProcess), "OnUpdate")]
    [HarmonyPostfix]
    public static void GameProcessPostUpdate(GameProcess __instance, GameMonitor[] ____monitors)
    {
        if (InputManager.GetSystemInputPush(InputManager.SystemButtonSetting.ButtonTest) && ui is null)
        {
            ui = ____monitors[0].gameObject.AddComponent<PractiseModeUI>();
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
