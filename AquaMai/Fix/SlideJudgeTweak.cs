using System;
using HarmonyLib;
using Manager;
using Monitor;
using Process;
using UnityEngine;

namespace AquaMai.Fix;

public class SlideJudgeTweak
{
    /*
     * 这个 Patch 让 BreakSlide 的 Critical 判定也可以像 BreakTap 一样闪烁
     */
    [HarmonyPostfix]
    [HarmonyPatch(typeof(SlideJudge), "UpdateBreakEffectAdd")]
    private static void FixBreakSlideJudgeBlink(
        SpriteRenderer ___SpriteRenderAdd, SpriteRenderer ___SpriteRender,
        SlideJudge.SlideJudgeType ____judgeType, SlideJudge.SlideAngle ____angle
    )
    {
        if (!___SpriteRenderAdd.gameObject.activeSelf) return;
        float num = ___SpriteRenderAdd.color.r;
        ___SpriteRenderAdd.color = new Color(num, num, num, 0.3f);
        if (num > 0.9f)
        {
            ___SpriteRender.sprite = GameNoteImageContainer.JudgeSlideCriticalBreak[(int) ____judgeType, (int) ____angle];
        }
        else if (num < 0.1f)
        {
            ___SpriteRender.sprite = GameNoteImageContainer.JudgeSlideCritical[(int) ____judgeType, (int) ____angle];
        }
    }

    /*
     * 这个 Patch 让圆弧形的 Slide 的判定显示与判定线精确对齐 (原本会有一点歪), 就像 majdata 里那样
     */
    [HarmonyPostfix]
    [HarmonyPatch(typeof(SlideRoot), "Initialize")]
    private static void FixCircleSlideJudgePosition(
        SlideRoot __instance, SlideType ___EndSlideType, SlideJudge ___JudgeObj
    )
    {
        if (null != ___JudgeObj)
        {
            float z = ___JudgeObj.transform.localPosition.z;
            if (___EndSlideType == SlideType.Slide_Circle_L)
            {
                float angle = -45.0f - 45.0f * __instance.EndButtonId;
                double angleRad = Math.PI / 180.0 * (angle + 90 + 22.5 + 2.6415);
                ___JudgeObj.transform.localPosition = new Vector3(480f * (float)Math.Cos(angleRad), 480f * (float)Math.Sin(angleRad), z);
                ___JudgeObj.transform.localRotation = Quaternion.Euler(0.0f, 0.0f, angle);
            }
            else if (___EndSlideType == SlideType.Slide_Circle_R)
            {
                float angle = -45.0f * __instance.EndButtonId;
                double angleRad = Math.PI / 180.0 * (angle + 90 - 22.5 - 2.6415);
                ___JudgeObj.transform.localPosition = new Vector3(480f * (float)Math.Cos(angleRad), 480f * (float)Math.Sin(angleRad), z);
                ___JudgeObj.transform.localRotation = Quaternion.Euler(0.0f, 0.0f, angle);
            }
        }
    }

    /*
     * 这个 Patch 让 Wifi Slide 的判定显示有上下的区别 (原本所有 Wifi 的判定显示都是朝向圆心的), 就像 majdata 里那样
     * 这个 bug 产生的原因是 SBGA 忘记给 Wifi 的 EndButtonId 赋值了
     * 不过需要注意的是, 考虑到圆弧形 Slide 的判定显示就是永远朝向圆心的, 我个人会觉得这个 Patch 关掉更好看一点
     * 所以这里把 Patch 注释掉了
     */
    // [HarmonyPostfix]
    // [HarmonyPatch(typeof(SlideFan), "Initialize")]
    private static void FixFanJudgeFilp(
        int[] ___GoalButtonId, SlideJudge ___JudgeObj
    )
    {
        if (null != ___JudgeObj)
        {
            if (2 <= ___GoalButtonId[1] && ___GoalButtonId[1] <= 5)
            {
                ___JudgeObj.Flip(false);
                ___JudgeObj.transform.Rotate(0.0f, 0.0f, 180f);
            }
            else
            {
                ___JudgeObj.Flip(true);
            }
        }
    }
}
