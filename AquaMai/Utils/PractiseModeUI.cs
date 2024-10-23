using System;
using AquaMai.Fix;
using AquaMai.Helpers;
using AquaMai.Resources;
using Manager;
using Monitor.Game;
using UnityEngine;

namespace AquaMai.Utils
{
	// Token: 0x0200003D RID: 61
	public class PractiseModeUI : MonoBehaviour
	{
		// Token: 0x17000088 RID: 136
		// (get) Token: 0x060001D3 RID: 467 RVA: 0x00092056 File Offset: 0x00092056
		private static float windowTop
		{
			get
			{
				return (float)Screen.height - GuiSizes.PlayerWidth + GuiSizes.PlayerWidth * 0.22f;
			}
		}

		// Token: 0x17000089 RID: 137
		// (get) Token: 0x060001D4 RID: 468 RVA: 0x00092070 File Offset: 0x00092070
		private static float controlHeight
		{
			get
			{
				return GuiSizes.PlayerWidth * 0.13f;
			}
		}

		// Token: 0x1700008A RID: 138
		// (get) Token: 0x060001D5 RID: 469 RVA: 0x0009207D File Offset: 0x0009207D
		private static float sideButtonWidth
		{
			get
			{
				return GuiSizes.PlayerWidth * 0.1f;
			}
		}

		// Token: 0x1700008B RID: 139
		// (get) Token: 0x060001D6 RID: 470 RVA: 0x0009208A File Offset: 0x0009208A
		private static float centerButtonWidth
		{
			get
			{
				return GuiSizes.PlayerWidth * 0.28f;
			}
		}

		// Token: 0x1700008C RID: 140
		// (get) Token: 0x060001D7 RID: 471 RVA: 0x00092097 File Offset: 0x00092097
		private static int fontSize
		{
			get
			{
				return (int)(GuiSizes.PlayerWidth * 0.018f);
			}
		}

		// Token: 0x060001D8 RID: 472 RVA: 0x0009211C File Offset: 0x0009211C
		private static Rect GetButtonRect(int pos, int row)
		{
			float x;
			float width;
			switch (pos)
			{
			case 0:
				x = GuiSizes.PlayerCenter - PractiseModeUI.centerButtonWidth / 2f - PractiseModeUI.sideButtonWidth - GuiSizes.Margin;
				width = PractiseModeUI.sideButtonWidth;
				break;
			case 1:
				x = GuiSizes.PlayerCenter - PractiseModeUI.centerButtonWidth / 2f;
				width = PractiseModeUI.centerButtonWidth;
				break;
			case 2:
				x = GuiSizes.PlayerCenter + PractiseModeUI.centerButtonWidth / 2f + GuiSizes.Margin;
				width = PractiseModeUI.sideButtonWidth;
				break;
			default:
				throw new ArgumentOutOfRangeException("pos", pos, null);
			}
			return new Rect(x, PractiseModeUI.windowTop + (GuiSizes.Margin + PractiseModeUI.controlHeight) * (float)row + GuiSizes.Margin, width, PractiseModeUI.controlHeight);
		}

		// Token: 0x060001D9 RID: 473 RVA: 0x000921D8 File Offset: 0x000921D8
		public void OnGUI()
		{
			GUIStyle style = GUI.skin.GetStyle("label");
			style.fontSize = PractiseModeUI.fontSize;
			style.alignment = TextAnchor.MiddleCenter;
			GUIStyle guistyle = new GUIStyle(GUI.skin.button);
			guistyle.fontSize = PractiseModeUI.fontSize;
			guistyle.normal.textColor = Color.white;
			guistyle.hover.textColor = Color.yellow;
			guistyle.active.textColor = Color.yellow;
			Color backgroundColor = new Color(0.8f, 0.9f, 1f, 0.3f);
			new Color(0.6f, 0.8f, 1f, 0.3f);
			guistyle.normal.background = Texture2D.whiteTexture;
			guistyle.hover.background = Texture2D.whiteTexture;
			guistyle.active.background = Texture2D.whiteTexture;
			guistyle.border = new RectOffset(12, 12, 12, 12);
			guistyle.margin = new RectOffset(10, 10, 10, 10);
			guistyle.padding = new RectOffset(10, 10, 10, 10);
			guistyle.overflow = new RectOffset(0, 0, 0, 0);
			guistyle.normal.background = Texture2D.whiteTexture;
			GUIStyle guistyle2 = new GUIStyle(GUI.skin.box);
			guistyle2.border = new RectOffset(12, 12, 12, 12);
			guistyle2.normal.background = Texture2D.whiteTexture;
			Color backgroundColor2 = new Color(0.8f, 0.9f, 1f, 0.1f);
			Color backgroundColor3 = GUI.backgroundColor;
			GUI.backgroundColor = backgroundColor2;
			GUI.Box(new Rect(GuiSizes.PlayerCenter - PractiseModeUI.centerButtonWidth / 2f - PractiseModeUI.sideButtonWidth - GuiSizes.Margin * 2f, PractiseModeUI.windowTop, PractiseModeUI.centerButtonWidth + PractiseModeUI.sideButtonWidth * 2f + GuiSizes.Margin * 4f, PractiseModeUI.controlHeight * 4f + GuiSizes.Margin * 5f), "", guistyle2);
			GUI.backgroundColor = backgroundColor;
			GUI.Button(PractiseModeUI.GetButtonRect(0, 0), Locale.SeekBackward, guistyle);
			GUI.Button(PractiseModeUI.GetButtonRect(1, 0), Locale.Pause, guistyle);
			GUI.Button(PractiseModeUI.GetButtonRect(2, 0), Locale.SeekForward, guistyle);
			if (PractiseMode.repeatStart == -1.0)
			{
				GUI.Button(PractiseModeUI.GetButtonRect(0, 1), Locale.MarkRepeatStart, guistyle);
				GUI.Label(PractiseModeUI.GetButtonRect(1, 1), Locale.RepeatNotSet, style);
			}
			else if (PractiseMode.repeatEnd == -1.0)
			{
				GUI.Button(PractiseModeUI.GetButtonRect(0, 1), Locale.MarkRepeatEnd, guistyle);
				GUI.Label(PractiseModeUI.GetButtonRect(1, 1), Locale.RepeatStartSet, style);
				if (GUI.Button(PractiseModeUI.GetButtonRect(2, 1), Locale.RepeatReset, guistyle))
				{
				}
			}
			else
			{
				GUI.Label(PractiseModeUI.GetButtonRect(1, 1), Locale.RepeatStartEndSet, style);
				GUI.Button(PractiseModeUI.GetButtonRect(2, 1), Locale.RepeatReset, guistyle);
			}
			GUI.Button(PractiseModeUI.GetButtonRect(0, 2), Locale.SpeedDown, guistyle);
			GUI.Label(PractiseModeUI.GetButtonRect(1, 2), string.Format("{0} {1:000}%", Locale.Speed, PractiseMode.speed * 100f), style);
			GUI.Button(PractiseModeUI.GetButtonRect(2, 2), Locale.SpeedUp, guistyle);
			GUI.Button(PractiseModeUI.GetButtonRect(1, 3), Locale.SpeedReset, guistyle);
			GUI.Label(PractiseModeUI.GetButtonRect(0, 3), string.Format("{0:mm\\:ss\\.fff}\n{1:mm\\:ss\\.fff}", TimeSpan.FromMilliseconds(PractiseMode.CurrentPlayMsec), TimeSpan.FromMilliseconds(NotesManager.Instance().getPlayFinalMsec())), style);
			GUIStyle guistyle3 = new GUIStyle(guistyle);
			guistyle3.normal.textColor = (PractiseMode.keepNoteSpeed ? Color.green : Color.red);
			GUI.Button(PractiseModeUI.GetButtonRect(2, 3), "保持流速\n" + (PractiseMode.keepNoteSpeed ? "ON" : "OFF"), guistyle3);
			GUI.backgroundColor = backgroundColor3;
		}

		// Token: 0x060001DA RID: 474 RVA: 0x000925B0 File Offset: 0x000925B0
		public void Update()
		{
			if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.E8))
			{
				PractiseMode.Seek(-1000);
				return;
			}
			if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.E2))
			{
				PractiseMode.Seek(1000);
				return;
			}
			if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B8) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B1))
			{
				DebugFeature.Pause = !DebugFeature.Pause;
				if (!DebugFeature.Pause)
				{
					PractiseMode.Seek(0);
					return;
				}
			}
			else
			{
				if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B7) && PractiseMode.repeatStart == -1.0)
				{
					PractiseMode.repeatStart = PractiseMode.CurrentPlayMsec;
					return;
				}
				if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B7) && PractiseMode.repeatEnd == -1.0)
				{
					PractiseMode.SetRepeatEnd(PractiseMode.CurrentPlayMsec);
					return;
				}
				if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B2))
				{
					PractiseMode.ClearRepeat();
					return;
				}
				if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B6))
				{
					PractiseMode.SpeedDown();
					return;
				}
				if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B3))
				{
					PractiseMode.SpeedUp();
					return;
				}
				if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B5) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B4))
				{
					PractiseMode.SpeedReset();
					return;
				}
				if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.E4))
				{
					PractiseMode.keepNoteSpeed = !PractiseMode.keepNoteSpeed;
					GameCtrl gameCtrl = PractiseMode.gameCtrl;
					if (gameCtrl == null)
					{
						return;
					}
					gameCtrl.ResetOptionSpeed();
					return;
				}
				else if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A1) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A2) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A3) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A4) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A5) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A6) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A7) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A8))
				{
					PractiseMode.ui = null;
					UnityEngine.Object.Destroy(this);
				}
			}
		}
	}
}
