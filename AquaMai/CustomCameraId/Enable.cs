using static Manager.CameraManager;
using System.Collections;
using System.Diagnostics;
using AquaMai.Utils;
using HarmonyLib;
using Manager;
using MelonLoader;
using UnityEngine;

namespace AquaMai.CustomCameraId;

public class Enable
{
    private static CameraParameter _gameCameraParam;
    private static CameraParameter _qrCameraParam;
    
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
        if(AquaMai.AppConfig.CustomCameraId.PrintCameraList) PrintCameraList();
        if (GameInfo.GetGameId() != "SDEZ")
        {
            WebCamDevice qrDevice = WebCamTexture.devices[GameInfo.GetGameId() == "SDGB" ? AquaMai.AppConfig.CustomCameraId.ChimeCamera : AquaMai.AppConfig.CustomCameraId.LeftQrCamera];
            WebCamTexture qrTexture = new WebCamTexture(qrDevice.name);
            qrTexture.Play();
            _qrCameraParam = new CameraParameter(qrTexture.width, qrTexture.height, (int)qrTexture.requestedFPS);
            AccessTools.Field(typeof(CameraManager), "QrCameraParam").SetValue(__instance, _qrCameraParam);
            qrTexture.Stop();
        }
        WebCamDevice gameDevice = WebCamTexture.devices[AquaMai.AppConfig.CustomCameraId.PhotoCamera];
        WebCamTexture gameTexture = new WebCamTexture(gameDevice.name);
        gameTexture.Play();
        _gameCameraParam = new CameraParameter(gameTexture.width, gameTexture.height, (int)gameTexture.requestedFPS);
        AccessTools.Field(typeof(CameraManager), "GameCameraParam").SetValue(__instance, _gameCameraParam);
        gameTexture.Stop();
    }
    
    private static IEnumerator CameraInitialize(CameraManager __instance)
    {
        WebCamTexture[] webcamtex = new WebCamTexture[WebCamTexture.devices.Length];
        int leftQrCameraId = ((AquaMai.AppConfig.CustomCameraId.LeftQrCamera < WebCamTexture.devices.Length)
            ? AquaMai.AppConfig.CustomCameraId.LeftQrCamera
            : 0);
        int rightQrCameraId = ((AquaMai.AppConfig.CustomCameraId.RightQrCamera < WebCamTexture.devices.Length)
            ? AquaMai.AppConfig.CustomCameraId.RightQrCamera
            : 0);
        int photoCameraId = ((AquaMai.AppConfig.CustomCameraId.PhotoCamera < WebCamTexture.devices.Length)
            ? AquaMai.AppConfig.CustomCameraId.PhotoCamera
            : 0);
        int chimeQrCameraId= ((AquaMai.AppConfig.CustomCameraId.ChimeCamera < WebCamTexture.devices.Length)
            ? AquaMai.AppConfig.CustomCameraId.ChimeCamera
            : 0);

        switch (GameInfo.GetGameId())
        {
            case "SDGB":
                webcamtex[chimeQrCameraId] = new WebCamTexture(WebCamTexture.devices[chimeQrCameraId].name, _qrCameraParam.Width, _qrCameraParam.Height, _qrCameraParam.Fps);
                DeviceId[0] = chimeQrCameraId;
                DeviceId[1] = photoCameraId;
                break;
            case "SDEZ":
                webcamtex[leftQrCameraId] = new WebCamTexture(WebCamTexture.devices[leftQrCameraId].name, _qrCameraParam.Width, _qrCameraParam.Height, _qrCameraParam.Fps);
                webcamtex[rightQrCameraId] = new WebCamTexture(WebCamTexture.devices[rightQrCameraId].name, _qrCameraParam.Width, _qrCameraParam.Height, _qrCameraParam.Fps);
                DeviceId[0] = leftQrCameraId;
                DeviceId[1] = rightQrCameraId;
                DeviceId[2] = photoCameraId;
                break;
            default:
                DeviceId[0] = photoCameraId;
                break;
        }
        webcamtex[photoCameraId] = new WebCamTexture(WebCamTexture.devices[photoCameraId].name, _gameCameraParam.Width, _gameCameraParam.Height, _gameCameraParam.Fps);
        AccessTools.Field(typeof(CameraManager), "_webcamtex").SetValue(__instance, webcamtex);
        
        __instance.isAvailableCamera = new bool[webcamtex.Length];
        __instance.cameraProcMode = new CameraManager.CameraProcEnum[webcamtex.Length];
        
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