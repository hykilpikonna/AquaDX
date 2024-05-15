using System;
using System.Collections.Generic;
using System.IO;
using HarmonyLib;
using Mai2.Mai2Cue;
using MAI2.Util;
using Manager;
using MelonLoader;

namespace AquaMai.UX
{
    public class RandomBgm
    {
        private static List<CriAtomExAcb> _acbs = new List<CriAtomExAcb>();
        private static Random _rng = new Random();
        private static CriAtomExAcb _originalAcb;

        [HarmonyPostfix]
        [HarmonyPatch(typeof(SoundManager), "Initialize")]
        public static void Init()
        {
            var files = Directory.EnumerateFiles(Path.Combine(Environment.CurrentDirectory, "LocalAssets", "Mai2Cue"));
            foreach (var file in files)
            {
                if (!file.EndsWith(".acb")) continue;
                _acbs.Add(CriAtomExAcb.LoadAcbFile(null, file, Path.ChangeExtension(file, "awb")));
            }

            MelonLogger.Msg($"Random BGM loaded {_acbs.Count} files");
        }

        [HarmonyPostfix]
        [HarmonyPatch(typeof(CriAtomExAcb), "LoadAcbFile")]
        public static void PostLoadAcbFile(string acbPath, CriAtomExAcb __result)
        {
            if (acbPath.EndsWith("SoundData/Mai2Cue.acb"))
                _originalAcb = __result;
        }

        [HarmonyPrefix]
        [HarmonyPatch(typeof(CriAtomExPlayer), "SetCue")]
        [HarmonyPatch(new[] { typeof(CriAtomExAcb), typeof(int) })]
        public static void PreSetCue(ref CriAtomExAcb acb, int id)
        {
            if (acb != _originalAcb) return;
            var cueIndex = (Cue)id;
            switch (cueIndex)
            {
                case Cue.BGM_ENTRY:
                case Cue.BGM_COLLECTION:
                case Cue.BGM_RESULT_CLEAR:
                case Cue.BGM_RESULT:
                    acb = _acbs[_rng.Next(_acbs.Count)];
                    MelonLogger.Msg($"Picked random BGM for {cueIndex}");
                    return;
                default:
                    return;
            }
        }
    }
}