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

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.gh.wuqian.R;

import me.samlss.broccoli.PlaceholderParameter;

/**
 * 占位控件
 *
 * @author xuexiang
 * @since 2019/4/7 下午1:02
 */
public class PlaceholderHelper {

    private PlaceholderHelper() {
        throw new UnsupportedOperationException("Can not be instantiated.");
    }

    public static PlaceholderParameter getParameter(View view) {
        if (view == null) {
            return null;
        }
        return getParameter(view.getId(), view);
    }

    private static PlaceholderParameter getParameter(int viewId, View view) {
        int placeHolderColor = Color.parseColor("#DDDDDD");
        switch (viewId) {
            case R.id.iv_avatar:
                return getOvalPlaceholder(view, placeHolderColor);

            case R.id.tv_user_name:
                return getRectanglePlaceholder(view, placeHolderColor, 5);


            case R.id.tv_tag:
                return getRectanglePlaceholder(view, placeHolderColor, 5);

            case R.id.tv_title:
                Animation titleAnimation = new ScaleAnimation(0.3f, 1, 1, 1);
                titleAnimation.setDuration(600);
                return getAnimationRectanglePlaceholder(view, titleAnimation, placeHolderColor, 5);

            case R.id.tv_summary:
                Animation summaryAnimation = new ScaleAnimation(0.4f, 1, 1, 1);
                summaryAnimation.setDuration(800);
                return getAnimationRectanglePlaceholder(view, summaryAnimation, placeHolderColor, 5);

            case R.id.iv_image:
                Animation imageAnimation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                imageAnimation.setDuration(800);
                return getAnimationOvalPlaceholder(view, imageAnimation, placeHolderColor);

            case R.id.iv_praise:
                return getOvalPlaceholder(view, placeHolderColor);

            case R.id.tv_praise:
                return getRectanglePlaceholder(view, placeHolderColor, 5);

            case R.id.iv_comment:
                return getOvalPlaceholder(view, placeHolderColor);

            case R.id.tv_comment:
                return getRectanglePlaceholder(view, placeHolderColor, 5);

            case R.id.tv_read:
                return getRectanglePlaceholder(view, placeHolderColor, 5);
            default:
                break;

        }
        return null;
    }

    /**
     * 圆形的动画占位
     * @param view
     * @param animation
     * @param placeHolderColor
     * @return
     */
    private static PlaceholderParameter getAnimationOvalPlaceholder(View view, Animation animation, int placeHolderColor) {
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        return new PlaceholderParameter.Builder()
                .setView(view)
                .setAnimation(animation)
                .setDrawable(DrawableUtils.createOvalDrawable(placeHolderColor))
                .build();
    }

    /**
     * 矩形的动画占位
     * @param view
     * @param animation
     * @param placeHolderColor
     * @param cornerRadius
     * @return
     */
    private static PlaceholderParameter getAnimationRectanglePlaceholder(View view, Animation animation, int placeHolderColor, int cornerRadius) {
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        return new PlaceholderParameter.Builder()
                .setView(view)
                .setAnimation(animation)
                .setDrawable(DrawableUtils.createRectangleDrawable(placeHolderColor, cornerRadius))
                .build();
    }

    /**
     * 圆形的占位
     *
     * @param view
     * @param placeHolderColor
     * @return
     */
    private static PlaceholderParameter getOvalPlaceholder(View view, int placeHolderColor) {
        return getPlaceholder(view, DrawableUtils.createOvalDrawable(placeHolderColor));
    }

    /**
     * 矩形的占位
     *
     * @param view
     * @param placeHolderColor
     * @param cornerRadius
     * @return
     */
    private static PlaceholderParameter getRectanglePlaceholder(View view, int placeHolderColor, int cornerRadius) {
        return getPlaceholder(view, DrawableUtils.createRectangleDrawable(placeHolderColor, cornerRadius));
    }

    private static PlaceholderParameter getPlaceholder(View view, GradientDrawable ovalDrawable) {
        return new PlaceholderParameter.Builder()
                .setView(view)
                .setDrawable(ovalDrawable)
                .build();
    }
}
