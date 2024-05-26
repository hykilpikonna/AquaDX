using HarmonyLib;
using MAI2.Util;
using Manager;
using MelonLoader;
using Monitor.Common;
using Monitor.Entry;
using Monitor.Entry.Parts.Screens;
using UnityEngine;
using Type = System.Type;

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
        public static bool IsPointInPolygon(Vector2[] polygon, ref Vector2 point, MeshButton __instance, ref bool __result)
        {
            __result = RectTransformUtility.RectangleContainsScreenPoint(__instance.GetComponent<RectTransform>(), point, Camera.main);
            return false;
        }

        [HarmonyPostfix]
        [HarmonyPatch(typeof(EntryMonitor), "DecideEntry")]
        public static void PostDecideEntry(EntryMonitor __instance)
        {
            MelonLogger.Msg("Confirm Entry");
            TimeManager.MarkGameStartTime();
            Singleton<EventManager>.Instance.UpdateEvent();
            Singleton<ScoreRankingManager>.Instance.UpdateData();
            __instance.Process.CreateDownloadProcess();
            __instance.ProcessManager.SendMessage(new Message(ProcessType.CommonProcess, 30001));
            __instance.ProcessManager.SendMessage(new Message(ProcessType.CommonProcess, 40000, 0, OperationInformationController.InformationType.Hide));
            __instance.Process.SetNextProcess();
        }

        // To prevent the "長押受付終了" overlay from appearing
        [HarmonyPrefix]
        [HarmonyPatch(typeof(WaitPartner), "Open")]
        public static bool WaitPartnerPreOpen()
        {
            return false;
        }
    }
}