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

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.popupwindow.easypopup.EasyPopup;
import com.xuexiang.xui.widget.popupwindow.easypopup.HorizontalGravity;
import com.xuexiang.xui.widget.popupwindow.easypopup.VerticalGravity;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xuexiang
 * @date 2017/10/30 上午11:49
 */
@Page(name = "EasyPopup\n可自定义的弹出窗")
public class EasyPopFragment extends BaseFragment {

    private EasyPopup mCirclePop;
    @BindView(R.id.btn_circle_comment)
    Button mBtnCircleComment;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_easypop;
    }

    @Override
    protected void initViews() {
        initCirclePop();
    }

    @Override
    protected void initListeners() {

    }

    public void initCirclePop() {
        mCirclePop = new EasyPopup(getContext())
                .setContentView(R.layout.layout_friend_circle_comment)
//                .setAnimationStyle(R.style.CirclePopAnim)
                .setFocusAndOutsideEnable(true)
                .createPopup();

        TextView tvZan = mCirclePop.getView(R.id.tv_zan);
        TextView tvComment = mCirclePop.getView(R.id.tv_comment);
        tvZan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 XToastUtils.toast("点赞");
                 mCirclePop.dismiss();
             }
         });

        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XToastUtils.toast("评论");
                mCirclePop.dismiss();
            }
        });
    }

    @OnClick(R.id.btn_circle_comment_left)
    public void showCirclePopLeft(View view) {
        mCirclePop.showAtAnchorView(mBtnCircleComment, VerticalGravity.CENTER, HorizontalGravity.LEFT, 0, 0);
    }

    @OnClick(R.id.btn_circle_comment_right)
    public void showCirclePopRight(View view) {
        mCirclePop.showAtAnchorView(mBtnCircleComment, VerticalGravity.CENTER, HorizontalGravity.RIGHT, 0, 0);
    }

    @OnClick(R.id.btn_circle_comment_top)
    public void showCirclePopTop(View view) {
        mCirclePop.showAtAnchorView(mBtnCircleComment, VerticalGravity.ABOVE, HorizontalGravity.CENTER, 0, 0);
    }

    @OnClick(R.id.btn_circle_comment_bottom)
    public void showCirclePopBottom(View view) {
        mCirclePop.showAtAnchorView(mBtnCircleComment, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
    }

}
