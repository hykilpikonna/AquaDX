using System;
using HarmonyLib;
using UnityEngine;

namespace AquaMai.UX
{
    // Hides the 2p (right hand side) UI.
    // Note: this is not my original work. I simply interpreted the code and rewrote it as a mod.
    public class SinglePlayer
    {
        [HarmonyPrefix]
        [HarmonyPatch(typeof(Main.GameMain), "LateInitialize", new Type[] { typeof(MonoBehaviour), typeof(Transform), typeof(Transform) })]
        public static bool LateInitialize(MonoBehaviour gameMainObject, ref Transform left, ref Transform right)
        {
            left.transform.position = Vector3.zero;
            right.localScale = Vector3.zero;
            GameObject.Find("Mask").SetActive(false);

            return true;
        }

        [HarmonyPrefix]
        [HarmonyPatch(typeof(MeshButton), "IsPointInPolygon", new Type[] { typeof(Vector2[]), typeof(Vector2) })]
        public static bool IsPointInPolygon(Vector2[] polygon, ref Vector2 point)
        {
            var screenWidth = Screen.width;
            point = new Vector2(point.x - (screenWidth / 2), point.y);

            return true;
        }
    }
}
