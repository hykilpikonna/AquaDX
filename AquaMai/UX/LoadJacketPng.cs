using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Reflection;
using HarmonyLib;
using UnityEngine;
using System.Text.RegularExpressions;
using Manager;
using MelonLoader;

namespace AquaMai.UX
{
    public class LoadJacketPng
    {
        [HarmonyPatch]
        public static class Loader
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
                __result = texture ?? __instance.LoadAsset<Texture2D>($"Jacket/UI_Jacket_{id}.png");

                return false;
            }
        }

        private static string[] imageExts = [".jpg", ".png", ".jpeg"];
        private static Regex localAssetsJacketExt = new(@"(\d{6})\.(png|jpg|jpeg)");
        private static Dictionary<string, string> jacketPaths = new();

        [HarmonyPrefix]
        [HarmonyPatch(typeof(DataManager), "LoadMusicBase")]
        public static void LoadMusicPostfix(List<string> ____targetDirs)
        {
            foreach (var aDir in ____targetDirs)
            {
                if (!Directory.Exists(Path.Combine(aDir, @"AssetBundleImages\jacket"))) continue;
                foreach (var file in Directory.GetFiles(Path.Combine(aDir, @"AssetBundleImages\jacket")))
                {
                    if (!imageExts.Contains(Path.GetExtension(file).ToLowerInvariant())) continue;
                    var idStr = Path.GetFileName(file).Substring("ui_jacket_".Length, 6);
                    jacketPaths[idStr] = file;
                }
            }

            foreach (var laFile in Directory.EnumerateFiles(Path.Combine(Environment.CurrentDirectory, "LocalAssets")))
            {
                var match = localAssetsJacketExt.Match(Path.GetFileName(laFile));
                if (!match.Success) continue;
                jacketPaths[match.Groups[1].Value] = laFile;
            }

            MelonLogger.Msg($"Loaded {jacketPaths.Count} custom jacket images.");
        }

        private static string GetJacketPath(string id)
        {
            return jacketPaths.GetValueOrDefault(id);
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
