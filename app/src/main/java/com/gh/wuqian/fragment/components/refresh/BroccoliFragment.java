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

package com.gh.wuqian.fragment.components.refresh;

import android.view.View;

import com.gh.wuqian.base.ComponentContainerFragment;
import com.gh.wuqian.fragment.components.refresh.broccoli.AnimationPlaceholderFragment;
import com.gh.wuqian.fragment.components.refresh.broccoli.CommonPlaceholderFragment;
import com.gh.wuqian.utils.Utils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

/**
 * @author xuexiang
 * @since 2019/4/6 下午11:23
 */
@Page(name = "Broccoli\n预加载占位控件")
public class BroccoliFragment extends ComponentContainerFragment {
    /**
     * 获取页面的类集合[使用@Page注解进行注册的页面]
     *
     * @return
     */
    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                CommonPlaceholderFragment.class,
                AnimationPlaceholderFragment.class
        };
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.TextAction("Github") {
            @Override
            public void performAction(View view) {
                Utils.goWeb(getContext(), "https://github.com/samlss/Broccoli");
            }
        });
        return titleBar;
    }
}
