using System.Collections.Generic;
using HarmonyLib;
using Monitor;
using UI;
using UnityEngine;
using UnityEngine.UI;

namespace AquaMai.UX;

public class DisableTrackStartTabs
{
    // 在歌曲开始界面, 把 TRACK X 字样, DX/标准谱面的显示框, 以及画面下方的滴蜡熊隐藏掉, 让他看起来不那么 sinmai, 更像是 majdata
    
    [HarmonyPostfix]
    [HarmonyPatch(typeof(TrackStartMonitor), "SetTrackStart")]
    private static void DisableTabs(
        SpriteCounter ____trackNumber, SpriteCounter ____bossTrackNumber, SpriteCounter ____utageTrackNumber,
        MultipleImage ____musicTabImage, GameObject[] ____musicTabObj, GameObject ____derakkumaRoot
    )
    {
        ____trackNumber.transform.parent.gameObject.SetActive(false);
        ____bossTrackNumber.transform.parent.gameObject.SetActive(false);
        ____utageTrackNumber.transform.parent.gameObject.SetActive(false);
        ____musicTabImage.gameObject.SetActive(false);
        ____musicTabObj[0].gameObject.SetActive(false);
        ____musicTabObj[1].gameObject.SetActive(false);
        ____musicTabObj[2].gameObject.SetActive(false);
        ____derakkumaRoot.SetActive(false);
    }
}