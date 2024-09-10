using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using HarmonyLib;
using MelonLoader;
using Net.Packet;

namespace AquaMai.Fix;

public class RemoveEncryption
{
    [HarmonyPrefix]
    [HarmonyPatch(typeof(Packet), "Obfuscator", typeof(string))]
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
# if SDGA145
            return [AccessTools.Method("Net.CipherAES:Encrypt", [typeof(byte[])])];
# else
            return [AccessTools.TypeByName("Net.CipherAES").GetMethods().FirstOrDefault(it => it.Name == "Encrypt")];
# endif
        }

# if SDGA145
        public static bool Prefix(byte[] data, ref byte[] __result)
        {
            __result = data;
            return false;
        }
# else
        public static bool Prefix(byte[] data, out byte[] encryptData, ref bool __result)
        {
            encryptData = data;
            __result = true;
            return false;
        }
# endif
    }

    [HarmonyPatch]
    public class Decrypt
    {
        public static IEnumerable<MethodBase> TargetMethods()
        {
# if SDGA145
            return [AccessTools.Method("Net.CipherAES:Decrypt", [typeof(byte[])])];
# else
            return [AccessTools.TypeByName("Net.CipherAES").GetMethods().FirstOrDefault(it => it.Name == "Decrypt")];
# endif
        }

# if SDGA145
        public static bool Prefix(byte[] encryptData, ref byte[] __result)
        {
            __result = encryptData;
            return false;
        }
# else
        public static bool Prefix(byte[] encryptData, out byte[] plainData, ref bool __result)
        {
            plainData = encryptData;
            __result = true;
            return false;
        }
# endif
    }
}
