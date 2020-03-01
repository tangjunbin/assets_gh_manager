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

package com.gh.wuqian.fragment.components;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.rxutil2.rxjava.RxJavaUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.textview.MarqueeTextView;
import com.xuexiang.xui.widget.textview.marqueen.ComplexItemEntity;
import com.xuexiang.xui.widget.textview.marqueen.ComplexViewMF;
import com.xuexiang.xui.widget.textview.marqueen.DisplayEntity;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeFactory;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeView;
import com.xuexiang.xui.widget.textview.marqueen.SimpleNoticeMF;
import com.gh.wuqian.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * 文字滚动控件
 *
 * @author XUE
 * @date 2017/9/13 10:10
 */
@Page(name = "文字滚动", extra = R.drawable.ic_widget_marquee)
public class MarqueeViewFragment extends BaseFragment {

    @BindView(R.id.marqueeView1)
    MarqueeView marqueeView1;
    @BindView(R.id.marqueeView2)
    MarqueeView marqueeView2;
    @BindView(R.id.marqueeView3)
    MarqueeView marqueeView3;
    @BindView(R.id.marqueeView4)
    MarqueeView marqueeView4;
    @BindView(R.id.marqueeView5)
    MarqueeView marqueeView5;
    @BindView(R.id.tv_marquee)
    MarqueeTextView mTvMarquee;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_marqueen;
    }

    @Override
    protected void initViews() {
        final List<String> datas = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");

        MarqueeFactory<TextView, String> marqueeFactory1 = new SimpleNoticeMF(getContext());
        marqueeView1.setMarqueeFactory(marqueeFactory1);
        marqueeView1.startFlipping();
        marqueeFactory1.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClick(View view, MarqueeFactory.ViewHolder<TextView, String> holder) {
                XToastUtils.toast(holder.getData());
            }
        });
        marqueeFactory1.setData(datas);

        MarqueeFactory<TextView, String> marqueeFactory2 = new SimpleNoticeMF(getContext());
        marqueeFactory2.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClick(View view, MarqueeFactory.ViewHolder<TextView, String> holder) {
                XToastUtils.toast(holder.getData());
            }
        });
        marqueeFactory2.setData(datas);
        marqueeView2.setMarqueeFactory(marqueeFactory2);
        marqueeView2.setAnimDuration(15000);
        marqueeView2.setInterval(16000);
        marqueeView2.startFlipping();

        MarqueeFactory<TextView, String> marqueeFactory3 = new SimpleNoticeMF(getContext());
        marqueeFactory3.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClick(View view, MarqueeFactory.ViewHolder<TextView, String> holder) {
                XToastUtils.toast(holder.getData());
            }
        });
        marqueeFactory3.setData(datas);
        marqueeView3.setMarqueeFactory(marqueeFactory3);
        marqueeView3.setAnimInAndOut(R.anim.marquee_left_in, R.anim.marquee_right_out);
        marqueeView3.setAnimDuration(8000);
        marqueeView3.setInterval(8500);
        marqueeView3.startFlipping();

        MarqueeFactory<TextView, String> marqueeFactory4 = new SimpleNoticeMF(getContext());
        marqueeFactory4.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClick(View view, MarqueeFactory.ViewHolder<TextView, String> holder) {
                XToastUtils.toast(holder.getData());
            }
        });
        marqueeFactory4.setData(datas);
        marqueeView4.setAnimInAndOut(R.anim.marquee_top_in, R.anim.marquee_bottom_out);
        marqueeView4.setMarqueeFactory(marqueeFactory4);
        marqueeView4.startFlipping();

        List<ComplexItemEntity> complexDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            complexDatas.add(new ComplexItemEntity("标题 " + i, "副标题 " + i, "时间 " + i));
        }
        MarqueeFactory<RelativeLayout, ComplexItemEntity> marqueeFactory5 = new ComplexViewMF(getContext());
        marqueeFactory5.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<RelativeLayout, ComplexItemEntity>() {
            @Override
            public void onItemClick(View view, MarqueeFactory.ViewHolder<RelativeLayout, ComplexItemEntity> holder) {
                XToastUtils.toast(holder.getData().toString());
            }
        });
        marqueeFactory5.setData(complexDatas);
        marqueeView5.setAnimInAndOut(R.anim.marquee_top_in, R.anim.marquee_bottom_out);
        marqueeView5.setMarqueeFactory(marqueeFactory5);
        marqueeView5.startFlipping();

        mTvMarquee.setOnMarqueeListener(new MarqueeTextView.OnMarqueeListener() {
            @Override
            public DisplayEntity onStartMarquee(DisplayEntity displayMsg, int index) {
                if (displayMsg.toString().equals("离离原上草，一岁一枯荣。")) {
                    return null;
                } else {
                    XToastUtils.toast("开始滚动：" + displayMsg.toString());
                    return displayMsg;
                }
            }

            @Override
            public List<DisplayEntity> onMarqueeFinished(List<DisplayEntity> displayDatas) {
                XToastUtils.toast("一轮滚动完毕！");
                return displayDatas;
            }
        });
        mTvMarquee.startSimpleRoll(datas);

        RxJavaUtils.delay(5, new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                mTvMarquee.removeDisplayString(datas.get(3));
            }
        });

//        RxManager.delay(5, (o) -> mTvMarquee.addDisplayString("这是动态添加的消息"));
//
//        RxManager.delay(10, (o) -> mTvMarquee.clear());
//
//        RxManager.delay(15, (o) -> mTvMarquee.addDisplayString("这是动态添加的消息"));
    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onDestroyView() {
        mTvMarquee.clear();
        super.onDestroyView();
    }
}