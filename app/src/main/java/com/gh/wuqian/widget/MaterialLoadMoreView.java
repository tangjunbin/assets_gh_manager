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

package com.gh.wuqian.widget;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

/**
 * 简单的Material Design风格的加载进度条
 *
 * @author XUE
 * @since 2019/5/9 9:37
 */
public class MaterialLoadMoreView extends ProgressBar implements SwipeRecyclerView.LoadMoreView {

    public MaterialLoadMoreView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setVisibility(GONE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        setPadding(0, DensityUtil.dp2px(10), 0, DensityUtil.dp2px(10));
        setLayoutParams(params);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onLoading() {
        setVisibility(VISIBLE);
    }

    @Override
    public void onLoadFinish(boolean dataEmpty, boolean hasMore) {
        setVisibility(GONE);
    }

    @Override
    public void onWaitToLoadMore(SwipeRecyclerView.LoadMoreListener loadMoreListener) {

    }

    @Override
    public void onLoadError(int errorCode, String errorMessage) {
        setVisibility(GONE);
    }
}
