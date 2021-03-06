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

package com.gh.wuqian.fragment;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.CompoundButton;

import com.gh.wuqian.activity.MainActivity;
import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.SettingSPUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xuexiang
 * @since 2019-09-17 17:51
 */
@Page(name = "设置")
public class SettingFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.stv_switch_custom_theme)
    SuperTextView stvSwitchCustomTheme;

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        stvSwitchCustomTheme.setSwitchIsChecked(SettingSPUtils.getInstance().isUseCustomTheme());
        stvSwitchCustomTheme.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                stvSwitchCustomTheme.setSwitchIsChecked(!stvSwitchCustomTheme.getSwitchIsChecked(), false);
            }
        });
        stvSwitchCustomTheme.setSwitchCheckedChangeListener(this);
    }

    @OnClick({R.id.stv_switch_printer})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.stv_switch_printer:
                openNewPage(PrinterFragment.class);
                break;
            default:
                break;
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SettingSPUtils.getInstance().setIsUseCustomTheme(isChecked);
        popToBack();

        //重启主页面
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
