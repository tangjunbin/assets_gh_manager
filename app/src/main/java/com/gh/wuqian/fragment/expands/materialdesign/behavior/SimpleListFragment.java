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

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.gh.wuqian.DemoDataProvider;
import com.gh.wuqian.R;
import com.gh.wuqian.adapter.NewsCardViewListAdapter;
import com.gh.wuqian.adapter.entity.NewInfo;
import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.Utils;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import butterknife.BindView;

/**
 * @author XUE
 * @since 2019/5/9 11:54
 */
public class SimpleListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsCardViewListAdapter mAdapter;

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.include_recycler_view_refresh;
    }

    @Override
    protected void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter = new NewsCardViewListAdapter());
        mAdapter.refresh(DemoDataProvider.getDemoNewInfos());

        swipeRefreshLayout.setEnabled(false);
    }

    @Override
    protected void initListeners() {
        mAdapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<NewInfo>() {
            @Override
            public void onItemClick(View itemView, NewInfo item, int position) {
                Utils.goWeb(getContext(), item.getDetailUrl());
            }
        });
    }
}
