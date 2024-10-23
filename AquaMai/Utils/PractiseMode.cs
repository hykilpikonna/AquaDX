using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Reflection;
using AquaMai.Fix;
using AquaMai.Helpers;
using DB;
using HarmonyLib;
using Manager;
using Monitor;
using Monitor.Game;
using Process;

namespace AquaMai.Utils
{
	// Token: 0x02000039 RID: 57
	public class PractiseMode
	{
		// Token: 0x060001AE RID: 430 RVA: 0x00092000 File Offset: 0x00092000
		public static void SetRepeatEnd(double time)
		{
			if (PractiseMode.repeatStart == -1.0)
			{
				MessageHelper.ShowMessage("Please set repeat start time first", WindowSizeID.Middle);
				return;
			}
			if (time < PractiseMode.repeatStart)
			{
				MessageHelper.ShowMessage("Repeat end time cannot be less than repeat start time", WindowSizeID.Middle);
				return;
			}
			PractiseMode.repeatEnd = time;
		}

		// Token: 0x060001AF RID: 431 RVA: 0x0002A838 File Offset: 0x0002A838
		public static void ClearRepeat()
		{
			PractiseMode.repeatStart = -1.0;
			PractiseMode.repeatEnd = -1.0;
		}

		// Token: 0x060001B0 RID: 432 RVA: 0x0002A858 File Offset: 0x0002A858
		public static void SetSpeed()
		{
			PractiseMode.player.SetPitch((float)(1200.0 * Math.Log((double)PractiseMode.speed, 2.0)));
			PractiseMode.player.UpdateAll();
			PractiseMode.movie.player.SetSpeed(PractiseMode.speed);
			GameCtrl gameCtrl = PractiseMode.gameCtrl;
			if (gameCtrl == null)
			{
				return;
			}
			gameCtrl.ResetOptionSpeed();
		}

		// Token: 0x060001B1 RID: 433 RVA: 0x0002A8BC File Offset: 0x0002A8BC
		private static IEnumerator SetSpeedCoroutineInner()
		{
			yield return null;
			PractiseMode.SetSpeed();
			yield break;
		}

		// Token: 0x060001B2 RID: 434 RVA: 0x0002A8C4 File Offset: 0x0002A8C4
		public static void SetSpeedCoroutine()
		{
			SharedInstances.GameMainObject.StartCoroutine(PractiseMode.SetSpeedCoroutineInner());
		}

		// Token: 0x060001B3 RID: 435 RVA: 0x0002A8D8 File Offset: 0x0002A8D8
		public static void SpeedUp()
		{
			PractiseMode.speed += 0.05f;
			if (PractiseMode.speed > 2f)
			{
				PractiseMode.speed = 2f;
			}
			PractiseMode.SetSpeed();
		}

		// Token: 0x060001B4 RID: 436 RVA: 0x0002A908 File Offset: 0x0002A908
		public static void SpeedDown()
		{
			PractiseMode.speed -= 0.05f;
			if ((double)PractiseMode.speed < 0.05)
			{
				PractiseMode.speed = 0.05f;
			}
			PractiseMode.SetSpeed();
		}

		// Token: 0x060001B5 RID: 437 RVA: 0x0002A93C File Offset: 0x0002A93C
		public static void SpeedReset()
		{
			PractiseMode.speed = 1f;
			PractiseMode.SetSpeed();
		}

		// Token: 0x060001B6 RID: 438 RVA: 0x0002A950 File Offset: 0x0002A950
		public static void Seek(int addMsec)
		{
			double num = PractiseMode.CurrentPlayMsec + (double)addMsec;
			if (num < 0.0)
			{
				num = 0.0;
			}
			PractiseMode.CurrentPlayMsec = num;
		}

		// Token: 0x17000083 RID: 131
		// (get) Token: 0x060001B7 RID: 439 RVA: 0x0002A984 File Offset: 0x0002A984
		// (set) Token: 0x060001B8 RID: 440 RVA: 0x0002A994 File Offset: 0x0002A994
		public static double CurrentPlayMsec
		{
			get
			{
				return (double)(NotesManager.GetCurrentMsec() - 91f);
			}
			set
			{
				DebugFeature.CurrentPlayMsec = value;
				PractiseMode.SetSpeedCoroutine();
			}
		}

		// Token: 0x060001B9 RID: 441 RVA: 0x00092038 File Offset: 0x00092038
		[HarmonyPatch(typeof(GameProcess), "OnStart")]
		[HarmonyPostfix]
		public static void GameProcessPostStart()
		{
			PractiseMode.repeatStart = -1.0;
			PractiseMode.repeatEnd = -1.0;
		}

		// Token: 0x060001BA RID: 442 RVA: 0x0002A9D4 File Offset: 0x0002A9D4
		[HarmonyPatch(typeof(GameCtrl), "Initialize")]
		[HarmonyPostfix]
		public static void GameCtrlPostInitialize(GameCtrl __instance)
		{
			PractiseMode.gameCtrl = __instance;
		}

		// Token: 0x060001BB RID: 443 RVA: 0x000920B0 File Offset: 0x000920B0
		[HarmonyPatch(typeof(GameProcess), "OnUpdate")]
		[HarmonyPostfix]
		public static void GameProcessPostUpdate(GameProcess __instance, GameMonitor[] ____monitors)
		{
			if (InputManager.GetSystemInputPush(InputManager.SystemButtonSetting.ButtonTest) && PractiseMode.ui == null)
			{
				PractiseMode.ui = ____monitors[0].gameObject.AddComponent<PractiseModeUI>();
			}
			if (PractiseMode.repeatStart >= 0.0 && PractiseMode.repeatEnd >= 0.0 && PractiseMode.CurrentPlayMsec >= PractiseMode.repeatEnd)
			{
				PractiseMode.CurrentPlayMsec = PractiseMode.repeatStart;
			}
		}

		// Token: 0x060001BC RID: 444 RVA: 0x0002AA40 File Offset: 0x0002AA40
		[HarmonyPatch(typeof(NotesManager), "StartPlay")]
		[HarmonyPostfix]
		public static void NotesManagerPostUpdateTimer(float msecStartGap)
		{
			PractiseMode.startGap = msecStartGap;
		}

		// Token: 0x060001BD RID: 445 RVA: 0x0002AA48 File Offset: 0x0002AA48
		[HarmonyPatch(typeof(NotesManager), "UpdateTimer")]
		[HarmonyPrefix]
		public static bool NotesManagerPostUpdateTimer(bool ____isPlaying, Stopwatch ____stopwatch, ref float ____curMSec, ref float ____curMSecPre, float ____msecStartGap)
		{
			if (PractiseMode.startGap != -1f)
			{
				____curMSec = PractiseMode.startGap;
				____curMSecPre = PractiseMode.startGap;
				if (____stopwatch != null)
				{
					____stopwatch.Reset();
				}
				PractiseMode.startGap = -1f;
			}
			else
			{
				____curMSecPre = ____curMSec;
				if (____isPlaying && ____stopwatch != null && !DebugFeature.Pause)
				{
					double num = (double)____stopwatch.ElapsedTicks / (double)Stopwatch.Frequency * 1000.0 * (double)PractiseMode.speed;
					____curMSec += (float)num;
					____stopwatch.Reset();
					____stopwatch.Start();
				}
			}
			return false;
		}

		// Token: 0x060001BE RID: 446 RVA: 0x0002AACC File Offset: 0x0002AACC
		[HarmonyPatch(typeof(SoundCtrl), "Initialize")]
		[HarmonyPostfix]
		public static void SoundCtrlPostInitialize(SoundCtrl.InitParam param, Dictionary<int, object> ____players)
		{
			object obj = ____players[2];
			PractiseMode.player = (CriAtomExPlayer)obj.GetType().GetField("Player").GetValue(obj);
		}

		// Token: 0x060001BF RID: 447 RVA: 0x0002AB04 File Offset: 0x0002AB04
		[HarmonyPatch(typeof(MovieController), "Awake")]
		[HarmonyPostfix]
		public static void MovieControllerPostAwake(MovieMaterialMai2 ____moviePlayers)
		{
			PractiseMode.movie = ____moviePlayers;
		}

		// Token: 0x040000BF RID: 191
		public static double repeatStart = -1.0;

		// Token: 0x040000C0 RID: 192
		public static double repeatEnd = -1.0;

		// Token: 0x040000C1 RID: 193
		public static float speed = 1f;

		// Token: 0x040000C2 RID: 194
		private static CriAtomExPlayer player;

		// Token: 0x040000C3 RID: 195
		private static MovieMaterialMai2 movie;

		// Token: 0x040000C4 RID: 196
		public static GameCtrl gameCtrl;

		// Token: 0x040000C5 RID: 197
		public static bool keepNoteSpeed = true;

		// Token: 0x040000C6 RID: 198
		public static PractiseModeUI ui;

		// Token: 0x040000C7 RID: 199
		private static float startGap = -1f;

		// Token: 0x040000C8 RID: 200
		public static bool CheckPlayingState;

		// Token: 0x0200003A RID: 58
		[HarmonyPatch]
		public class PatchNoteSpeed
		{
			// Token: 0x060001C2 RID: 450 RVA: 0x0002AB4C File Offset: 0x0002AB4C
			public static IEnumerable<MethodBase> TargetMethods()
			{
				yield return AccessTools.Method(typeof(GameManager), "GetNoteSpeed", null, null);
				yield return AccessTools.Method(typeof(GameManager), "GetTouchSpeed", null, null);
				yield break;
			}

			// Token: 0x060001C3 RID: 451 RVA: 0x0002AB58 File Offset: 0x0002AB58
			public static void Postfix(ref float __result)
			{
				if (!PractiseMode.keepNoteSpeed)
				{
					return;
				}
				__result /= PractiseMode.speed;
			}
		}
	}
}
