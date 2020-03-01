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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.utils.SnackbarUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.gh.wuqian.R;
import com.gh.wuqian.base.BaseFragment;

import butterknife.BindView;

import static com.google.android.material.tabs.TabLayout.MODE_FIXED;

/**
 * @author XUE
 * @since 2019/5/9 11:43
 */
@Page(name = "TabLayout + ViewPager + AppBarLayout")
public class TabLayoutBehaviorFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected TitleBar initTitle() {
        toolbar.setTitle("TabLayout");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tablayout_behavior;
    }

    String[] titles = new String[]{"资讯", "娱乐", "教育"};

    @Override
    protected void initViews() {
        FragmentAdapter<BaseFragment> adapter = new FragmentAdapter<>(getChildFragmentManager());
        tabLayout.setTabMode(MODE_FIXED);
        for (String title : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
            adapter.addFragment(new SimpleListFragment(), title);
        }
        viewPager.setOffscreenPageLimit(titles.length - 1);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initListeners() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    fab.show();
                } else {
                    fab.hide();
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarUtils.Long(v, "是否确认新建?")
                        .setAction("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        });
    }
}
