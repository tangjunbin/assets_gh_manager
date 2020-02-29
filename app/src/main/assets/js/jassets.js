! function(win, func) {
    "function" == typeof define && (define.amd || define.cmd) ? define(function() {
        return func(win)
    }) : func(win, !0)
}(this, function(win, isDirect) {


    function runAssetsJSBridge(funName, parameter, obj) {
        
        win.AssetsJSBridge ? (AssetsJSBridge_callback.initLoccallback(obj),AssetsJSBridge.invoke_native(funName, parameterSerialize(parameter), (function(a) {
            AssetsJSBridge_callback.do(a)
        }).toString())) : errJs(funName, obj)
    }

    function parameterSerialize(a) {
        return JSON.stringify(a)
    }

    function AssetsJSBridge_callback_class() {
    }
    AssetsJSBridge_callback_class.prototype.do = function( b){
        var d, e, f;
        var obj = this.initLoccallback;
        switch (
        obj = obj || {}, 
        obj._complete && (obj._complete(b), delete obj._complete), 
        d = b.errMsg || "",
          C.debug && alert(JSON.stringify(b)),
           e = d.indexOf(":"), 
           f = d.substring(e + 1)
           ) {
            case "ok":
                obj.success && obj.success(b);
                break;
            case "cancel":
                obj.cancel && obj.cancel(b);
                break;
            default:
                obj.fail && obj.fail(b)
        }
        obj.complete && obj.complete(b)
    }
    AssetsJSBridge_callback_class.prototype.initLoccallback = function(obj){
        this.initLoccallback = obj;
    }

    function errJs(a, b) {
        if (!( b && b.isInnerInvoke)) {
            var c = p[a];
            c && (a = c), b && b._complete && delete b._complete, console.log('"' + a + '",', b || "")
        }
    }
    var o, p, doc, userAgent, platform, isPc, v, isweixin, isAndroid, isIphone, z, A, B, C, D, E, mainJS,initfc;
    if (!win.jWeixin) return doc = win.document,
    userAgent = navigator.userAgent.toLowerCase(),
    platform = navigator.platform.toLowerCase(), 
    isPc = !(!platform.match("mac") && !platform.match("win")), 
    isweixin = -1 != userAgent.indexOf("micromessenger"), 
    isAndroid = -1 != userAgent.indexOf("android"), 
    isIphone = -1 != userAgent.indexOf("iphone") || -1 != userAgent.indexOf("ipad"), 
    z = function() {
        var a = userAgent.match(/micromessenger\/(\d+\.\d+\.\d+)/) || userAgent.match(/micromessenger\/(\d+\.\d+)/);
        return a ? a[1] : ""
    }(), 
    C = {}, 
    D = {
        _completes: []
    }, 
    E = {
        state: 0,
        data: {}
    },initfc = function(){
        win.AssetsJSBridge_callback = new AssetsJSBridge_callback_class()
    },
    mainJS = {
        isDebug:function(){
            C.debug = !0;
        },
        /**
         * 唤起扫一扫
         * @param {*} a 
         */
        scanQRCode: function(a) {
            initfc();
            a = a || {}, runAssetsJSBridge("scanQRCode", {
                needResult: a.needResult || 0,
                scanType: a.scanType || ["qrCode", "barCode"]
            }, function() {
                return a._complete = function(a) {
                    var b, c;
                    isIphone && (b = a.resultStr, b && (c = JSON.parse(b), a.resultStr = c && c.scan_code && c.scan_code.scan_result))
                }, a
            }())
        },
        printDoc: function(a) {
            initfc();
            a = a || {}, runAssetsJSBridge("printDoc", {
                needResult: a.needResult || 0,
                data:a.printData
            }, function() {
                return a._complete = function(a) {
                    var b, c;
                    isIphone && (b = a.resultStr, b && (c = JSON.parse(b), a.resultStr = c && c.scan_code && c.scan_code.scan_result))
                }, a
            }())
        },
    },isDirect && (win.ghAssets = win.jWeixin = mainJS),mainJS
});