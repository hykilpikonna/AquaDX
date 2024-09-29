using System;
using AquaMai.Fix;
using Manager;
using UnityEngine;
using UrGUI.GUIWindow;

namespace AquaMai.Utils;

public class PractiseModeUI : MonoBehaviour
{
    private float playerWidth;
    private float playerCenter;
    private float windowTop;
    private float controlHeight;
    private float margin;
    private float sideButtonWidth;
    private float centerButtonWidth;
    private int fontSize;

    public void Start()
    {
        playerWidth = Screen.height / 1920f * 1080;
        if (AquaMai.AppConfig.UX.SinglePlayer)
        {
            playerCenter = Screen.width / 2f;
        }
        else
        {
            playerCenter = Screen.width / 2f - playerWidth / 2;
        }

        windowTop = Screen.height - playerWidth + playerWidth * .22f;
        controlHeight = playerWidth * .13f;
        margin = playerWidth * .01f;
        sideButtonWidth = playerWidth * .1f;
        centerButtonWidth = playerWidth * .28f;
        fontSize = (int)(playerWidth * .02f);
    }

    public Rect GetButtonRect(int pos, int row)
    {
        float x;
        float width;
        switch (pos)
        {
            case 0:
                x = playerCenter - centerButtonWidth / 2 - sideButtonWidth - margin;
                width = sideButtonWidth;
                break;
            case 1:
                x = playerCenter - centerButtonWidth / 2;
                width = centerButtonWidth;
                break;
            case 2:
                x = playerCenter + centerButtonWidth / 2 + margin;
                width = sideButtonWidth;
                break;
            default:
                throw new ArgumentOutOfRangeException(nameof(pos), pos, null);
        }

        return new Rect(x, windowTop + (margin + controlHeight) * row + margin, width, controlHeight);
    }

    public void OnGUI()
    {
        var labelStyle = GUI.skin.GetStyle("label");
        labelStyle.fontSize = fontSize;
        labelStyle.alignment = TextAnchor.MiddleCenter;

        var buttonStyle = GUI.skin.GetStyle("button");
        buttonStyle.fontSize = fontSize;

        GUI.Box(new Rect(
            playerCenter - centerButtonWidth / 2 - sideButtonWidth - margin * 2,
            windowTop,
            centerButtonWidth + sideButtonWidth * 2 + margin * 4,
            controlHeight * 4 + margin * 5
        ), "");

        GUI.Button(GetButtonRect(0, 0), "Seek <<");
        GUI.Button(GetButtonRect(1, 0), "Pause");
        GUI.Button(GetButtonRect(2, 0), "Seek >>");

        if (PractiseMode.repeatStart == -1)
        {
            GUI.Button(GetButtonRect(0, 1), "Start");
            GUI.Label(GetButtonRect(1, 1), "Loop not set");
        }
        else if (PractiseMode.repeatEnd == -1)
        {
            GUI.Button(GetButtonRect(0, 1), "End");
            GUI.Label(GetButtonRect(1, 1), "Loop start set");
            GUI.Button(GetButtonRect(2, 1), "Reset");
        }
        else
        {
            GUI.Label(GetButtonRect(1, 1), "Loop set");
            GUI.Button(GetButtonRect(2, 1), "Reset");
        }

        GUI.Button(GetButtonRect(0, 2), "Speed -");
        GUI.Label(GetButtonRect(1, 2), $"Speed {PractiseMode.speed * 100:000}%");
        GUI.Button(GetButtonRect(2, 2), "Speed +");
        GUI.Button(GetButtonRect(1, 3), "Speed Reset");
    }

    public void Update()
    {
        if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.E8))
        {
            DebugFeature.Seek(-1000);
        }
        else if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.E2))
        {
            DebugFeature.Seek(1000);
        }
        else if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B8) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B1))
        {
            DebugFeature.Pause = !DebugFeature.Pause;
        }
        else if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B7) && PractiseMode.repeatStart == -1)
        {
            PractiseMode.repeatStart = DebugFeature.CurrentPlayMsec;
        }
        else if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B7) && PractiseMode.repeatEnd == -1)
        {
            PractiseMode.SetRepeatEnd(DebugFeature.CurrentPlayMsec);
        }
        else if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B2))
        {
            PractiseMode.ClearRepeat();
        }
        else if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B6))
        {
            PractiseMode.SpeedDown();
        }
        else if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B3))
        {
            PractiseMode.SpeedUp();
        }
        else if (InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B5) || InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.B4))
        {
            PractiseMode.SpeedReset();
        }
        else if (
            InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A1) ||
            InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A2) ||
            InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A3) ||
            InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A4) ||
            InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A5) ||
            InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A6) ||
            InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A7) ||
            InputManager.GetTouchPanelAreaDown(InputManager.TouchPanelArea.A8)
        )
        {
            PractiseMode.ui = null;
            Destroy(this);
        }
    }
}
