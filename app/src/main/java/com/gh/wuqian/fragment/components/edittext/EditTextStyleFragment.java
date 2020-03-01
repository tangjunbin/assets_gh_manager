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
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.edittext.materialedittext.validation.RegexpValidator;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 *
 * @author xuexiang
 * @since 2018/11/26 下午5:39
 */
@Page(name = "统一的输入框样式")
public class EditTextStyleFragment extends BaseFragment {

    @BindView(R.id.et_basic)
    MaterialEditText mEtBasic;
    @BindView(R.id.bt_disenable)
    RoundButton mBtDisenable;
    @BindView(R.id.et_check)
    MaterialEditText mEtCheck;
    @BindView(R.id.et_auto_check)
    MaterialEditText mEtAutoCheck;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edittext_style;
    }

    @Override
    protected void initViews() {
        initValidationEt();
    }

    @Override
    protected void initListeners() {

    }

    private void initValidationEt() {
        mEtCheck.addValidator(new RegexpValidator("只能输入数字!", "\\d+"));
        mEtAutoCheck.addValidator(new RegexpValidator("只能输入数字!", "\\d+"));
        mEtAutoCheck.addValidator(new RegexpValidator(getString(R.string.tip_number_only_error_message), getString(R.string.regexp_number_only)));
    }

    @OnClick(R.id.bt_disenable)
    public void disEnable() {
        mEtBasic.setEnabled(!mEtBasic.isEnabled());
        mBtDisenable.setText(mEtBasic.isEnabled() ? "不允许输入" : "允许输入");
    }


    @OnClick(R.id.bt_check_vaild)
    public void checkValid() {
        mEtCheck.validate();
    }
}
