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
    private static readonly Window[] window = new Window[2];
    private static MusicSelectProcess.MusicSelectData SelectData { get; set; }
    private static readonly int[] difficulty = new int[2];

    [HarmonyPostfix]
    [HarmonyPatch(typeof(MusicSelectMonitor), "UpdateRivalScore")]
    public static void ScrollUpdate(MusicSelectProcess ____musicSelect, MusicSelectMonitor __instance)
    {
        int player;
        if (__instance == ____musicSelect.MonitorArray[0])
        {
            player = 0;
        }
        else if (__instance == ____musicSelect.MonitorArray[1])
        {
            player = 1;
        }
        else
        {
            return;
        }

        if (window[player] != null)
        {
            window[player].Close();
        }

        var userData = Singleton<UserDataManager>.Instance.GetUserData(player);
        if (!userData.IsEntry) return;

        if (____musicSelect.IsRandomIndex()) return;

        SelectData = ____musicSelect.GetMusic(0);
        if (SelectData == null) return;
        difficulty[player] = ____musicSelect.GetDifficulty(player);

        window[player] = player == 0 ? __instance.gameObject.AddComponent<P1Window>() : __instance.gameObject.AddComponent<P2Window>();
    }

    private class P1Window : Window
    {
        protected override int player => 0;
    }

    private class P2Window : Window
    {
        protected override int player => 1;
    }

    private abstract class Window : MonoBehaviour
    {
        private GUIWindow window;
        protected abstract int player { get; }

        public void Start()
        {
            var x = Screen.width / 2f - 100;
            if (!AquaMai.AppConfig.UX.SinglePlayer)
            {
                var halfPlayerWidth = Screen.height / 1920f * 1080 / 2;
                x += halfPlayerWidth * (player == 0 ? -1 : 1);
            }

            window = GUIWindow.Begin($"ID: {SelectData.MusicData.name.id}", x, Screen.height * 0.87f, 200, 50, 10, 22, 5, true, true, true);
            window.Label(MusicDirHelper.LookupPath(SelectData.MusicData.name.id).Split('/').Reverse().ToArray()[3]);
            if (SelectData.MusicData.genreName is not null) // SelectData.MusicData.genreName.str may not correct
                window.Label(Singleton<DataManager>.Instance.GetMusicGenre(SelectData.MusicData.genreName.id)?.genreName);
            if (SelectData.MusicData.AddVersion is not null)
                window.Label(Singleton<DataManager>.Instance.GetMusicVersion(SelectData.MusicData.AddVersion.id)?.genreName);
            var notesData = SelectData.MusicData.notesData[difficulty[player]];
            window.Label($"{notesData?.level}.{notesData?.levelDecimal}");

            var rate = CalcB50(SelectData.MusicData, difficulty[player]);
            if (rate > 0)
            {
                window.Label($"SSS+ => DXRating += {rate}");
            }
        }

        private uint CalcB50(MusicData musicData, int difficulty)
        {
            var newRate = new UserRate(musicData.name.id, difficulty, 1010000, (uint)musicData.version);
            var user = Singleton<UserDataManager>.Instance.GetUserData(player);
            var userLowRate = (newRate.OldFlag ? user.RatingList.RatingList : user.RatingList.NewRatingList).Last();

            if (newRate.SingleRate > userLowRate.SingleRate)
            {
                return newRate.SingleRate - userLowRate.SingleRate;
            }

            return 0;
        }

        private void OnGUI()
        {
            window?.Draw();
        }

        public void Close()
        {
            Destroy(this);
        }
    }
}
