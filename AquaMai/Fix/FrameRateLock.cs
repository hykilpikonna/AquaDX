using UnityEngine;

namespace AquaMai.Fix;

public class FrameRateLock
{
    public static void DoCustomPatch(HarmonyLib.Harmony h)
    {
        Application.targetFrameRate = 60;
        QualitySettings.vSyncCount = 0;
    }
}
