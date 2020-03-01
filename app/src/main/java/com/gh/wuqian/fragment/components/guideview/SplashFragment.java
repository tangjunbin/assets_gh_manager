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

import android.content.Intent;

import com.gh.wuqian.activity.SplashActivity;
import com.gh.wuqian.base.BaseSimpleListFragment;
import com.xuexiang.xpage.annotation.Page;

import java.util.List;

/**
 * 启动页演示
 *
 * @author xuexiang
 * @since 2018/11/30 上午12:56
 */
@Page(name = "启动页")
public class SplashFragment extends BaseSimpleListFragment {
    /**
     * 初始化例子
     *
     * @param lists
     * @return
     */
    @Override
    protected List<String> initSimpleData(List<String> lists) {
        lists.add("渐近式启动页");
        lists.add("非渐近式启动页");
        return lists;
    }

    /**
     * 条目点击
     *
     * @param position
     */
    @Override
    protected void onItemClick(int position) {
        Intent i = new Intent();
        i.putExtra(SplashActivity.KEY_IS_DISPLAY, true);
        i.setClass(getContext(), SplashActivity.class);
        switch (position) {
            case 0:
                i.putExtra(SplashActivity.KEY_ENABLE_ALPHA_ANIM, true);
                break;
            case 1:
                i.putExtra(SplashActivity.KEY_ENABLE_ALPHA_ANIM, false);
                break;
            default:
                break;
        }
        startActivity(i);
    }
}
