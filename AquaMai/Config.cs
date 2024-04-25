using System.Diagnostics.CodeAnalysis;

namespace AquaMai
{
    [SuppressMessage("ReSharper", "ClassNeverInstantiated.Global")]
    public class Config
    {
        public UXConfig UX { get; set; }
        public CheatConfig Cheat { get; set; }
        public PerformanceConfig Performance { get; set; }

        public class CheatConfig
        {
            public bool TicketUnlock { get; set; }
        }

        public class UXConfig
        {
            public bool SkipWarningScreen { get; set; }
            public bool SinglePlayer { get; set; }
            public bool SkipToMusicSelection { get; set; }
            public bool LoadJacketPng { get; set; }
            public bool LoadAssetBundleWithoutManifest { get; set; }
            public bool QuickSkip { get; set; }
            public string CustomVersionString { get; set; }
        }
        
        public class PerformanceConfig
        {
            public bool ImproveLoadSpeed { get; set; }
        }
    }
}
