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

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.button.CountDownButton;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xuexiang
 * @date 2017/10/17 下午4:26
 */
@Page(name = "统一的按钮样式")
public class ButtonStyleFragment extends BaseFragment {

    @BindView(R.id.bt_countdown1)
    SuperButton mBtCountDown1;
    private CountDownButtonHelper mCountDownHelper1;
    @BindView(R.id.bt_countdown2)
    ButtonView mBtCountDown2;
    private CountDownButtonHelper mCountDownHelper2;
    @BindView(R.id.bt_countdown3)
    RoundButton mBtCountDown3;
    private CountDownButtonHelper mCountDownHelper3;
    @BindView(R.id.bt_countdown4)
    CountDownButton mBtCountDown4;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_button_style;
    }

    @Override
    protected void initViews() {
        mCountDownHelper1 = new CountDownButtonHelper(mBtCountDown1, 10);
        mCountDownHelper2 = new CountDownButtonHelper(mBtCountDown2, 20);
        mCountDownHelper3 = new CountDownButtonHelper(mBtCountDown3, 30);
        mCountDownHelper3.setOnCountDownListener(new CountDownButtonHelper.OnCountDownListener() {
            @Override
            public void onCountDown(int time) {
                mBtCountDown3.setText("还剩：" + time + "秒");
            }

            @Override
            public void onFinished() {
                mBtCountDown3.setText("点击重试");
                XToastUtils.toast("时间到！");
            }
        });
    }

    @Override
    protected void initListeners() {

    }

    @OnClick(R.id.bt_countdown1)
    public void startCountDown1() {
        mCountDownHelper1.start();
    }

    @OnClick(R.id.bt_countdown2)
    public void startCountDown2() {
        mCountDownHelper2.start();
    }

    @OnClick(R.id.bt_countdown3)
    public void startCountDown3() {
        mCountDownHelper3.start();
    }

    @Override
    public void onDestroyView() {
        mCountDownHelper1.cancel();
        mCountDownHelper2.cancel();
        mCountDownHelper3.cancel();
        mBtCountDown4.cancelCountDown();
        super.onDestroyView();
    }
}
