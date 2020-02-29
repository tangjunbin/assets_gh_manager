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

package com.xuexiang.xuidemo.fragment.assets;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wuqian.xui.widget.spinner.DropDownMenu;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;
import com.xuexiang.xuidemo.R;
import com.xuexiang.xuidemo.activity.MainActivity;
import com.xuexiang.xuidemo.activity.SearchViewActivity;
import com.xuexiang.xuidemo.adapter.AssetsListAdapter;
import com.xuexiang.xuidemo.adapter.dropdownmenu.CityDropDownAdapter;
import com.xuexiang.xuidemo.adapter.dropdownmenu.ConstellationAdapter;
import com.xuexiang.xuidemo.adapter.dropdownmenu.ListDropDownAdapter;
import com.xuexiang.xuidemo.base.BaseFragment;
import com.xuexiang.xuidemo.fragment.AboutFragment;
import com.xuexiang.xuidemo.fragment.components.tabbar.tabsegment.MultiPage;
import com.xuexiang.xuidemo.fragment.viewmodel.AssetsModel;
import com.xuexiang.xuidemo.fragment.viewmodel.model.AssetsPageData;
import com.xuexiang.xuidemo.utils.XToastUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE;

/**
 * 资产列表
 *
 * @author 五千
 * @since 2019/11/14 下午2:22
 */
public class AssetsListFragment extends Fragment {

        protected View mRootView;
        protected Unbinder mUnbinder;


        @BindView(R.id.ddm_content)
        protected DropDownMenu mDropDownMenu;

        protected String[] mHeaders ;

        protected List<View> mPopupViews;

        protected CityDropDownAdapter mSortAdapter;
        protected CityDropDownAdapter mSignAdapter;
        protected CityDropDownAdapter mStatusAdapter;

        protected String[] mSort;
        protected String[] mSign;
        protected String[] mStatus;

        protected RecyclerView mRecyclerView ;

        protected SmartRefreshLayout mRefreshLayout;
        protected StatefulLayout mLlStateful;

        protected Map<String, Object> orderMap = new TreeMap<>();

        protected AssetsModel assetsViewModel;

        protected Integer p = 1;
        protected Integer limit = 10;//每此获取的条数

        private AssetsListAdapter mAdapter;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                mRootView = inflater.inflate(getLayoutId(), container, false);
                mUnbinder = ButterKnife.bind(this, mRootView);
                //init mPopupViews
                mPopupViews = new ArrayList<>();
                initArgs();
                initViews();
                return mRootView;
        }

        protected int getLayoutId() {
                return R.layout.fragment_assets_list;
        }
        protected void initArgs() {
                mHeaders = new String[]{"默认排序", "签字状态", "资产状态"};

                mSort = ResUtils.getStringArray(R.array.asstes_order_entry);
                mSign = ResUtils.getStringArray(R.array.asstes_sign_entry);
                mStatus = ResUtils.getStringArray(R.array.asstes_status_entry);
        }
        protected void initViews(){
                initTabLayout();
                initScreen();
        }

        /**
         * 初始化 tab 切换
         */
        protected void initTabLayout(){
                final ListView sortView = new ListView(getContext());
                mSortAdapter = new CityDropDownAdapter(getContext(), mSort);
                sortView.setDividerHeight(0);
                sortView.setAdapter(mSortAdapter);

                //init age menu
                final ListView aSignView = new ListView(getContext());
                aSignView.setDividerHeight(0);
                mSignAdapter = new CityDropDownAdapter(getContext(), mSign);
                aSignView.setAdapter(mSignAdapter);

                //init sex menu
                final ListView statusView = new ListView(getContext());
                statusView.setDividerHeight(0);
                mStatusAdapter = new CityDropDownAdapter(getContext(), mStatus);
                statusView.setAdapter(mStatusAdapter);

                mPopupViews.add(sortView);
                mPopupViews.add(aSignView);
                mPopupViews.add(statusView);
                //add item click event
                sortView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mSortAdapter.setSelectPosition(position);
                                mDropDownMenu.setTabMenuText(position == 0 ? mHeaders[0] : mSort[position],position);

                                orderMap.put("sort",mSort[position]);
                                getHttpData();
                                mDropDownMenu.closeMenu();
                        }
                });

                aSignView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mSignAdapter.setSelectPosition(position);
                                mDropDownMenu.setTabMenuText(position == 0 ? mHeaders[1] : mSign[position],position);

                                orderMap.put("sign",mSign[position]);
                                getHttpData();
                                mDropDownMenu.closeMenu();
                        }
                });

                statusView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mStatusAdapter.setSelectPosition(position);
                                mDropDownMenu.setTabMenuText(position == 0 ? mHeaders[2] : mStatus[position],position);

                                orderMap.put("status",mStatus[position]);
                                getHttpData();
                                mDropDownMenu.closeMenu();
                        }
                });

        }


        protected void initScreen(){

                //init context view
                View inflate = View.inflate(getContext(), R.layout.wuqian_layout_refresh_assets_list, null);

                mRecyclerView = inflate.findViewById(R.id.recycler_assets_list);

                mRefreshLayout = inflate.findViewById(R.id.assets_list_layout);

                mLlStateful = inflate.findViewById(R.id.asseetsll_stateful);

                WidgetUtils.initRecyclerView(mRecyclerView);

                initListAdapter();

                mRefreshLayout.autoRefresh();
                //初始化 拉下刷新事件
                initList();
                //init dropdownview
                mDropDownMenu.setDropDownMenu(mHeaders, mPopupViews, inflate);
        }
        /**
         * 初始化列表 配置器
         */
        protected void initListAdapter(){
                mRecyclerView.setAdapter(mAdapter = new AssetsListAdapter());
                assetsViewModel = ViewModelProviders.of(this).get(AssetsModel.class);
                assetsViewModel.mAssetsLiveData.observe(this, new Observer<AssetsPageData>() {
                        @Override
                        public void onChanged(AssetsPageData assetsPageData) {
                                if(assetsPageData.getStatus() == AssetsPageData.Status.LORDMORE) {
                                        mAdapter.loadMore(assetsPageData.getOnePageAssets());
                                }else{
                                        mAdapter.refresh(assetsPageData.getOnePageAssets());
                                        mRefreshLayout.resetNoMoreData();
                                        mLlStateful.showContent();
                                        mRefreshLayout.setEnableLoadMore(true);
                                }
                        }
                });
        }
        protected void getHttpData(){
                mRefreshLayout.autoRefresh();
        }
        /**
         * 初始化列表数据
         */
        protected void initList(){


                //下拉刷新
                mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                                p = 1;//刷新 页数 赋 0
                                refreshLayout.getLayout().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                                assetsViewModel.refresh(limit,orderMap,new AssetsModel.Callback() {
                                                        @Override
                                                        public void onCodeSuccess() {
                                                                p++;
                                                        }

                                                        @Override
                                                        public void onSuccess(String jsonString) {
                                                        }

                                                        @Override
                                                        public void onCodeError() {
                                                                showError();
                                                        }

                                                        @Override
                                                        public void onDataComplete() {
                                                                XToastUtils.toast("数据全部加载完毕");
                                                                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                                                        }

                                                        @Override
                                                        public void onSuccessComplete() {
                                                                refreshLayout.finishRefresh();
                                                        }

                                                        @Override
                                                        public void onNetworkError() {
                                                                showOffline();
                                                                refreshLayout.finishRefresh();
                                                        }
                                                });
                                        }
                                }, 100);
                        }
                });
                //上拉加载
                mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                                refreshLayout.getLayout().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                                assetsViewModel.loadMore(p,limit,orderMap,new AssetsModel.Callback() {
                                                        @Override
                                                        public void onCodeSuccess() {
                                                                p++;
                                                        }

                                                        @Override
                                                        public void onSuccess(String jsonString) {
                                                                refreshLayout.finishLoadMore();
                                                        }

                                                        @Override
                                                        public void onCodeError() {

                                                        }

                                                        @Override
                                                        public void onDataComplete() {
                                                                XToastUtils.toast("数据全部加载完毕");
                                                                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                                                        }

                                                        @Override
                                                        public void onSuccessComplete() {
                                                                refreshLayout.finishRefresh();
                                                        }

                                                        @Override
                                                        public void onNetworkError() {
                                                                showOffline();
                                                                refreshLayout.finishRefresh();
                                                        }
                                                });

                                        }
                                }, 100);
                        }
                });
        }
        public MainActivity getContainer() {
                return (MainActivity) getActivity();
        }
        protected void showOffline() {
                mLlStateful.showOffline(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                mRefreshLayout.autoRefresh();
                        }
                });
                mRefreshLayout.setEnableLoadMore(false);
        }

        protected void showError() {
                mLlStateful.showError(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                mRefreshLayout.autoRefresh();
                        }
                });
                mRefreshLayout.setEnableLoadMore(false);
        }
}
