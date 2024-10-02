using System;
using System.IO;
using HarmonyLib;
using MelonLoader;
using TMPro;
using UnityEngine;
using UnityEngine.TextCore.LowLevel;

namespace AquaMai.UX;

public class CustomFont
{
    private static TMP_FontAsset fontAsset;

    public static void DoCustomPatch(HarmonyLib.Harmony h)
    {
        var fontPath = Path.Combine(Environment.CurrentDirectory, "LocalAssets", "font.ttf");
        if (!File.Exists(fontPath)) return;

        var font = new Font(fontPath);

        // 不设置成 8192 的话，贴图会用完，剩下的字显示不出来
        fontAsset = TMP_FontAsset.CreateFontAsset(font, 90, 9, GlyphRenderMode.SDFAA, 8192, 8192);
    }

    [HarmonyPatch(typeof(TextMeshProUGUI), "Awake")]
    [HarmonyPostfix]
    public static void PostFix(TextMeshProUGUI __instance)
    {
        if (fontAsset is null) return;
# if DEBUG
        MelonLogger.Msg($"{__instance.font.name} {__instance.text}");
# endif
        __instance.font = fontAsset;
    }
}
