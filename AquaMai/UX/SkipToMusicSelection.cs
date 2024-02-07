using HarmonyLib;
using Manager;
using Process;
using Process.Information;

namespace AquaMai.UX
{
    public class SkipToMusicSelection
    {
        /*
         * Highly experimental, may well break some stuff
         * Works by overriding the info screen (where it shows new events and stuff)
         * to directly exit to the music selection screen, skipping character and
         * event selection, among others
         */
        [HarmonyPrefix]
        [HarmonyPatch(typeof(InformationProcess), "OnUpdate")]
        public static bool OnUpdate(InformationProcess __instance, ProcessDataContainer ___container)
        {
            GameManager.SetMaxTrack();
            ___container.processManager.AddProcess(new MusicSelectProcess(___container), 50);
            ___container.processManager.ReleaseProcess(__instance);
            return false;
        }
    }
}
