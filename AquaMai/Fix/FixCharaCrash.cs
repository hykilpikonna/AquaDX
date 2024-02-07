using HarmonyLib;
using Process;
using Util;

namespace AquaMai.Fix
{
    /**
     * Fix character selection crashing because get map color returns null
     */
    public class FixCharaCrash
    {
        // Check if the return is null. If it is, make up a color
        [HarmonyPostfix]
        [HarmonyPatch(typeof(CharacterSelectProces), "GetMapColorData")]
        public static void GetMapColorData(ref CharacterSelectProces __instance, ref CharacterMapColorData __result)
        {
            if (__result != null) return;
            
            // 1 is a color that definitely exists
            if (MapMaster.GetSlotData(1) == null)
            {
                MapMaster.GetSlotData(1).Load();
            }
            __result = MapMaster.GetSlotData(1);
        }
        
    }
}