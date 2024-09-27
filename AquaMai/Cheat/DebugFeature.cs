using System;
using HarmonyLib;
using MAI2.Util;
using Manager;
using Monitor;
using Process;
using UnityEngine;

namespace AquaMai.Cheat;

public class DebugFeature
{
# if SDGA145
    private static bool isPause;
    private static double timer;

    [HarmonyPostfix]
    [HarmonyPatch(typeof(GameProcess), "OnUpdate")]
    public static void PostGameProcessUpdate(GameProcess __instance, byte ____sequence, MovieController ____gameMovie, GameMonitor[] ____monitors)
    {
        if (____sequence != 4) return;
        // GameSequence.Play
        if (!isPause)
        {
            timer += GameManager.GetGameMSecAddD();
        }
        if (Input.GetKeyDown(KeyCode.Home))
        {
            GameManager.AutoPlay = (GameManager.AutoPlayMode)((int)(GameManager.AutoPlay + 1) % Enum.GetNames(typeof(GameManager.AutoPlayMode)).Length);
        }
        else if (Input.GetKeyDown(KeyCode.Return))
        {
            isPause = !isPause;
            SoundManager.PauseMusic(isPause);
            ____gameMovie.Pause(isPause);
            NotesManager.Pause(isPause);
        }
        else if (DebugInput.GetKeyDown(KeyCode.LeftArrow) || DebugInput.GetKeyDown(KeyCode.RightArrow))
        {
            var num23 = 0;
            if (DebugInput.GetKeyDown(KeyCode.LeftArrow))
            {
                num23 = -1000;
            }
            if (DebugInput.GetKeyDown(KeyCode.RightArrow))
            {
                num23 = 1000;
            }
            int addMsec = ((!DebugInput.GetKey(KeyCode.LeftShift) && !DebugInput.GetKey(KeyCode.RightShift)) ? ((!DebugInput.GetKey(KeyCode.LeftControl) && !DebugInput.GetKey(KeyCode.RightControl)) ? num23 : (num23 * 10)) : (num23 * 5));
            Singleton<GamePlayManager>.Instance.Initialize();
            DebugTimeSkip(addMsec);
        }

        return;


        void DebugTimeSkip(int addMsec)
        {
            ____gameMovie.Pause(pauseFlag: true);
            NotesManager.Pause(true);
            if (addMsec >= 0)
            {
                timer += addMsec;
            }
            else
            {
                timer = timer + addMsec >= 0.0 ? timer + addMsec : 0.0;
            }
            ____gameMovie.SetSeekFrame(timer);
            SoundManager.SeekMusic((int)timer);
            for (int i = 0; i < ____monitors.Length; i++)
            {
                ____monitors[i].Seek((int)timer);
            }
            // magic number, dont know why
            NotesManager.StartPlay((int)timer + 91);
            NotesManager.Pause(isPause);
            if (!isPause)
            {
                SoundManager.PauseMusic(pause: false);
                ____gameMovie.Pause(pauseFlag: false);
            }
            else
            {
                ____gameMovie.Pause(pauseFlag: true);
            }
            __instance.UpdateNotes();
        }
    }
# endif
}
