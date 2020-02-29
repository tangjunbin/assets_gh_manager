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

package com.wuqian.xui.widget.activity;

import android.app.Activity;
import android.drm.DrmStore;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.xuexiang.xui.utils.Utils;

public abstract class BaseSplashActivity extends Activity {
    /**
     * 默认启动页过渡时间
     */
    private static final int DEFAULT_SPLASH_DURATION_MILLIS = 2000;

    protected LinearLayout mWelcomeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initSplashView(getSplashImgResId());
        onCreateActivity();
    }

    private void initView() {
        mWelcomeLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWelcomeLayout.setLayoutParams(params);
        mWelcomeLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mWelcomeLayout);
    }

    /**
     * 初始化启动界面
     *
     * @param splashImgResId 背景资源图片资源ID
     */
    protected void initSplashView(int splashImgResId) {
        if (splashImgResId != 0) {
            Utils.setBackground(this, mWelcomeLayout, splashImgResId);
        }
    }

    /**
     * 初始化启动界面背景图片
     *
     * @return 背景图片资源ID
     */
    protected int getSplashImgResId() {
        return 0;
    }

    /**
     * activity启动后的初始化
     */
    protected abstract void onCreateActivity();

    /**
     * 启动页结束后的动作
     */
    protected abstract void onSplashFinished();

    /**
     * @return 启动页持续的时间
     */
    protected long getSplashDurationMillis() {
        return DEFAULT_SPLASH_DURATION_MILLIS;
    }

    /**
     * 开启过渡
     *
     * @param enableAlphaAnim 是否启用渐近动画
     */
    protected void startSplash(boolean enableAlphaAnim) {
        if (enableAlphaAnim) {
            startSplashAnim(new AlphaAnimation(0.2F, 1.0F));
        } else {
            startSplashAnim(new AlphaAnimation(1.0F, 1.0F));
        }
    }

    /**
     * 开启引导过渡动画
     *
     * @param anim
     */
    private void startSplashAnim(Animation anim) {
        Utils.checkNull(anim, "Splash Animation can not be null");
        anim.setDuration(getSplashDurationMillis());
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onSplashFinished();
            }
        });
        mWelcomeLayout.startAnimation(anim);
    }

    @Override
    protected void onDestroy() {
        Utils.recycleBackground(mWelcomeLayout);
        super.onDestroy();
    }
}
