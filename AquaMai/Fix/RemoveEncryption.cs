using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Reflection;
using HarmonyLib;
using Net;
using Net.Packet;

namespace AquaMai.Fix;

public class RemoveEncryption
{
    [HarmonyPrefix]
    [HarmonyPatch(typeof(Packet), "Obfuscator", typeof(string))]
    public static bool PreObfuscator(string srcStr, ref string __result)
    {
        __result = srcStr.Replace("MaimaiExp", "").Replace("MaimaiChn", "");
        return false;
    }

    [HarmonyPatch]
    public class EncryptDecrypt
    {
        public static IEnumerable<MethodBase> TargetMethods()
        {
            var methods = AccessTools.TypeByName("Net.CipherAES").GetMethods();
            return
            [
                methods.FirstOrDefault(it => it.Name == "Encrypt" && it.IsPublic),
                methods.FirstOrDefault(it => it.Name == "Decrypt" && it.IsPublic)
            ];
        }

        public static bool Prefix(object[] __args, ref object __result)
        {
            if (__args.Length == 1)
            {
                // public static byte[] Encrypt(byte[] data)
	            // public static byte[] Decrypt(byte[] encryptData)
                __result = __args[0];
            }
            else if (__args.Length == 2)
            {
                // public static bool Encrypt(byte[] data, out byte[] encryptData)
	            // public static bool Decrypt(byte[] encryptData, out byte[] plainData)
                __args[1] = __args[0];
                __result = true;
            }
            return false;
        }
    }

    [HarmonyPostfix]
    [HarmonyPatch(typeof(NetHttpClient), MethodType.Constructor)]
    public static void OnNetHttpClientConstructor(NetHttpClient __instance)
    {
        // Bypass Cake.dll hash check
        var tInstance = Traverse.Create(__instance).Field("isTrueDll");
        if (tInstance.FieldExists())
        {
            tInstance.SetValue(true);
        }
    }

    [HarmonyPostfix]
    [HarmonyPatch(typeof(NetHttpClient), "Create")]
    public static void OnNetHttpClientCreate()
    {
        // Unset the certificate validation callback (SSL pinning) to restore the default behavior
        ServicePointManager.ServerCertificateValidationCallback = null;
    }
}
