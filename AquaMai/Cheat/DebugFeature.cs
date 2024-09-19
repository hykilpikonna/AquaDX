using System;
using HarmonyLib;
using Manager;
using Process;
using UnityEngine;

namespace AquaMai.Cheat;

public class DebugFeature
{
# if SDGA145
    private static bool isPause = false;

    [HarmonyPostfix]
    [HarmonyPatch(typeof(GameProcess), "OnUpdate")]
    public static void PostGameProcessUpdate(GameProcess __instance, byte ____sequence, MovieController ____gameMovie)
    {
        if (____sequence != 4) return;
        // GameSequence.Play
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
    }
# endif
}
