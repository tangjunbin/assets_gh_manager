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

import android.view.View;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.progress.ratingbar.RatingBar;
import com.xuexiang.xui.widget.progress.ratingbar.RotationRatingBar;
import com.xuexiang.xui.widget.progress.ratingbar.ScaleRatingBar;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xuexiang
 * @since 2019/3/26 下午11:24
 */
@Page(name = "RatingBar\n星级评分控件")
public class RatingBarFragment extends BaseFragment {
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.scale_rating_bar)
    ScaleRatingBar scaleRatingBar;
    @BindView(R.id.rrb_custom)
    RotationRatingBar rrbCustom;

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ratingbar;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        ratingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(RatingBar ratingBar, float rating) {
                XToastUtils.toast("当前星级：" + rating);
            }
        });
        scaleRatingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(RatingBar ratingBar, float rating) {

            }
        });
    }

    @SingleClick
    @OnClick(R.id.btn_add_rating)
    public void onViewClicked(View view) {

        float currentRating = ratingBar.getRating();
        ratingBar.setRating(currentRating + 0.25f);

        currentRating = scaleRatingBar.getRating();
        scaleRatingBar.setRating(currentRating + 0.25f);

        currentRating = rrbCustom.getRating();
        rrbCustom.setRating(currentRating + 0.25f);

    }
}
