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

import android.annotation.TargetApi;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

/**
 * @author xuexiang
 * @since 2019/4/7 下午12:57
 */
public class DrawableUtils {

    private DrawableUtils() {
        throw new UnsupportedOperationException("Can not be instantiated.");
    }

    /**
     * 矩形
     * @param color
     * @param cornerRadius
     * @return
     */
    public static GradientDrawable createRectangleDrawable(int color, float cornerRadius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    /**
     * 矩形
     * @param colors
     * @param cornerRadius
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static GradientDrawable createRectangleDrawable(int[] colors, float cornerRadius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColors(colors);
        return gradientDrawable;
    }

    /**
     * 圆形
     * @param color
     * @return
     */
    public static GradientDrawable createOvalDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    /**
     * 圆形
     * @param colors
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static GradientDrawable createOvalDrawable(int[] colors) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColors(colors);
        return gradientDrawable;
    }
}
