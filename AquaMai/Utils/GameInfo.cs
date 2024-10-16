using System;
using System.Reflection;
using System.Runtime.CompilerServices;
using MAI2System;

namespace AquaMai.Utils;

public class GameInfo
{
    public static uint GetGameVersion()
    {
        return (uint) typeof(ConstParameter).GetField("NowGameVersion", BindingFlags.Public | BindingFlags.Static | BindingFlags.FlattenHierarchy).GetValue(null);
    }
    
    public static string GetGameId()
    {
        return typeof(ConstParameter).GetField("GameIDStr", BindingFlags.Public | BindingFlags.Static | BindingFlags.FlattenHierarchy).GetValue(null) as string;
    }
}