using System.Linq;
using AquaMai.Helpers;
using HarmonyLib;
using MAI2.Util;
using Manager;
using Manager.MaiStudio;
using Manager.UserDatas;
using Monitor;
using Process;
using UnityEngine;
using UrGUI.GUIWindow;

namespace AquaMai.UX;

public class SelectionDetail
{
    private static Window window;
    public static MusicSelectProcess.MusicSelectData SelectData { get; private set; }
    public static int Difficulty { get; private set; }

    [HarmonyPostfix]
    [HarmonyPatch(typeof(MusicSelectMonitor), "UpdateRivalScore")]
    public static void ScrollUpdate(MusicSelectProcess ____musicSelect, MusicSelectMonitor __instance)
    {
        if (__instance != ____musicSelect.MonitorArray[0]) return;
        if (window != null)
        {
            window.Close();
        }

        if (____musicSelect.IsRandomIndex()) return;

        SelectData = ____musicSelect.GetMusic(0);
        if (SelectData == null) return;
        Difficulty = ____musicSelect.GetDifficulty(0);

        window = __instance.gameObject.AddComponent<Window>();
    }

    public class Window : MonoBehaviour
    {
        private GUIWindow window;

        private void Start()
        {
            window = GUIWindow.Begin($"ID: {SelectData.MusicData.name.id}", Screen.width / 2f - 100, Screen.height * 0.87f, 200, 50, 10, 22, 5, true, true, true);
            window.Label(MusicDirHelper.LookupPath(SelectData.MusicData.name.id).Split('/').Reverse().ToArray()[3]);
            window.Label(SelectData.MusicData.genreName?.str);
            window.Label(SelectData.MusicData.AddVersion?.str);
            window.Label($"{SelectData.MusicData.notesData[Difficulty]?.level}.{SelectData.MusicData.notesData[Difficulty]?.levelDecimal}");

            var rate = CalcB50(SelectData.MusicData);
            if (rate > 0)
            {
                window.Label($"SSS+ => DXRating += {rate}");
            }
        }

        private uint CalcB50(MusicData musicData)
        {
            var newRate = new UserRate(musicData.name.id, Difficulty, 1010000, (uint)musicData.version);
            var user = Singleton<UserDataManager>.Instance.GetUserData(0);
            var userLowRate = (newRate.OldFlag ? user.RatingList.RatingList : user.RatingList.NewRatingList).Last();

            if (newRate.SingleRate > userLowRate.SingleRate)
            {
                return newRate.SingleRate - userLowRate.SingleRate;
            }

            return 0;
        }

        private void OnGUI()
        {
            window.Draw();
        }

        public void Close()
        {
            Destroy(this);
        }
    }
}
