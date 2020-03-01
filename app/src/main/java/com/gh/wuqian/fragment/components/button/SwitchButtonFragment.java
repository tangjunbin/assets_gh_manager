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

package com.gh.wuqian.fragment.components.button;

import android.view.View;
import android.widget.CompoundButton;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.button.switchbutton.SwitchButton;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * @author xuexiang
 * @date 2018/1/16 上午11:01
 */
@Page(name = "SwitchButton\n切换按钮")
public class SwitchButtonFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.sb_default)
    SwitchButton mSBDefault;
    @BindView(R.id.sb_md)
    SwitchButton mSBMD;
    @BindView(R.id.sb_ios)
    SwitchButton mSBIOS;
    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_switch_button;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
    }

    /**
     * 初始化监听
     */
    @Override
    protected void initListeners() {
        mSBDefault.setOnCheckedChangeListener(this);
        mSBMD.setOnCheckedChangeListener(this);
        mSBIOS.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        XToastUtils.toast("isChecked：" + isChecked);
    }

    @OnClick(R.id.btn_switch)
    void onClick(View v) {
        mSBDefault.toggle();
        mSBMD.toggle();
        mSBIOS.toggle();
    }
}
