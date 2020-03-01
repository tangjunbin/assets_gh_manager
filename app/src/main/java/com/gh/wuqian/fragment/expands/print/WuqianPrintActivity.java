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

package com.gh.wuqian.fragment.expands.print;

import android.app.Dialog;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.gh.wuqian.utils.XToastUtils;
import com.gh.wuqian.utils.loadDialogUtils;
import com.gprinter.command.EscCommand;
import com.gprinter.command.LabelCommand;
import com.gh.wuqian.fragment.expands.print.gpprint.DeviceConnFactoryManager;
import com.gh.wuqian.fragment.expands.print.gpprint.ThreadPool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class WuqianPrintActivity {
    private final String	TAG	= "WuqianPrint";
    private int		id = 0;
    //蓝牙地址
    private final String macaddress = "DC:1D:30:4B:17:BB";
    private ThreadPool		threadPool;
    public static Dialog mDialog;
    /**
     * 打印类型
     */
    public enum PrintType {
        /**
         * 标签
         */
        LABEL,
        /**
         * 默认(修改主题）
         */
        DEFAULT_Custom,
        /**
         * 远程
         */
        REMOTE,
        /**
         * 自定义
         */
        CUSTOM,
    }

    public WuqianPrintActivity(){
        connBlueTooth();
    }
    /**
     * 打印标签
     */
    public void sendLabel(String params, PrintInterfaceCallback callbackJsWeb) throws JSONException {

        LabelCommand tsc = new LabelCommand();
        try {
            JSONObject paramsJson = new JSONObject(params);
            JSONObject size = paramsJson.getJSONObject("size");
            Integer width = size.getInt("width");
            Integer height = size.getInt("height");
            Integer gap = paramsJson.getInt("gap");

            JSONArray items = paramsJson.getJSONArray("items");

            for(int i=0;i<items.length();i++){
                JSONArray itemList = (JSONArray)items.get(i);

                if (width>0 && height>0) {
                    /* ??????????????? */
                    tsc.addSize( width, height );
                }
                //response.send(size);
                //response.send("size:"+width+"xxxxx"+height);

                /* ???????????????????????????0 */
                tsc.addGap( gap );
                /* ?????? */
                tsc.addDirection( LabelCommand.DIRECTION.FORWARD, LabelCommand.MIRROR.NORMAL );
                /* ???Response?????????? */
                tsc.addQueryPrinterStatus( LabelCommand.RESPONSE_MODE.ON );
                /* ?????? */
                tsc.addReference( 0, 0 );
                /* ?????? */
                tsc.addTear( EscCommand.ENABLE.ON );
                /* ??????? */
                tsc.addCls();

                for(int k=0;k<itemList.length();k++){
                    JSONObject itemLabel = (JSONObject)itemList.get(k);
                    Integer x = itemLabel.getInt("x");
                    Integer y = itemLabel.getInt("y");
                    String type = itemLabel.getString("type");
                    //
                    if(type.equalsIgnoreCase("text")){
                        String textC = itemLabel.getString("value");

                        /* ?????? */
                        tsc.addText( x, y, LabelCommand.FONTTYPE.SIMPLIFIED_CHINESE,
                                LabelCommand.ROTATION.ROTATION_0,
                                LabelCommand.FONTMUL.MUL_1,
                                LabelCommand.FONTMUL.MUL_1,
                                textC
                        );
                    }
                    if(type == "image"){
                        /* ???? */
                        //Bitmap b = BitmapFactory.decodeResource( getResources(), R.drawable.gprinter );
                        //tsc.addBitmap( x, y, LabelCommand.BITMAP_MODE.OVERWRITE, 80, b );
                    }
                    if(type.equalsIgnoreCase("QRCode")){

                        Integer celW = itemLabel.getInt("cellwidth");
                        String data = itemLabel.getString("data");
                        tsc.addQRCode( x, y, LabelCommand.EEC.LEVEL_L, celW, LabelCommand.ROTATION.ROTATION_0, data );
                    }
                    if(type.equalsIgnoreCase("Barcode")){
                        Integer heigth = itemLabel.getInt("heigth");
                        String content = itemLabel.getString("content");
                        /* ?????? */
                        tsc.add1DBarcode( x, y, LabelCommand.BARCODETYPE.CODE128, heigth, LabelCommand.READABEL.EANBEL, LabelCommand.ROTATION.ROTATION_0, content );
                    }
                }
                //break;//????????
            }

        }catch (Exception e){
            callbackJsWeb.webprintDoc(44,"Json 解析异常");
            XToastUtils.toast("Json 解析异常", Toast.LENGTH_LONG);
            Log.e("error","Json 解析异常");
        }


        /* ???? */
        tsc.addPrint( 1, 1 );
        /* ????? ???? */

        tsc.addSound( 2, 100 );
        tsc.addCashdrwer( LabelCommand.FOOT.F5, 255, 255 );
        Vector<Byte> datas = tsc.getCommand();
        /* ???? */
        if ( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null )
        {
            callbackJsWeb.webprintDoc(45,"sendLabel: 打印机未连接");
            XToastUtils.toast("sendLabel: 打印机未连接", Toast.LENGTH_LONG);
            Log.d(TAG, "sendLabel: 打印机未连接");
            return;
        }
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately( datas );
        callbackJsWeb.webprintDoc(0,"打印成功");
    }

    /**
     * 连接蓝牙
     */
    public void connBlueTooth(){

        if(DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null &&DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].mPort != null ){
            return ;
        }
        new DeviceConnFactoryManager.Build()
                .setId( id )
                /* ?????? */
                .setConnMethod( DeviceConnFactoryManager.CONN_METHOD.BLUETOOTH )
                /* ???????mac?? */
                .setMacAddress( macaddress )
                .build();
        /* ???? */
        Log.d(TAG, "onActivityResult: "+id);
        Log.d(TAG, "onActivityResult: mac"+macaddress);
        threadPool = ThreadPool.getInstantiation();
        threadPool.addTask( new Runnable()
        {
            @Override
            public void run()
            {
                DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
            }
        } );
        Log.d(TAG, "连接蓝牙成功！！！");
    }
    static public void sendTest(PrintType printType, FragmentActivity activity,String jsonparam,PrintInterfaceCallback callbackJsWeb) throws JSONException {

        mDialog = loadDialogUtils.createLoadingDialog(activity, "加载中...");
        WuqianPrintActivity wuqianPrint = new WuqianPrintActivity();
        switch (printType){
            case LABEL:
                String jsonString = jsonparam;
                //"{\"name\":\"模板2\",\"size\":{\"width\":50,\"height\":30},\"gap\":2,\"count\":1,\"items\":[[{\"type\":\"text\",\"x\":40,\"y\":20,\"value\":\"资产编号:\"},{\"type\":\"text\",\"x\":40,\"y\":55,\"value\":\"ZC0000000003\"},{\"type\":\"text\",\"x\":40,\"y\":90,\"value\":\"资产名称:\"},{\"type\":\"text\",\"x\":40,\"y\":125,\"value\":\"诺基亚\"},{\"type\":\"text\",\"x\":40,\"y\":160,\"value\":\"资产分类:\"},{\"type\":\"text\",\"x\":40,\"y\":195,\"value\":\"土地、房屋及构筑物\"},{\"type\":\"QRCode\",\"x\":289,\"y\":120,\"cellwidth\":3,\"data\":\"http://183.136.147.50:8888/assets/public/index.php/assets/index/detail?no=ZC0000000003\"}]]}";
                wuqianPrint.sendLabel(jsonString,callbackJsWeb);
                break;

        }
        loadDialogUtils.closeDialog(mDialog);
    }

    public interface PrintInterfaceCallback{
        void webprintDoc(int resultCode,String message) throws JSONException;
    }
}
