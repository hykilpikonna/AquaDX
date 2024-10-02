using HarmonyLib;
using UnityEngine;

namespace AquaMai.UX;

public class HideMask
{
    [HarmonyPrefix]
    [HarmonyPatch(typeof(Main.GameMain), "LateInitialize", typeof(MonoBehaviour), typeof(Transform), typeof(Transform))]
    public static void LateInitialize(MonoBehaviour gameMainObject)
    {
        GameObject.Find("Mask").SetActive(false);
    }
}
