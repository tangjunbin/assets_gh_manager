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

package com.gh.wuqian.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gh.wuqian.DemoDataProvider;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleImageBanner;
import com.xuexiang.xui.widget.banner.widget.banner.base.BaseBanner;
import com.gh.wuqian.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 沉浸式状态栏
 *
 * @author xuexiang
 * @since 2019/4/9 上午12:00
 */
public class TranslucentActivity extends AppCompatActivity {

    @BindView(R.id.sib_simple_usage)
    SimpleImageBanner sibSimpleUsage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉浸式状态栏
        StatusBarUtils.translucent(this);
        setContentView(R.layout.activity_translucent);
        ButterKnife.bind(this);

        sibSimpleUsage.setSource(DemoDataProvider.getBannerList())
                .setOnItemClickListener(new BaseBanner.OnItemClickListener<BannerItem>() {
                    @Override
                    public void onItemClick(View view, BannerItem item, int position) {
                        XToastUtils.toast("headBanner position--->" + position);
                    }
                }).startScroll();

    }


    @Override
    protected void onDestroy() {
        sibSimpleUsage.recycle();
        super.onDestroy();
    }
}
