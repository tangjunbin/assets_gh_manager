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

package com.gh.wuqian.fragment.components.flowlayout;

import android.view.View;

import com.gh.wuqian.adapter.FlowTagAdapter;
import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;
import com.gh.wuqian.R;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xuexiang
 * @date 2017/11/21 上午10:09
 */
@Page(name = "FlowTagLayout\n流标签")
public class FlowTagLayoutFragment extends BaseFragment {
    @BindView(R.id.flowlayout_normal_select)
    FlowTagLayout mNormalFlowTagLayout;

    @BindView(R.id.flowlayout_single_select)
    FlowTagLayout mSingleFlowTagLayout;

    @BindView(R.id.flowlayout_single_select_cancelable)
    FlowTagLayout mSingleCancelableFlowTagLayout;

    @BindView(R.id.flowlayout_multi_select)
    FlowTagLayout mMultiFlowTagLayout;

    @BindView(R.id.flowlayout_display)
    FlowTagLayout mDisplayFlowTagLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_flowtaglayout;
    }

    @Override
    protected void initViews() {
        initNormalFlowTagLayout();
        initSingleFlowTagLayout();
        initSingleCancelableFlowTagLayout();
        initMultiFlowTagLayout();
    }

    @Override
    protected void initListeners() {

    }

    private void initNormalFlowTagLayout() {
        FlowTagAdapter tagAdapter = new FlowTagAdapter(getContext());
        mNormalFlowTagLayout.setAdapter(tagAdapter);
        mNormalFlowTagLayout.setOnTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                XToastUtils.toast("点击了：" + parent.getAdapter().getItem(position));
            }
        });
        tagAdapter.addTags(ResUtils.getStringArray(R.array.tags_values));
    }

    private void initSingleFlowTagLayout() {
        FlowTagAdapter tagAdapter = new FlowTagAdapter(getContext());
        mSingleFlowTagLayout.setAdapter(tagAdapter);
        mSingleFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mSingleFlowTagLayout.setOnTagSelectListener(new FlowTagLayout.OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                XToastUtils.toast(getSelectedText(parent, selectedList));
            }
        });
        tagAdapter.addTags(ResUtils.getStringArray(R.array.tags_values));
        tagAdapter.setSelectedPositions(2, 3, 4);

    }

    private void initSingleCancelableFlowTagLayout() {
        FlowTagAdapter tagAdapter = new FlowTagAdapter(getContext());
        mSingleCancelableFlowTagLayout.setAdapter(tagAdapter);
        mSingleCancelableFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mSingleCancelableFlowTagLayout.setOnTagSelectListener(new FlowTagLayout.OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                XToastUtils.toast(getSelectedText(parent, selectedList));
            }
        });
        tagAdapter.addTags(ResUtils.getStringArray(R.array.tags_values));
        tagAdapter.setSelectedPositions(2, 3, 4);

    }

    private void initMultiFlowTagLayout() {
        FlowTagAdapter tagAdapter = new FlowTagAdapter(getContext());
        mMultiFlowTagLayout.setAdapter(tagAdapter);
        mMultiFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMultiFlowTagLayout.setOnTagSelectListener(new FlowTagLayout.OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
                XToastUtils.toast(getSelectedText(parent, selectedList));
            }
        });
        tagAdapter.addTags(ResUtils.getStringArray(R.array.tags_values));
        tagAdapter.setSelectedPositions(2, 3, 4);

//        mMultiFlowTagLayout.setItems("1111", "2222", "3333", "4444");
//        mMultiFlowTagLayout.setSelectedItems("3333", "4444");

    }

    private String getSelectedText(FlowTagLayout parent, List<Integer> selectedList) {
        StringBuilder sb = new StringBuilder("选中的内容：\n");
        for (int index : selectedList) {
            sb.append(parent.getAdapter().getItem(index));
            sb.append(";");
        }
        return sb.toString();
    }

    @OnClick({R.id.btn_add_tag, R.id.btn_clear_tag})
    void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_add_tag:
                mDisplayFlowTagLayout.addTag("标签" + (int)(Math.random() * 100));
                break;
            case R.id.btn_clear_tag:
                mDisplayFlowTagLayout.clearTags();
                break;
            default:
                break;
        }
    }

}
