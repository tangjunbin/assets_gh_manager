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

package com.gh.wuqian.fragment.components.refresh;

import android.os.Handler;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gh.wuqian.DemoDataProvider;
import com.gh.wuqian.adapter.SimpleRecyclerAdapter;
import com.gh.wuqian.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.WidgetUtils;
import com.gh.wuqian.R;

import butterknife.BindView;

/**
 * @author XUE
 * @since 2019/4/1 9:59
 */
@Page(name = "SwipeRefreshLayout\n谷歌官方下拉刷新控件")
public class SwipeRefreshLayoutFragment extends BaseFragment {
    private SimpleRecyclerAdapter mAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_swipe_refresh_layout;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        WidgetUtils.initRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter = new SimpleRecyclerAdapter());

        swipeRefreshLayout.setColorSchemeColors(0xff0099cc, 0xffff4444, 0xff669900, 0xffaa66cc, 0xffff8800);
    }

    @Override
    protected void initListeners() {
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        refresh(); //第一次进入触发自动刷新，演示效果
    }

    private void refresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadData();
    }

    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.refresh(DemoDataProvider.getDemoData());
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 1000);
    }

}
