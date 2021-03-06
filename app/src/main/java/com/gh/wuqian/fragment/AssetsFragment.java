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

package com.gh.wuqian.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gh.wuqian.activity.MainActivity;
import com.gh.wuqian.activity.SearchViewActivity;
import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.fragment.components.tabbar.tabsegment.MultiPage;
import com.google.android.material.tabs.TabLayout;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.actionbar.TitleUtils;
import com.gh.wuqian.R;
import com.gh.wuqian.fragment.assets.AssetsBorrowListFragment;
import com.gh.wuqian.fragment.assets.AssetsCleanUpListFragment;
import com.gh.wuqian.fragment.assets.AssetsCollarUseListFragment;
import com.gh.wuqian.fragment.assets.AssetsListFragment;
import com.gh.wuqian.fragment.assets.AssetsRetreatListFragment;


import butterknife.BindView;

import static com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE;

/**
 * 资产管理系统
 *
 * @author 五千
 * @since 2019/11/14 下午2:22
 */
@Page(name = "资产管理", anim = CoreAnim.none)
public class AssetsFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    private String TAG="AssetsFragment";
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.assets_bill_tab)
    TabLayout mTabLayoutBill;

    @BindView(R.id.toolbar)
    FrameLayout toolbar;

//    @BindView(R.id.header_toolbar)
//    FrameLayout header_toolbar;
//    @BindView(R.id.drawer_layout)
//    DrawerLayout drawerLayout;

//    @BindView(R.id.nav_view)
//    NavigationView navView;

    MyAdapter fragmentAdater;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_assets;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "framage状态切换");
        if (hidden) {
            return;
        }else {
            Log.i(TAG, "framage状态切换");
        }
    }
    @Override
    protected TitleBar initTitle() {


        TitleBar titleBar = TitleUtils.addTitleBarDynamic(toolbar, getPageTitle(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popToBack();
            }
        });

        titleBar.setLeftImageResource(R.drawable.ic_action_menu);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            @SingleClick
            public void onClick(View v) {
                getContainer().openMenu();
            }
        });
        LayoutInflater inflater = LayoutInflater.from(getContainer());
        View searchView = inflater.inflate(R.layout.head_search_nav, null);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            @SingleClick
            public void onClick(View v) {
                Intent intent=new Intent(getContainer(), SearchViewActivity.class);
                startActivity(intent);
            }
        });
        titleBar.setCustomTitle(searchView);

        titleBar.addAction(new TitleBar.ImageAction(R.drawable.icon_action_about) {
            @Override
            @SingleClick
            public void performAction(View view) {
                openNewPage(AboutFragment.class);
            }
        });

        return titleBar;
    }
    @Override
    protected void initListeners() {
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                XToastUtils.toast("点击了:" + menuItem.getTitle());
//                return true;
//            }
//        });
//        fab.setOnClickListener(this);

        //主页
        //viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void initViews() {
        initTab();
    }

    /**
     * 初始化tab1 单据切换
     */
    private void initTab(){

        for (String page : MultiPage.getPageNames()) {
            mTabLayoutBill.addTab(mTabLayoutBill.newTab().setText(page));
        }

        mTabLayoutBill.setTabMode(MODE_SCROLLABLE);
        mTabLayoutBill.addOnTabSelectedListener(this);


        //fragmentAdater = new  MyAdapter(getContainer().getSupportFragmentManager());

        fragmentAdater = new  MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(fragmentAdater);
        mTabLayoutBill.setupWithViewPager(viewPager);
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    public MainActivity getContainer() {
        return (MainActivity) getActivity();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return MultiPage.size();
        }


        @Override
        public Fragment getItem(int position) {
            Log.i(TAG,"获取ITEM_____"+position);
            if (position == 1) {
                return new AssetsCollarUseListFragment();//领用
            } else if (position == 2) {
                return new AssetsRetreatListFragment();//退库
            } else if (position == 3) {
                return new AssetsBorrowListFragment();//借用
            }
            else if (position == 4) {
                return new AssetsCleanUpListFragment();//报废
            }
            return new AssetsListFragment();//首页
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return MultiPage.getPage(position).name();
        }
    }

}
