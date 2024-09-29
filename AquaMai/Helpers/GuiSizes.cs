using UnityEngine;

namespace AquaMai.Helpers;

public static class GuiSizes
{
    public static float PlayerWidth => Screen.height / 1920f * 1080;
    public static float PlayerCenter => AquaMai.AppConfig.UX.SinglePlayer ? Screen.width / 2f : Screen.width / 2f - PlayerWidth / 2;
    public static int FontSize => (int)(PlayerWidth * .015f);
    public static float LabelHeight => FontSize * 1.5f;
    public static float Margin => PlayerWidth * .005f;
}
