using System.Reflection;
using System;
using HarmonyLib;
using MAI2.Util;
using MAI2System;
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
        public static bool IsSDGB { get; set; } = SDGB_Check();

        [HarmonyPrefix]
        [HarmonyPatch(typeof(Main.GameMain), "LateInitialize", new Type[] { typeof(MonoBehaviour), typeof(Transform), typeof(Transform) })]
        public static void LateInitialize(MonoBehaviour gameMainObject, ref Transform left, ref Transform right)
        {
            left.transform.position = Vector3.zero;
            right.localScale = Vector3.zero;
            GameObject.Find("Mask").transform.position = new Vector3(540f, 0f, 0f);
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
            // SDGB 在扫码状态下会降低帧率以适应摄像头，直接SetNextProcess会导致摄像头未能正确关闭
            if (SinglePlayer.IsSDGB != true){ __instance.Process.SetNextProcess();}
        }

        // To prevent the "長押受付終了" overlay from appearing
        [HarmonyPrefix]
        [HarmonyPatch(typeof(WaitPartner), "Open")]
        public static bool WaitPartnerPreOpen()
        {
            if (SinglePlayer.IsSDGB == true) { return true; }
            return false;
        }

        public static bool SDGB_Check() {

            String gameID = "";
            // 检测游戏版本并判断是否为 SDGB , Thanks to Sinmai-Assist !!!
            try
            {
                gameID = (string)typeof(ConstParameter).GetField("GameIDStr",
                BindingFlags.Public | BindingFlags.Static | BindingFlags.FlattenHierarchy).GetValue(null);
                
            }
            catch (Exception e)
            {
                MelonLogger.Error("[SinglePlayer] Failed to get GameIDStr");
                MelonLogger.Error(e);
            }

            if (gameID.Equals("SDGB")) {
                MelonLogger.Warning("[SinglePlayer] Inject Game is SDGB!");
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
