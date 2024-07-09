using System.Collections.Generic;
using System.Reflection;
using HarmonyLib;
using Net.Packet;

namespace AquaMai.Fix;

public class RemoveEncryption
{
    [HarmonyPrefix]
    [HarmonyPatch(typeof(Packet), "Obfuscator")]
    private static bool PreObfuscator(string srcStr, ref string __result)
    {
        __result = srcStr.Replace("MaimaiExp", "").Replace("MaimaiChn", "");
        return false;
    }

    [HarmonyPatch]
    public class Encrypt
    {
        public static IEnumerable<MethodBase> TargetMethods()
        {
            return new[] { AccessTools.Method("Net.CipherAES:Encrypt") };
        }

        public static bool Prefix(byte[] data, ref byte[] __result)
        {
            __result = data;
            return false;
        }
    }

    [HarmonyPatch]
    public class Decrypt
    {
        public static IEnumerable<MethodBase> TargetMethods()
        {
            return new[] { AccessTools.Method("Net.CipherAES:Decrypt") };
        }

        public static bool Prefix(byte[] encryptData, ref byte[] __result)
        {
            __result = encryptData;
            return false;
        }
    }
}
