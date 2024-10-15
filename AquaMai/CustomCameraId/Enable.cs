using static Manager.CameraManager;
using System.Collections;
using HarmonyLib;
using Manager;
using MelonLoader;
using UnityEngine;

namespace AquaMai.CustomCameraId;

public class Enable
{
    [HarmonyPrefix]
    [HarmonyPatch(typeof(CameraManager), "CameraInitialize")]
    public static bool CameraInitialize(CameraManager __instance, ref IEnumerator __result)
    {
        __result = CameraInitialize(__instance);
        return false;
    }
    
    [HarmonyPostfix]
    [HarmonyPatch(typeof(CameraManager), "Initialize")]
    public static void SetCameraResolution(CameraManager __instance)
    {
        PrintCameraList();
        WebCamDevice gameDevice = WebCamTexture.devices[AquaMai.AppConfig.CustomCameraId.PhotoCamera];
        WebCamDevice qrDevice = WebCamTexture.devices[AquaMai.AppConfig.CustomCameraId.LeftQrCamera];
        WebCamTexture gameTexture = new WebCamTexture(gameDevice.name);
        WebCamTexture qrTexture = new WebCamTexture(qrDevice.name);
        gameTexture.Play();
        qrTexture.Play();
        CameraParameter gameCameraParam = new CameraParameter(gameTexture.width, gameTexture.height, (int)gameTexture.requestedFPS);
        CameraParameter qrCameraParam = new CameraParameter(qrTexture.width, qrTexture.height, (int)qrTexture.requestedFPS);
        AccessTools.Field(typeof(CameraManager), "GameCameraParam").SetValue(__instance, gameCameraParam);
        AccessTools.Field(typeof(CameraManager), "QrCameraParam").SetValue(__instance, qrCameraParam);
        gameTexture.Stop();
        qrTexture.Stop();
    }
    
    private static IEnumerator CameraInitialize(CameraManager __instance)
    {
        var webcamtexField = AccessTools.Field(typeof(CameraManager), "_webcamtex");
        WebCamTexture[] webcamtex = new WebCamTexture[3];
        int leftQrCameraId = ((AquaMai.AppConfig.CustomCameraId.LeftQrCamera < WebCamTexture.devices.Length)
            ? AquaMai.AppConfig.CustomCameraId.LeftQrCamera
            : 0);
        int rightQrCameraId = ((AquaMai.AppConfig.CustomCameraId.RightQrCamera < WebCamTexture.devices.Length)
            ? AquaMai.AppConfig.CustomCameraId.RightQrCamera
            : 0);
        int photoCameraId = ((AquaMai.AppConfig.CustomCameraId.PhotoCamera < WebCamTexture.devices.Length)
            ? AquaMai.AppConfig.CustomCameraId.PhotoCamera
            : 0);
        webcamtex[leftQrCameraId] = new WebCamTexture(WebCamTexture.devices[leftQrCameraId].name, QrCameraParam.Width, QrCameraParam.Height, QrCameraParam.Fps);
        webcamtex[rightQrCameraId] = new WebCamTexture(WebCamTexture.devices[rightQrCameraId].name, QrCameraParam.Width, QrCameraParam.Height, QrCameraParam.Fps);
        webcamtex[photoCameraId] = new WebCamTexture(WebCamTexture.devices[photoCameraId].name, GameCameraParam.Width, GameCameraParam.Height, GameCameraParam.Fps);
        webcamtexField.SetValue(__instance, webcamtex);
        
        DeviceId[(int) CameraTypeEnum.QRLeft] = leftQrCameraId;
        DeviceId[(int) CameraTypeEnum.QRRight] = rightQrCameraId;
        DeviceId[(int) CameraTypeEnum.Photo] = photoCameraId;
            
        for (int i = 0; i < webcamtex.Length; i++)
        {
            __instance.isAvailableCamera[i] = true;
            __instance.cameraProcMode[i] = CameraManager.CameraProcEnum.Good;
        }

        CameraManager.IsReady = true;
        yield break;
    }
    
    private static void PrintCameraList()
    {
        WebCamDevice[] devices = WebCamTexture.devices;
        string cameraList = "Connected Web Cameras:\n";
        for (int i = 0; i < devices.Length; i++)
        {
            WebCamDevice webCamDevice = devices[i];
            WebCamTexture webCamTexture = new WebCamTexture(webCamDevice.name);
            webCamTexture.Play();
            cameraList += "==================================================\n";
            cameraList += "Name: " + webCamDevice.name + "\n";
            cameraList += $"ID: {i}\n";
            cameraList += $"Resolution: {webCamTexture.width} * {webCamTexture.height}\n";
            cameraList += $"FPS: {webCamTexture.requestedFPS}\n";
            webCamTexture.Stop();
        }
        cameraList += "==================================================";
        MelonLogger.Msg(cameraList);
    }
}