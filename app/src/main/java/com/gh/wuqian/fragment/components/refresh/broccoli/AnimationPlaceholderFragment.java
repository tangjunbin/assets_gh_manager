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

package com.gh.wuqian.fragment.components.refresh.broccoli;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.gh.wuqian.DemoDataProvider;
import com.gh.wuqian.R;
import com.gh.wuqian.adapter.NewsListAdapter;
import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.Utils;

import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019/4/7 下午3:10
 */
@Page(name = "动画占位控件")
public class AnimationPlaceholderFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private NewsListAdapter mNewsListAdapter;
    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_broccoli_place_holder;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        WidgetUtils.initRecyclerView(recyclerView, DensityUtils.dp2px(5), ThemeUtils.resolveColor(getContext(), R.attr.xui_config_color_background));

        recyclerView.setAdapter(mNewsListAdapter = new NewsListAdapter(true));
    }

    @Override
    protected void initListeners() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mNewsListAdapter.loadMore(DemoDataProvider.getDemoNewInfos());
                        refreshLayout.finishLoadMore();
                    }
                }, 1000);
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mNewsListAdapter.refresh(DemoDataProvider.getDemoNewInfos());
                        refreshLayout.finishRefresh();
                    }
                }, 3000);
            }
        });

        mNewsListAdapter.setOnItemClickListener(new SmartViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Utils.goWeb(getContext(), mNewsListAdapter.getItem(position).getDetailUrl());
            }
        });
        //设置刷新加载时禁止所有列表操作
        refreshLayout.setDisableContentWhenRefresh(true);
        refreshLayout.setDisableContentWhenLoading(true);
        refreshLayout.autoRefresh();
    }

    @Override
    public void onDestroyView() {
        mNewsListAdapter.recycle();
        super.onDestroyView();
    }

}
