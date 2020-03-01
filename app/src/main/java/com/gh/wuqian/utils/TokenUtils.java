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

package com.gh.wuqian.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.tencent.mmkv.MMKV;
import com.umeng.analytics.MobclickAgent;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.gh.wuqian.R;
import com.gh.wuqian.activity.LoginActivity;
import com.gh.wuqian.activity.MainActivity;
import com.xuexiang.xutil.app.ActivityUtils;
import com.xuexiang.xutil.common.StringUtils;
import com.yarolegovich.slidingrootnav.SlidingRootNavLayout;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Token管理工具
 *
 * @author xuexiang
 * @since 2019-11-17 22:37
 */
public final class TokenUtils {

    private static String TAG = "TokenUtils";
    private static String sToken;

    private static final String KEY_TOKEN = "com.xuexiang.xuidemo.utils.KEY_TOKEN";
    private static final String KEY_USER = "com.xuexiang.xuidemo.utils.KEY_USER";
    private static final String KEY_HIS_FAG = "com.xuexiang.xuidemo.utils.KEY_HIS_FAG";

    private TokenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化Token信息
     */
    public static void init(Context context) {
        MMKV.initialize(context);
        sToken = MMKV.defaultMMKV().decodeString(KEY_TOKEN, "");
    }

    public static void setToken(String token) {
        sToken = token;
        MMKV.defaultMMKV().putString(KEY_TOKEN, token);
    }

    public static void clearToken() {
        sToken = null;
        MMKV.defaultMMKV().remove(KEY_TOKEN);
    }

    public static boolean hasToken() {
        return MMKV.defaultMMKV().containsKey(KEY_TOKEN);
    }
    public static void setUserInfo(String userJson) {
        MMKV.defaultMMKV().putString(KEY_USER, userJson);
    }
    public static String getUserInfo() {
        return MMKV.defaultMMKV().getString(KEY_USER,"");
    }

    public static String getHistoryFlag(){
        return MMKV.defaultMMKV().getString(KEY_HIS_FAG, "");
    }
    public static void clearHistoryFlag(){
        MMKV.defaultMMKV().remove(KEY_HIS_FAG);
    }
    public static void addHistoryFlag(String tag){
        String flags = getHistoryFlag();
        String [] flagsArr = flags.split(",");
        for (int i=0;i<flagsArr.length;i++){
            if(flagsArr[i].equals(tag)){
                return ;
            }
        }

        if(flags.equals("")){
            flags = tag;
        }else{
            flags += ","+tag;
        }
        MMKV.defaultMMKV().putString(KEY_HIS_FAG, flags);
    }

    /**
     * 处理登录成功的事件
     *
     * @param token  账户信息token
     * @param userJson 用戶信息
     * @return
     */
    public static boolean handleLoginSuccess(String token,String userJson) {
        if (!StringUtils.isEmpty(token)) {
            XToastUtils.success("登录成功！");
            MobclickAgent.onProfileSignIn("github", token);

            setUserInfo(userJson);
            setToken(token);
            return true;
        } else {
            XToastUtils.error("登录失败！");
            return false;
        }
    }
    public static String getToken(){
        return MMKV.defaultMMKV().getString(KEY_TOKEN,"");
    }
    /**
     * 更新处理左上角
     *
     */
    public static void handleUserInfo(SlidingRootNavLayout layout, MainActivity mainActivity) {
        String userJson = getUserInfo();
        Log.e(TAG,"userJson========"+userJson);
        if(userJson==""){
            return ;
        }
        JSONObject userJsonObject = JSONObject.parseObject(userJson);

        //Log.e(TAG,"isavatar========"+userJsonObject.containsKey("avatar"));

        if(userJsonObject.containsKey("bindName")){
            String bindName = userJsonObject.getString("bindName");
            TextView tv_name = layout.findViewById(R.id.tv_name);
            tv_name.setText(bindName);
        }
        if(userJsonObject.containsKey("email")) {
            String email = userJsonObject.getString("email");
            TextView tv_email = layout.findViewById(R.id.tv_email);
            tv_email.setText(email);
        }
        if(userJsonObject.containsKey("avatar")) {
            String avatar = userJsonObject.getString("avatar");
            //Log.e(TAG,"avatar========"+avatar);
            RadiusImageView iv_avatar = layout.findViewById(R.id.iv_avatar);

            final Bitmap bitmap = ImageUtils.getPhotoFromStorage(getToken());
            if (bitmap != null) {
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("加载用户头像", "本地加载头像");
                        Glide.with(mainActivity).load(bitmap).into(iv_avatar);
                    }
                });
            }else{
                // 从服务器请求头像数据
                asyncGet(avatar,mainActivity,iv_avatar);
            }
            //iv_avatar.setImageURI(Uri.fromFile(new File(avatar)));
        }
    }
    public static void asyncGet(String imgUrl, MainActivity mainActivity, ImageView mIvPhoto) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().get()
                .url(imgUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        byte[] bytes = response.body().bytes();
                        final Bitmap bitmap = ImageUtils.getBitmapFromByte(bytes, 70, 70);
                        ImageUtils.savePhotoToStorage(bitmap, getToken());
                        if (mainActivity != null) {
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Glide.with(mainActivity).load(bitmap).into(mIvPhoto);
                                }
                            });
                            Log.i("加载用户头像", "从服务器加载头像");
                        }
                    }
                }
            }
        });
    }
    /**
     * 处理登出的事件
     */
    public static void handleLogoutSuccess() {
        MobclickAgent.onProfileSignOff();
        //登出时，清除账号信息
        clearToken();
        XToastUtils.success("登出成功！");
        //跳转到登录页
        ActivityUtils.startActivity(LoginActivity.class);
    }


}
