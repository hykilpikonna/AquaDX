using System.Diagnostics;
using System.Linq;
using HarmonyLib;
using Manager;
using MelonLoader;

namespace AquaMai.UX;

public class TestProof
{
    private static int _keyPressFrames;

    [HarmonyPrefix]
    [HarmonyPatch(typeof(InputManager), "GetSystemInputDown")]
    public static bool GetSystemInputDown(ref bool __result, InputManager.SystemButtonSetting button, bool[] ___SystemButtonDown)
    {
        __result = ___SystemButtonDown[(int)button];
        if (button != InputManager.SystemButtonSetting.ButtonTest)
            return false;
        if (!InputManager.GetSystemInputPush(button))
        {
            _keyPressFrames = 0;
            return false;
        }

        var stackTrace = new StackTrace(); // get call stack
        var stackFrames = stackTrace.GetFrames(); // get method calls (frames)

        if (stackFrames.Any(it => it.GetMethod().Name == "DMD<Main.GameMainObject::Update>"))
        {
            __result = false;
            if (InputManager.GetSystemInputPush(button))
            {
                _keyPressFrames++;
            }

            if (_keyPressFrames == 60)
            {
                __result = true;
            }

            MelonLogger.Msg(_keyPressFrames);
        }

        return false;
    }
}
