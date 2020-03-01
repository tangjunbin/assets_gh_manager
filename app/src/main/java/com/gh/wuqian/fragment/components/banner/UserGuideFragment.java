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

package com.gh.wuqian.fragment.components.banner;

import androidx.viewpager.widget.ViewPager;

import com.gh.wuqian.DemoDataProvider;
import com.gh.wuqian.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.banner.anim.select.ZoomInEnter;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleGuideBanner;
import com.gh.wuqian.R;

import static com.gh.wuqian.fragment.components.banner.UserGuideFragment.POSITION;


/**
 * 可使用Applink打开:https://xuexiangjys.club/xpage/transfer?pageName=UserGuide&position=2
 *
 * @author xuexiang
 * @since 2019-07-06 10:21
 */
@Page(name = "UserGuide", params = {POSITION})
public class UserGuideFragment extends BaseFragment {

    public final static String POSITION = "position";

    private Class<? extends ViewPager.PageTransformer> transformerClass;

    @AutoWired
    int position;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_guide;
    }

    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);

        if (position >= 0 && position <= DemoDataProvider.transformers.length - 1) {
            transformerClass = DemoDataProvider.transformers[position];
        } else {
            transformerClass = DemoDataProvider.transformers[0];
        }
    }

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected void initViews() {
        sgb();
    }


    @Override
    protected void initListeners() {

    }


    private void sgb() {
        SimpleGuideBanner sgb = findViewById(R.id.sgb);

        sgb
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .setTransformerClass(transformerClass)
                .barPadding(0, 10, 0, 10)
                .setSource(DemoDataProvider.getUsertGuides())
                .startScroll();

        sgb.setOnJumpClickListener(new SimpleGuideBanner.OnJumpClickListener() {
            @Override
            public void onJumpClick() {
                popToBack();
            }
        });
    }
}
