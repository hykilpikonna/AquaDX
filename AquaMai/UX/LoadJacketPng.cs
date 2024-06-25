using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;
using HarmonyLib;
using UnityEngine;
using System.Text.RegularExpressions;

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

            var texture = GetJacketTexture2D(id);
            if (texture is null)
            {
                __result = __instance.LoadAsset<Texture2D>($"Jacket/UI_Jacket_{id}.png");
            }
            else
            {
                __result = texture;
            }

            return false;
        }

        private static string GetJacketPath(string id)
        {
            foreach (var ext in new[] { ".jpg", ".png", ".webp", ".bmp", ".gif" })
            {
                if (File.Exists(Path.Combine(Environment.CurrentDirectory, "LocalAssets", id + ext)))
                {
                    return Path.Combine(Environment.CurrentDirectory, "LocalAssets", id + ext);
                }
            }

            return null;
        }

        public static Texture2D GetJacketTexture2D(string id)
        {
            var path = GetJacketPath(id);
            if (path == null)
            {
                return null;
            }

            var texture = new Texture2D(1, 1);
            texture.LoadImage(File.ReadAllBytes(path));
            return texture;
        }

        public static Texture2D GetJacketTexture2D(int id)
        {
            return GetJacketTexture2D($"{id:000000}");
        }
    }
}
