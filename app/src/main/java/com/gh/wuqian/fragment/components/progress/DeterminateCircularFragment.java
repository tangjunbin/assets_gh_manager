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
 * @date 2017/12/8 上午12:14
 */
@Page(name = "环形进度条样式")
public class DeterminateCircularFragment extends BaseFragment {
    @BindViews({
            R.id.normal_progress,
            R.id.tinted_normal_progress,
            R.id.dynamic_progress,
            R.id.tinted_dynamic_progress
    })
    ProgressBar[] mPrimaryProgressBars;
    @BindViews({
            R.id.normal_secondary_progress,
            R.id.normal_background_progress,
            R.id.tinted_normal_secondary_progress,
            R.id.tinted_normal_background_progress,
            R.id.dynamic_secondary_progress,
            R.id.dynamic_background_progress,
            R.id.tinted_dynamic_secondary_progress,
            R.id.tinted_dynamic_background_progress
    })
    ProgressBar[] mPrimaryAndSecondaryProgressBars;

    private ValueAnimator mPrimaryProgressAnimator;
    private ValueAnimator mPrimaryAndSecondaryProgressAnimator;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_determinatecircular;
    }

    @Override
    protected void initViews() {
        mPrimaryProgressAnimator =
                Animators.makeDeterminateCircularPrimaryProgressAnimator(mPrimaryProgressBars);
        mPrimaryAndSecondaryProgressAnimator =
                Animators.makeDeterminateCircularPrimaryAndSecondaryProgressAnimator(
                        mPrimaryAndSecondaryProgressBars);

        mPrimaryProgressAnimator.start();
        mPrimaryAndSecondaryProgressAnimator.start();
    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onDestroyView() {
        mPrimaryProgressAnimator.end();
        mPrimaryAndSecondaryProgressAnimator.end();
        super.onDestroyView();
    }
}
