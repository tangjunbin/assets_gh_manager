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

package com.gh.wuqian.fragment.components.progress;

import android.animation.ValueAnimator;
import android.widget.ProgressBar;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.Animators;
import com.xuexiang.xpage.annotation.Page;
import com.gh.wuqian.R;

import butterknife.BindViews;

/**
 * @author xuexiang
 * @date 2017/12/7 下午6:05
 */
@Page(name = "MaterialProgressBar\n进度条")
public class MaterialProgressBarFragment extends BaseFragment {
    @BindViews({
            R.id.determinate_circular_large_progress,
            R.id.determinate_circular_progress,
            R.id.determinate_circular_small_progress
    })
    ProgressBar[] mDeterminateCircularProgressBars;

    private ValueAnimator mDeterminateCircularProgressAnimator;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_material_progress_bar;
    }

    @Override
    protected void initViews() {
        mDeterminateCircularProgressAnimator =
                Animators.makeDeterminateCircularPrimaryProgressAnimator(
                        mDeterminateCircularProgressBars);

        mDeterminateCircularProgressAnimator.start();
    }

    @Override
    protected void initListeners() {


    }

    @Override
    public void onDestroyView() {
        mDeterminateCircularProgressAnimator.end();
        super.onDestroyView();
    }

}
