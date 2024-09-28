using DB;
using HarmonyLib;
using Manager;
using MelonLoader;
using Process;

namespace AquaMai.Helpers;

public class MessageHelper
{
    private static IGenericManager _genericManager = null;

    [HarmonyPostfix]
    [HarmonyPatch(typeof(ProcessManager), "SetMessageManager")]
    private static void OnSetMessageManager(IGenericManager genericManager)
    {
        _genericManager = genericManager;
    }

    public static void ShowMessage(string message, WindowSizeID size = WindowSizeID.Middle)
    {
        if (_genericManager is null)
        {
            MelonLogger.Error($"[MessageHelper] Unable to show message: `{message}` GenericManager is null");
            return;
        }

        _genericManager.Enqueue(0, WindowMessageID.CollectionAttentionEmptyFavorite, new WindowParam()
        {
            hideTitle = true,
            replaceText = true,
            text = message,
            changeSize = true,
            sizeID = size,
        });
    }
}
