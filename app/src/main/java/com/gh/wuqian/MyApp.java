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

package com.gh.wuqian;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.google.android.cameraview.CameraView;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.mikepenz.iconics.Iconics;
import com.gh.wuqian.BuildConfig;
import com.xuexiang.xui.XUI;
import com.gh.wuqian.utils.sdkinit.AutoCameraStrategy;
import com.gh.wuqian.utils.sdkinit.BuglyInit;
import com.gh.wuqian.utils.sdkinit.TbsInit;
import com.gh.wuqian.utils.sdkinit.UMengInit;
import com.gh.wuqian.utils.sdkinit.XBasicLibInit;
import com.gh.wuqian.utils.sdkinit.XUpdateInit;
import com.gh.wuqian.widget.iconfont.XUIIconFont;


/**
 * 应用初始化
 *
 * @author xuexiang
 * @since 2018/11/7 下午1:12
 */
public class MyApp extends Application {
    private static Context mContext;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决4.x运行崩溃的问题
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initUI();
        //初始化基础库
        XBasicLibInit.init(this);
        //三方SDK初始化
        XUpdateInit.init(this);
        TbsInit.init(this);
        //运营统计数据运行时不初始化
        if (!MyApp.isDebug()) {
            UMengInit.init(this);
            BuglyInit.init(this);
        }
    }
    public static Context getContext() {
        return mContext;
    }
    /**
     * 初始化XUI 框架
     */
    private void initUI() {
        XUI.init(this);
        XUI.debug(MyApp.isDebug());
//        //设置默认字体为华文行楷
//        XUI.getInstance().initFontStyle("fonts/hwxk.ttf");
        PictureFileUtils.setAppName("xui");

        //字体图标库
        Iconics.init(this);
        //这是自己定义的图标库
        Iconics.registerFont(new XUIIconFont());

        CameraView.setICameraStrategy(new AutoCameraStrategy(1920 * 1080));
    }


    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }



}
