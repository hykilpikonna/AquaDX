using System;
using System.Collections.Generic;
using System.Linq;
using DB;
using HarmonyLib;
using MAI2.Util;
using MAI2System;
using Manager;
using Manager.MaiStudio;
using Manager.UserDatas;
using MelonLoader;
using Monitor.Entry.Parts.Screens;
using Net.Packet;
using Net.Packet.Helper;
using Net.Packet.Mai2;
using Process;
using Process.UserDataNet.State.UserDataULState;

namespace AquaMai.UX
{
    public class ImmediateSave
    {
        [HarmonyPrefix]
        [HarmonyPatch(typeof(StateULUserAime), "RequestUploadUserPlayLogData")]
        public static bool PreRequestUploadUserPlayLogData(StateULUserAime __instance)
        {
            Traverse.Create(__instance).Method("RequestUploadUserPortraitData").GetValue();
            return false;
        }

        [HarmonyPostfix]
        [HarmonyPatch(typeof(ResultProcess), "OnStart")]
        public static void ResultProcessOnStart()
        {
            for (int i = 0; i < 2; i++)
            {
                var userData = Singleton<UserDataManager>.Instance.GetUserData(i);
                if(!userData.IsEntry) continue;
                if(userData.IsGuest()) continue;

                SaveDataFix(userData);
# if SDGA145
                PacketHelper.StartPacket(new PacketUploadUserPlaylog(i, userData, (int)GameManager.MusicTrackNumber - 1,
# else
                var accessToken = Singleton<OperationManager>.Instance.GetAccessToken(i);
                PacketHelper.StartPacket(new PacketUploadUserPlaylog(i, userData, (int)GameManager.MusicTrackNumber - 1, accessToken,
# endif
                    delegate { MelonLogger.Msg("Playlog saved"); },
                    delegate(PacketStatus err)
                    {
                        SoundManager.PlaySE(Mai2.Mai2Cue.Cue.SE_ENTRY_AIME_ERROR, i);
                        MelonLogger.Error("Playlog save error");
                        MelonLogger.Error(err);
                    }));
# if SDGA145
                PacketHelper.StartPacket(new PacketUpsertUserAll(i, userData, delegate(int code)
# else
                PacketHelper.StartPacket(new PacketUpsertUserAll(i, userData, accessToken, delegate(int code)
# endif
                {
                    if (code == 1)
                    {
                        MelonLogger.Msg("UserAll saved");
                    }
                    else
                    {
                        SoundManager.PlaySE(Mai2.Mai2Cue.Cue.SE_ENTRY_AIME_ERROR, i);
                        MelonLogger.Error("UserAll upsert error");
                        MelonLogger.Error(code);
                    }
                }, delegate(PacketStatus err)
                {
                    SoundManager.PlaySE(Mai2.Mai2Cue.Cue.SE_ENTRY_AIME_ERROR, i);
                    MelonLogger.Error("UserAll upsert error");
                    MelonLogger.Error(err);
                }));
            }
        }


        private static void SaveDataFix(UserData userData)
        {
            UserDetail detail = userData.Detail;
            _ = userData.ScoreList;
            userData.AddPlayCount();
            detail.EventWatchedDate = TimeManager.GetDateString(TimeManager.PlayBaseTime);
            userData.CalcTotalValue();
            float num = 0f;
            try
            {
                if (userData.RatingList.RatingList.Any())
                {
                    num = userData.RatingList.RatingList.Last().SingleRate;
                }
            }
            catch
            {
            }

            num = (float)Math.Ceiling((double)((num + 1f) / GameManager.TheoryRateBorderNum) * 10.0);
            float num2 = 0f;
            try
            {
                if (userData.RatingList.NextRatingList.Any())
                {
                    num2 = userData.RatingList.NewRatingList.Last().SingleRate;
                }
            }
            catch
            {
            }

            num2 = (float)Math.Ceiling((double)((num2 + 1f) / GameManager.TheoryRateBorderNum) * 10.0);
            string logDateString = TimeManager.GetLogDateString(TimeManager.PlayBaseTime);
            string timeJp = (string.IsNullOrEmpty(userData.Detail.DailyBonusDate) ? TimeManager.GetLogDateString(0L) : userData.Detail.DailyBonusDate);
            if (userData.IsEntry && userData.Detail.IsNetMember >= 2 && !GameManager.IsEventMode && TimeManager.GetUnixTime(logDateString) > TimeManager.GetUnixTime(timeJp) && Singleton<UserDataManager>.Instance.IsSingleUser() && !GameManager.IsFreedomMode && !GameManager.IsCourseMode && !DoneEntry.IsWeekdayBonus(userData))
            {
                userData.Detail.DailyBonusDate = logDateString;
            }

            List<UserRate> list = new List<UserRate>();
            List<UserRate> list2 = new List<UserRate>();
            List<UserScore>[] scoreList = userData.ScoreList;
            List<UserRate> ratingList = userData.RatingList.RatingList;
            List<UserRate> newRatingList = userData.RatingList.NewRatingList;
            int achive = RatingTableID.Rate_22.GetAchive();
            for (int j = 0; j < scoreList.Length; j++)
            {
                if (scoreList[j] == null)
                {
                    continue;
                }

                foreach (UserScore item2 in scoreList[j])
                {
                    if (achive <= item2.achivement)
                    {
                        continue;
                    }

                    MusicData music = Singleton<DataManager>.Instance.GetMusic(item2.id);
                    if (music == null)
                    {
                        continue;
                    }

                    UserRate item = new UserRate(item2.id, j, item2.achivement, (uint)music.version);
                    if (item.OldFlag)
                    {
                        if (num <= (float)item.Level && !ratingList.Contains(item))
                        {
                            list.Add(item);
                        }
                    }
                    else if (num2 <= (float)item.Level && !newRatingList.Contains(item))
                    {
                        list2.Add(item);
                    }
                }
            }

            list.Sort();
            list.Reverse();
            if (list.Count > 10)
            {
                list.RemoveRange(10, list.Count - 10);
            }

            userData.RatingList.NextRatingList = list;
            list2.Sort();
            list2.Reverse();
            if (list2.Count > 10)
            {
                list2.RemoveRange(10, list2.Count - 10);
            }

            userData.RatingList.NextNewRatingList = list2;

            userData.Detail.LastPlayCredit = 0;
            userData.Detail.LastPlayMode = 0;
            if (GameManager.IsFreedomMode)
            {
                userData.Detail.LastPlayMode = 1;
            }

            if (GameManager.IsCourseMode)
            {
                userData.Detail.LastPlayMode = 2;
            }

            userData.Detail.LastGameId = "SDEZ";
            userData.Detail.LastRomVersion = Singleton<SystemConfig>.Instance.config.romVersionInfo.versionNo.versionString;
            userData.Detail.LastDataVersion = Singleton<SystemConfig>.Instance.config.dataVersionInfo.versionNo.versionString;
        }
    }
}
