using System.Diagnostics.CodeAnalysis;

namespace AquaMai
{
    [SuppressMessage("ReSharper", "ClassNeverInstantiated.Global")]
    public class Config
    {
        public UXConfig UX { get; set; }
        
        public class UXConfig
        {
            public bool SkipWarningScreen { get; set; }
        }
    }
}