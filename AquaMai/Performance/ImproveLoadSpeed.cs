using System.Diagnostics;
using HarmonyLib;
using MAI2.Util;
using Manager;
using Process;

namespace AquaMai.Performance
{
    public class ImproveLoadSpeed
    {
        [HarmonyPrefix]
        [HarmonyPatch(typeof(PowerOnProcess), "OnUpdate")]
        public static bool PrePowerOnUpdate(PowerOnProcess __instance)
        {
            var traverse = Traverse.Create(__instance);
            var state = traverse.Field("_state").GetValue<byte>();
            switch (state)
            {
                case 3:
                    traverse.Field("_state").SetValue((byte)4);
                    break;
                case 5:
                case 6:
                case 7:
                    traverse.Field("_state").SetValue((byte)8);
                    break;
                case 9:
                    traverse.Field("_state").SetValue((byte)10);
                    break;
            }

            return true;
        }
        
        [HarmonyPrefix]
        [HarmonyPatch(typeof(StartupProcess), "OnUpdate")]
        public static bool PreStartupUpdate(StartupProcess __instance)
        {
            var traverse = Traverse.Create(__instance);
            var state = traverse.Field("_state").GetValue<byte>();
            switch (state)
            {
                case 0:
                    traverse.Field("_state").SetValue((byte)1);
                    break;
                case 2:
                    // AimeReader maybe typeof AimeReaderManager or ChimeReaderManager, must build with correct Assembly-CSharp.dll in Libs folder
                    if(SingletonStateMachine<AmManager, AmManager.EState>.Instance.AimeReader.GetType().FullName == "Manager.AimeReaderManager")
                        traverse.Field("_state").SetValue((byte)3);
                    break;
                case 4:
                    traverse.Field("_state").SetValue((byte)5);
                    break;
                case 8:
                    var timer = traverse.Field("timer").GetValue<Stopwatch>();
                    Traverse.Create(timer).Field("elapsed").SetValue(2 * 10000000L);
                    break;
            }

            return true;
        }
    }
}