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

package com.gh.wuqian.fragment.components.statelayout;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.github.clans.fab.FloatingActionMenu;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.statelayout.MultipleStatusView;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 自定义状态布局
 *
 * @author xuexiang
 * @since 2018/11/26 上午12:25
 */
@Page(name = "MultipleStatusView\n自定义布局")
public class MultipleStatusViewFragment extends BaseFragment {
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.fab_menu)
    FloatingActionMenu mFloatingActionMenu;

    private Handler mLoadingHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (mMultipleStatusView.getViewStatus() == MultipleStatusView.STATUS_LOADING) {
                mMultipleStatusView.showContent();
            }
            return true;
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multiplestatusview;
    }

    @Override
    protected void initViews() {
        loading();
    }

    @Override
    protected void initListeners() {
        mMultipleStatusView.setOnRetryClickListener(mRetryClickListener);
    }

    final View.OnClickListener mRetryClickListener = (new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            XToastUtils.toast("点击重试");
            loading();
        }
    });

    void loading() {
        mMultipleStatusView.showLoading();
        mLoadingHandler.sendEmptyMessageDelayed(0, 5000);
    }

    @OnClick({R.id.fab_loading, R.id.fab_empty, R.id.fab_error, R.id.fab_no_network, R.id.fab_content})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_loading:
                loading();
                break;
            case R.id.fab_empty:
                mMultipleStatusView.showEmpty();
                break;
            case R.id.fab_error:
                mMultipleStatusView.showError();
                break;
            case R.id.fab_no_network:
                mMultipleStatusView.showNoNetwork();
                break;
            case R.id.fab_content:
                mMultipleStatusView.showContent();
                break;
        }
        mFloatingActionMenu.toggle(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLoadingHandler.removeCallbacksAndMessages(null);
    }
}
