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

package com.gh.wuqian.fragment.components.guideview;

import android.widget.Button;

import com.gh.wuqian.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.guidview.GuideCaseQueue;
import com.xuexiang.xui.widget.guidview.GuideCaseView;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 引导页队列显示
 *
 * @author xuexiang
 * @since 2018/11/30 上午12:30
 */
@Page(name = "GuideCaseQueue\n引导队列")
public class GuideCaseViewQueueFragment extends BaseFragment {

    @BindView(R.id.step1)
    Button mStep1;
    @BindView(R.id.step2)
    Button mStep2;
    @BindView(R.id.step3)
    Button mStep3;
    @BindView(R.id.step4)
    Button mStep4;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guide_case_queue;
    }

    @Override
    protected void initViews() {
        showTextGuideView();
    }

    /**
     * 显示文字引导
     */
    private void showTextGuideView() {
        final GuideCaseView guideStep1 = new GuideCaseView.Builder(getActivity())
                .title("请注意，这是第一步")
                .focusOn(mStep1)
                .build();

        final GuideCaseView guideStep2 = new GuideCaseView.Builder(getActivity())
                .title("请注意，这是第二步")
                .focusOn(mStep2)
                .build();

        final GuideCaseView guideStep3 = new GuideCaseView.Builder(getActivity())
                .title("请注意，这是第三步")
                .focusOn(mStep3)
                .build();

        final GuideCaseView guideStep4 = new GuideCaseView.Builder(getActivity())
                .title("请注意，这是第四步")
                .focusOn(mStep4)
                .build();

        new GuideCaseQueue()
                .add(guideStep1)
                .add(guideStep2)
                .add(guideStep3)
                .add(guideStep4)
                .show();
    }

    @OnClick(R.id.changePicture)
    public void changePictureGuideView() {
        showPictureGuideView();
    }


    /**
     * 显示图片引导
     */
    private void showPictureGuideView() {
        final GuideCaseView guideStep1 = new GuideCaseView.Builder(getActivity())
                .picture(R.drawable.img_guidecaseview_1)
                .focusOn(mStep1)
                .build();

        final GuideCaseView guideStep2 = new GuideCaseView.Builder(getActivity())
                .picture(R.drawable.img_guidecaseview_2)
                .focusOn(mStep2)
                .build();

        final GuideCaseView guideStep3 = new GuideCaseView.Builder(getActivity())
                .picture(R.drawable.img_guidecaseview_3)
                .focusOn(mStep3)
                .build();

        final GuideCaseView guideStep4 = new GuideCaseView.Builder(getActivity())
                .picture(R.drawable.img_guidecaseview_4)
                .focusOn(mStep4)
                .build();

        new GuideCaseQueue()
                .add(guideStep1)
                .add(guideStep2)
                .add(guideStep3)
                .add(guideStep4)
                .show();
    }

    @Override
    protected void initListeners() {

    }
}
