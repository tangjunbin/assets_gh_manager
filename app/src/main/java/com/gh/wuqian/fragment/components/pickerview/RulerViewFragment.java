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

package com.gh.wuqian.fragment.components.pickerview;

import android.view.View;
import android.widget.EditText;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.picker.RulerView;
import com.gh.wuqian.R;
import com.xuexiang.xutil.common.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xuexiang
 * @since 2019/4/2 下午10:30
 */
@Page(name = "RulerView\n支持选择身高、体重、视力的尺子")
public class RulerViewFragment extends BaseFragment {

    @BindView(R.id.et_weight)
    EditText etWeight;
    @BindView(R.id.rulerView)
    RulerView rulerView;

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ruler_view;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {


    }


    @SingleClick
    @OnClick({R.id.btn_set, R.id.btn_get})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_set:
                if (!StringUtils.isEmpty(etWeight.getText().toString())) {
                    rulerView.setCurrentValue(StringUtils.toFloat(etWeight.getText().toString(), 0));
                } else {
                    XToastUtils.toast("请输入体重值！");
                }
                break;
            case R.id.btn_get:
                XToastUtils.toast("体重：" + rulerView.getCurrentValue());
                break;
            default:
                break;
        }
    }
}
