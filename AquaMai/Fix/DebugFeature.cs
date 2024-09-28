using System;
using System.Reflection;
using HarmonyLib;
using MAI2.Util;
using Manager;
using MelonLoader;
using Monitor;
using Process;
using UnityEngine;

namespace AquaMai.Fix;

public class DebugFeature
{
    public static bool IsPolyfill { get; private set; }
    private static GameProcess _gameProcess;
    private static MovieController _gameMovie;
    private static GameMonitor[] _monitors;
    private static object _debugFeatureOriginal;

    [HarmonyPatch(typeof(GameProcess), "OnStart")]
    [HarmonyPostfix]
    public static void Init(GameProcess __instance, MovieController ____gameMovie, GameMonitor[] ____monitors)
    {
        _gameProcess = __instance;
        _gameMovie = ____gameMovie;
        _monitors = ____monitors;
    }

    public static void DoCustomPatch(HarmonyLib.Harmony h)
    {
        var original = typeof(GameProcess).GetField("debugFeature", BindingFlags.NonPublic | BindingFlags.Instance);
        if (original is null)
        {
            MelonLogger.Msg("  > [DebugFeature] Running Polyfill");
            IsPolyfill = true;
            h.PatchAll(typeof(PolyFill));
        }
        else
        {
            MelonLogger.Msg("  > [DebugFeature] Already included");
            h.PatchAll(typeof(GetOriginal));
        }
    }

    public static void SetPause(bool pause)
    {
        if (IsPolyfill)
        {
            PolyFill.isPause = pause;
        }
        else
        {
            var debugFeatureClass = typeof(GameProcess).GetNestedType("DebugFeature", BindingFlags.Instance | BindingFlags.NonPublic);
            debugFeatureClass?.GetField("_debugPause", BindingFlags.Instance | BindingFlags.NonPublic)?.SetValue(_debugFeatureOriginal, pause);
        }

        SoundManager.PauseMusic(pause);
        _gameMovie.Pause(pause);
        NotesManager.Pause(pause);
    }

    [HarmonyPatch]
    private static class GetOriginal
    {
        [HarmonyPatch(typeof(GameProcess), "OnStart")]
        [HarmonyPostfix]
        public static void Postfix(object ___debugFeature)
        {
            _debugFeatureOriginal = ___debugFeature;
        }
    }

    [HarmonyPatch]
    private static class PolyFill
    {
        public static bool isPause;
        public static double timer;

        public static void DebugTimeSkip(int addMsec)
        {
            _gameMovie.Pause(pauseFlag: true);
            NotesManager.Pause(true);
            if (addMsec >= 0)
            {
                timer += addMsec;
            }
            else
            {
                timer = timer + addMsec >= 0.0 ? timer + addMsec : 0.0;
            }

            _gameMovie.SetSeekFrame(timer);
            SoundManager.SeekMusic((int)timer);
            for (int i = 0; i < _monitors.Length; i++)
            {
                _monitors[i].Seek((int)timer);
            }

            // magic number, dont know why
            NotesManager.StartPlay((int)timer + 91);
            NotesManager.Pause(isPause);
            if (!isPause)
            {
                SoundManager.PauseMusic(pause: false);
                _gameMovie.Pause(pauseFlag: false);
            }
            else
            {
                _gameMovie.Pause(pauseFlag: true);
            }

            _gameProcess.UpdateNotes();
        }

        [HarmonyPatch(typeof(GameProcess), "OnUpdate")]
        [HarmonyPostfix]
        public static void Postfix(byte ____sequence)
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
                _gameMovie.Pause(isPause);
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
        }
    }
}
