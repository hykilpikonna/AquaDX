using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;
using HarmonyLib;
using UnityEngine;
using System.Text.RegularExpressions;
using MelonLoader;

namespace AquaMai.UX
{
    [HarmonyPatch]
    public class LoadJacketPng
    {
        public static IEnumerable<MethodBase> TargetMethods()
        {
            var AM = typeof(AssetManager);
            return new[] { AM.GetMethod("GetJacketThumbTexture2D", new[] { typeof(string) }), AM.GetMethod("GetJacketTexture2D", new[] { typeof(string) }) };
        }

        public static bool Prefix(string filename, ref Texture2D __result, AssetManager __instance)
        {
            var matches = Regex.Matches(filename, @"UI_Jacket_(\d+)(_s)?\.png");
            if (matches.Count < 1)
            {
                return true;
            }

            var id = matches[0].Groups[1].Value;
            foreach (var ext in new[] { ".jpg", ".png", ".webp", ".bmp", ".gif" })
            {
                if (File.Exists(Path.Combine(Environment.CurrentDirectory, "LocalAssets", id + ext)))
                {
                    filename = id + ext;
                }
            }

            var localPath = Path.Combine(Environment.CurrentDirectory, "LocalAssets", filename);
            if (File.Exists(localPath))
            {
                __result = new Texture2D(1, 1);
                ImageConversion.LoadImage(__result, File.ReadAllBytes(localPath));
            }
            else
            {
                __result = __instance.LoadAsset<Texture2D>($"Jacket/UI_Jacket_{id}.png");
            }

            return false;
        }
    }
}