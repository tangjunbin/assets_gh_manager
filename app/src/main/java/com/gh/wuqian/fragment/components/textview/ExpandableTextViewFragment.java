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

package com.gh.wuqian.fragment.components.textview;

import android.widget.TextView;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.textview.ExpandableTextView;
import com.gh.wuqian.R;

import butterknife.BindView;

/**
 * @author xuexiang
 * @date 2017/10/27 下午3:33
 */
@Page(name = "可伸缩折叠的TextView")
public class ExpandableTextViewFragment extends BaseFragment {
    @BindView(R.id.expand_text_view)
    ExpandableTextView mExpandableTextView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_expandabletextview;
    }

    @Override
    protected void initViews() {
        mExpandableTextView.setText(getString(R.string.etv_content_demo1));
        mExpandableTextView.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                XToastUtils.toast(isExpanded ? "Expanded" : "Collapsed");
            }
        });

    }

    @Override
    protected void initListeners() {

    }
}
