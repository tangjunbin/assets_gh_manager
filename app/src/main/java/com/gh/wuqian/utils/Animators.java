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

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

public class Animators {

    private Animators() {}

    public static ValueAnimator makeDeterminateCircularPrimaryProgressAnimator(
            final ProgressBar[] progressBars) {
        ValueAnimator animator = ValueAnimator.ofInt(0, 150);
        animator.setDuration(6000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int value = (int) animator.getAnimatedValue();
                        for (ProgressBar progressBar : progressBars) {
                            progressBar.setProgress(value);
                        }
                    }
                });
        return animator;
    }

    public static ValueAnimator makeDeterminateCircularPrimaryAndSecondaryProgressAnimator(
            final ProgressBar[] progressBars) {
        ValueAnimator animator = makeDeterminateCircularPrimaryProgressAnimator(progressBars);
        animator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int value = Math.round(1.25f * (int) animator.getAnimatedValue());
                        for (ProgressBar progressBar : progressBars) {
                            progressBar.setSecondaryProgress(value);
                        }
                    }
                });
        return animator;
    }
}
