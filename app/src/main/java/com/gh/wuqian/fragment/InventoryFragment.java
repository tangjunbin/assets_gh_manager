/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.gh.wuqian.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.gh.wuqian.activity.MainActivity;
import com.gh.wuqian.utils.Utils;
import com.gh.wuqian.utils.XToastUtils;
import com.just.agentweb.core.AgentWeb;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xqrcode.XQRCode;
import com.xuexiang.xqrcode.util.QRCodeAnalyzeUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.gh.wuqian.R;
import com.gh.wuqian.base.webview.BaseWebViewFragment;
import com.gh.wuqian.fragment.expands.print.WuqianPrintActivity;
import com.gh.wuqian.fragment.expands.qrcode.CustomCaptureActivity;
import com.xuexiang.xutil.app.PathUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;
import static com.gh.wuqian.fragment.expands.print.WuqianPrintActivity.PrintType;

/**
 * 盘点
 *
 * @author 五千
 * @since 2020/02/26 下午2:22
 */
@Page(name = "盘点", anim = CoreAnim.none)
public class InventoryFragment extends BaseWebViewFragment {
    private String TAG="InventoryFragment";
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;

    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    /**
     * 回调函数 String
     */
    private String jsCallbackJAVA="";
    private String jsCallMethodJAVA="";

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_js_webview;
    }
    public MainActivity getContainer() {
        return (MainActivity) getActivity();
    }
    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.setLeftImageResource(R.drawable.ic_action_menu);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            @SingleClick
            public void onClick(View v) {
                getContainer().openMenu();
            }
        });
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.icon_action_about) {
            @Override
            @SingleClick
            public void performAction(View view) {
                openNewPage(AboutFragment.class);
            }
        });
        return titleBar;
    }
    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        mAgentWeb = Utils.createAgentWeb(this, flContainer, "file:///android_asset/jsTest.html");
        //mAgentWeb = Utils.createAgentWeb(this, flContainer, "https://wx.eastabc.com/zlpos/public/index.php/assets/master/index.html");

        //注入接口,供JS调用
        mAgentWeb.getJsInterfaceHolder().addJavaObject("AssetsJSBridge", new InventoryFragment.AndroidInterfaceWeb(mAgentWeb, getActivity(),new WebJsInterfaceCallback(){

            @Override
            public void webQrScand(String method,String jsCallback) {
                qrScand(method,jsCallback);
            }

            @Override
            public void webprintDoc(String method, String jsCallback, com.alibaba.fastjson.JSONObject paramsJSON) {
                try {
                    WuqianPrintActivity.sendTest(PrintType.LABEL,getActivity(),paramsJSON.getString("data"),new WuqianPrintActivity.PrintInterfaceCallback(){
                        @Override
                        public void webprintDoc(int resultCode, String message) throws JSONException {
                            JSONObject resultJSON = new JSONObject();
                            if (resultCode == 0) {
                                String result = message;
                                resultJSON.put("errMsg", method+":ok");
                                resultJSON.put("data", result);

                            } else {
                                resultJSON.put("errMsg", method+":fail");
                                resultJSON.put("errDesc", message);
                                resultJSON.put("data", "");
                            }
                            Log.e(TAG,"resultCode="+resultCode);
                            Log.e(TAG,jsCallback+resultJSON.toString()+")");
                            mAgentWeb.getJsAccessEntrace().quickCallJs(jsCallback+resultJSON.toString()+")");
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));

    }

    /**
     * 唤起扫一扫
     */
    public void qrScand(String method,String jsCallback){
        this.jsCallbackJAVA = jsCallback;//保存jsbackfun
        this.jsCallMethodJAVA = method;//保存方法
        CustomCaptureActivity.start(this, REQUEST_CODE, R.style.XQRCodeTheme_Custom);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理二维码扫描结果
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            //处理扫描结果（在界面上显示）
            try {
                handleScanResult(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //选择系统图片并解析
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                getAnalyzeQRCodeResult(uri);
            }
        }
    }
    @SuppressLint("MissingPermission")
    private void getAnalyzeQRCodeResult(Uri uri) {
        XQRCode.analyzeQRCode(PathUtils.getFilePathByUri(getContext(), uri), new QRCodeAnalyzeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                XToastUtils.toast("解析结果:" + result, Toast.LENGTH_LONG);
            }
            @Override
            public void onAnalyzeFailed() {
                XToastUtils.toast("解析二维码失败", Toast.LENGTH_LONG);
            }
        });
    }
    /**
     * 处理二维码扫描结果
     *
     * @param data
     */
    private void handleScanResult(Intent data) throws JSONException {
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                JSONObject resultJSON = new JSONObject();
                if (bundle.getInt(XQRCode.RESULT_TYPE) == XQRCode.RESULT_SUCCESS) {
                    String result = bundle.getString(XQRCode.RESULT_DATA);
                    //XToastUtils.toast("解析结果:" + result, Toast.LENGTH_LONG);
                    resultJSON.put("errMsg", jsCallMethodJAVA+":ok");
                    resultJSON.put("data", result);

                } else if (bundle.getInt(XQRCode.RESULT_TYPE) == XQRCode.RESULT_FAILED) {
                    XToastUtils.toast("", Toast.LENGTH_LONG);
                    resultJSON.put("errMsg", jsCallMethodJAVA+":fail");
                    resultJSON.put("errDesc", "解析二维码失败");
                    resultJSON.put("data", "");
                }

                mAgentWeb.getJsAccessEntrace().quickCallJs(jsCallbackJAVA+resultJSON.toString()+")");
            }
        }
    }
    /**
     * 注入到JS里的对象接口
     */
    public class AndroidInterfaceWeb {

        private AgentWeb agent;
        private Activity activity;
        private String TAG="AndroidInterfaceWeb";
        private WebJsInterfaceCallback interfaceCallback;
        public AndroidInterfaceWeb(AgentWeb agent, Activity activity,WebJsInterfaceCallback interfaceCallback) {
            this.agent = agent;
            this.activity = activity;
            this.interfaceCallback = interfaceCallback;
        }
        @JavascriptInterface
        public void invoke_native(String method, String params, String callbackfunction) throws JSONException {
            Log.e(TAG,"method="+method+";params="+params+";callbackfunction="+callbackfunction);

            if (!TextUtils.isEmpty(method)) {
                switch (method) {
                    case "scanQRCode":
                        if(interfaceCallback != null){
                            //扫一扫
                            String jsCode = "eval(\"var func = " +InventoryFragment.replaceBlank(callbackfunction)+"\");func.call(this,";
                            interfaceCallback.webQrScand(method,jsCode);
                        }
                        break;
                    case "printDoc":
                        if(interfaceCallback != null){
                            //打印机
                            com.alibaba.fastjson.JSONObject paramsObject =  com.alibaba.fastjson.JSONObject.parseObject(params);
                            String jsCode = "eval(\"var func = " +InventoryFragment.replaceBlank(callbackfunction)+"\");func.call(this,";
                            interfaceCallback.webprintDoc(method,jsCode,paramsObject);
                        }
                        break;
                }
            }
        }

    }

    public interface WebJsInterfaceCallback{
        void webQrScand(String method,String jsCallback);
        void webprintDoc(String method,String jsCallback,com.alibaba.fastjson.JSONObject paramsObject);
    }
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
