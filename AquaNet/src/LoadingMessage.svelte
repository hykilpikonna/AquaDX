<div class="pleaseWait">
    <div class="loadingCircleDiv"><div class="loadingCircle">
        <div></div>
        </div></div>
        <style type="text/css">
        .pleaseWait {
            margin:auto;
        }

        @keyframes loadingCircle {
          0% { transform: rotate(0deg) }
          50% { transform: rotate(180deg) }
          100% { transform: rotate(360deg) }
        }
        .loadingCircle div {
          position: absolute;
          animation: loadingCircle 1s linear infinite;
          width: 82px;
          height: 82px;
          top: 9px;
          left: 9px;
          border-radius: 50%;
          box-shadow: 0 4.1px 0 0 #93dbe9;
          transform-origin: 41px 43.05px;
        }
        .loadingCircleDiv {
          width: 94px;
          height: 94px;
          display: inline-block;
          overflow: hidden;
          background: none;
        }
        .loadingCircle {
          width: 100%;
          height: 100%;
          position: relative;
          transform: translateZ(0) scale(0.94);
          backface-visibility: hidden;
          transform-origin: 0 0;
        }
        .loadingCircle div { box-sizing: content-box; }
        </style>
<p>Please Wait...</p>
</div>

