using DB;
using HarmonyLib;
using MAI2.Util;
using Manager;
using Process;

namespace AquaMai.UX
{
    public class DemoMaster
    {
        [HarmonyPostfix]
        [HarmonyPatch(typeof(AdvDemoProcess), "OnStart")]
        public static void AdvDemoProcessPostStart()
        {
            var userOption = Singleton<GamePlayManager>.Instance.GetGameScore(0).UserOption;
            userOption.NoteSpeed = OptionNotespeedID.Speed7_0;
        }
        
        [HarmonyPrefix]
        [HarmonyPatch(typeof(GamePlayManager), "InitializeAdvertise")]
        public static void PreInitializeAdvertise()
        {
            GameManager.SelectDifficultyID[0] = 3;
            GameManager.SelectDifficultyID[1] = 3;
        }
    }
}