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

package com.gh.wuqian.fragment.expands.materialdesign.behavior;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.gh.wuqian.R;
import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xutil.display.Colors;

import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019-05-08 23:15
 */
@Page(name = "CoordinatorLayout + AppBarLayout\n详情页常用组合")
public class ToolbarBehaviorFragment extends BaseFragment {
    @BindView(R.id.appbar_layout_toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapse_layout)
    CollapsingToolbarLayout collapseLayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.fab_scrolling)
    FloatingActionButton fabScrolling;

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_behavior_toolbar;
    }

    @Override
    protected void initArgs() {
        super.initArgs();
        StatusBarUtils.translucent(getActivity(), Colors.TRANSPARENT);
        StatusBarUtils.setStatusBarLightMode(getActivity());

    }

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        toolbar.inflateMenu(R.menu.menu_search);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });

        fabScrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XToastUtils.toast("分享");
            }
        });

        appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    StatusBarUtils.setStatusBarDarkMode(getActivity());
                } else {
                    StatusBarUtils.setStatusBarLightMode(getActivity());
                }
            }
        });
    }

}
