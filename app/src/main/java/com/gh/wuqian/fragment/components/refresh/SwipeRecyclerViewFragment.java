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
import com.gh.wuqian.fragment.components.refresh.swipe.SwipeDragMoveFragment;
import com.gh.wuqian.fragment.components.refresh.swipe.SwipeHeadFootViewFragment;
import com.gh.wuqian.fragment.components.refresh.swipe.SwipeMenuItemFragment;
import com.gh.wuqian.fragment.components.refresh.swipe.SwipeRefreshFragment;
import com.gh.wuqian.utils.Utils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

/**
 * @author XUE
 * @since 2019/4/1 11:00
 */
@Page(name = "SwipeRecyclerView\n基于RecyclerView封装, 支持Item侧滑菜单、Item滑动删除、Item长按拖拽、添加HeaderView/FooterView、加载更多、Item点击监听等功能")
public class SwipeRecyclerViewFragment extends ComponentContainerFragment {
    /**
     * 获取页面的类集合[使用@Page注解进行注册的页面]
     *
     * @return
     */
    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                SwipeMenuItemFragment.class,
                SwipeDragMoveFragment.class,
                SwipeHeadFootViewFragment.class,
                SwipeRefreshFragment.class
        };
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.TextAction("Github") {
            @Override
            public void performAction(View view) {
                Utils.goWeb(getContext(), "https://github.com/yanzhenjie/SwipeRecyclerView");
            }
        });
        return titleBar;
    }
}
