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

package com.gh.wuqian.fragment.components.popupwindow;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.popupwindow.ViewTooltip;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xuexiang
 * @date 2017/10/29 下午11:13
 */
@Page(name = "控件提示工具")
public class ViewTipFragment extends BaseFragment {
    @BindView(R.id.editText)
    EditText mEditText;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_viewtip;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @OnClick(R.id.btn_left)
    public void tipOnLeft(View view) {
        ViewTooltip
                .on(mEditText)
                .color(Color.BLACK)
                .position(ViewTooltip.Position.LEFT)
                .text("Some tooltip with long text")
                .clickToHide(true)
                .autoHide(false, 0)
                .animation(new ViewTooltip.FadeTooltipAnimation(500))
                .onDisplay(new ViewTooltip.ListenerDisplay() {
                    @Override
                    public void onDisplay(View view) {
                        XToastUtils.toast("onDisplay");
                    }
                })
                .onHide(new ViewTooltip.ListenerHide() {
                    @Override
                    public void onHide(View view) {
                        XToastUtils.toast("onHide");
                    }
                })
                .show();
    }

    @OnClick(R.id.btn_right)
    public void tipOnRight(View view) {
        ViewTooltip
                .on(mEditText)
                .autoHide(true, 1000)
                .position(ViewTooltip.Position.RIGHT)
                .text("Some tooltip with long text")
                .show();
    }

    @OnClick(R.id.btn_top)
    public void tipOnTop(View view) {
        ViewTooltip
                .on(mEditText)
                .position(ViewTooltip.Position.TOP)
                .text("Popup可以设置其位置以及显示和隐藏的动画")
                .show();
    }


    @OnClick(R.id.btn_bottom)
    public void tipOnBotton(View view) {
        ViewTooltip
                .on(mEditText)
                .color(Color.BLACK)
//                .padding(20, 20, 20, 20)
                .position(ViewTooltip.Position.BOTTOM)
                .align(ViewTooltip.ALIGN.START)
                .text("abcdefg")
                .show();
    }

    @OnClick(R.id.bottomRight)
    public void tipOnBottomRight(View view) {
        ViewTooltip
                .on(view)
                .position(ViewTooltip.Position.TOP)
                .text("bottomRight bottomRight bottomRight")
                .show();
    }

    @OnClick(R.id.bottomLeft)
    public void tipOnBottomLeft(View view) {
        ViewTooltip
                .on(view)
                .position(ViewTooltip.Position.TOP)
                .text("bottomLeft bottomLeft bottomLeft")
                .show();
    }
}
