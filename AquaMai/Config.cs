using System.Diagnostics.CodeAnalysis;
using Tomlet.Attributes;

namespace AquaMai
{
    [SuppressMessage("ReSharper", "ClassNeverInstantiated.Global")]
    public class Config
    {
        public UXConfig UX { get; set; } = new();
        public CheatConfig Cheat { get; set; } = new();
        public FixConfig Fix { get; set; } = new();
        public UtilsConfig Utils { get; set; } = new();
        public TimeSavingConfig TimeSaving { get; set; } = new();
        public TouchSensitivityConfig TouchSensitivity { get; set; } = new();

        public class CheatConfig
        {
            public bool TicketUnlock { get; set; }
            public bool MapUnlock { get; set; }
            public bool UnlockUtage { get; set; }
        }

        public class UXConfig
        {
            public bool SinglePlayer { get; set; }
            public bool LoadAssetsPng { get; set; }
            public bool LoadJacketPng { get; set; }
            public bool LoadAssetBundleWithoutManifest { get; set; }
            public bool QuickSkip { get; set; }
            public bool RandomBgm { get; set; }
            public bool DemoMaster { get; set; }
            public bool ExtendTimer { get; set; }
            public bool ImmediateSave { get; set; }
            public bool LoadLocalBga { get; set; }
            public bool TestProof { get; set; }
            public bool HideSelfMadeCharts { get; set; }
            public bool SelectionDetail { get; set; }
            public string CustomVersionString { get; set; } = "";
            public string CustomPlaceName { get; set; } = "";
            public string ExecOnIdle { get; set; } = "";
            public string ExecOnEntry { get; set; } = "";
        }

        public class FixConfig
        {
            public bool SkipVersionCheck { get; set; }
            public bool RemoveEncryption { get; set; }
            public bool ForceAsServer { get; set; } = true;
            public bool ForceFreePlay { get; set; } = true;
            public bool ForcePaidPlay { get; set; }
            public int ExtendNotesPool { get; set; }
        }

        public class UtilsConfig
        {
            public bool LogUserId { get; set; }
            public float JudgeAdjustA { get; set; }
            public float JudgeAdjustB { get; set; }
            public int TouchDelay { get; set; }
            public bool Windowed { get; set; }
            public int Width { get; set; }
            public int Height { get; set; }
            public bool PractiseMode { get; set; }
        }

        public class TimeSavingConfig
        {
            public bool SkipWarningScreen { get; set; }
            public bool ImproveLoadSpeed { get; set; }
            public bool SkipToMusicSelection { get; set; }
            public bool SkipEventInfo { get; set; }
            public bool IWontTapOrSlideVigorously { get; set; }
            public bool SkipGameOverScreen { get; set; }
            public bool SkipTrackStart { get; set; }
        }

        public class TouchSensitivityConfig
        {
            public bool Enable { get; set; }
            public byte A1 { get; set; } = 40;
            public byte A2 { get; set; } = 40;
            public byte A3 { get; set; } = 40;
            public byte A4 { get; set; } = 40;
            public byte A5 { get; set; } = 40;
            public byte A6 { get; set; } = 40;
            public byte A7 { get; set; } = 40;
            public byte A8 { get; set; } = 40;
            public byte B1 { get; set; } = 20;
            public byte B2 { get; set; } = 20;
            public byte B3 { get; set; } = 20;
            public byte B4 { get; set; } = 20;
            public byte B5 { get; set; } = 20;
            public byte B6 { get; set; } = 20;
            public byte B7 { get; set; } = 20;
            public byte B8 { get; set; } = 20;
            public byte C1 { get; set; } = 20;
            public byte C2 { get; set; } = 20;
            public byte D1 { get; set; } = 20;
            public byte D2 { get; set; } = 20;
            public byte D3 { get; set; } = 20;
            public byte D4 { get; set; } = 20;
            public byte D5 { get; set; } = 20;
            public byte D6 { get; set; } = 20;
            public byte D7 { get; set; } = 20;
            public byte D8 { get; set; } = 20;
            public byte E1 { get; set; } = 20;
            public byte E2 { get; set; } = 20;
            public byte E3 { get; set; } = 20;
            public byte E4 { get; set; } = 20;
            public byte E5 { get; set; } = 20;
            public byte E6 { get; set; } = 20;
            public byte E7 { get; set; } = 20;
            public byte E8 { get; set; } = 20;
        }
    }
}
