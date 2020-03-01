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

package com.gh.wuqian.fragment.components.edittext;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.edittext.verify.VerifyCodeEditText;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author XUE
 * @since 2019/5/7 13:34
 */
@Page(name = "VerifyCodeEditText\n用于手机验证码或者支付密码的输入框")
public class VerifyCodeEditTextFragment extends BaseFragment implements VerifyCodeEditText.OnInputListener {

    @BindView(R.id.vcet_1)
    VerifyCodeEditText vcet1;
    @BindView(R.id.vcet_2)
    VerifyCodeEditText vcet2;
    @BindView(R.id.vcet_3)
    VerifyCodeEditText vcet3;
    @BindView(R.id.vcet_4)
    VerifyCodeEditText vcet4;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify_code_edittext;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        vcet1.setOnInputListener(this);
        vcet2.setOnInputListener(this);
        vcet3.setOnInputListener(this);
        vcet4.setOnInputListener(this);
    }

    @Override
    public void onComplete(String input) {
        XToastUtils.toast("onComplete:" + input);
    }

    @Override
    public void onChange(String input) {
        XToastUtils.toast("onChange:" + input);
    }

    @Override
    public void onClear() {
        XToastUtils.toast("onClear");
    }

    @OnClick(R.id.btn_clear)
    public void onViewClicked() {
        vcet1.clearInputValue();
        vcet2.clearInputValue();
        vcet3.clearInputValue();
        vcet4.clearInputValue();
    }
}
