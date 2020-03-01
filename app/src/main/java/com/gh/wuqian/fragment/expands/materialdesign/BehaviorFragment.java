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

package com.gh.wuqian.fragment.expands.materialdesign;

import com.gh.wuqian.base.ComponentContainerFragment;
import com.gh.wuqian.fragment.expands.materialdesign.behavior.TabLayoutBehaviorFragment;
import com.gh.wuqian.fragment.expands.materialdesign.behavior.ToolbarBehaviorFragment;
import com.xuexiang.xpage.annotation.Page;
import com.gh.wuqian.fragment.expands.materialdesign.behavior.BottomNavigationViewBehaviorFragment;
import com.gh.wuqian.fragment.expands.materialdesign.behavior.ComplexDetailsPageFragment;
import com.gh.wuqian.fragment.expands.materialdesign.behavior.RecyclerViewBehaviorFragment;

/**
 * @author XUE
 * @since 2019/5/9 9:11
 */
@Page(name = "Behavior\n手势行为")
public class BehaviorFragment extends ComponentContainerFragment {
    @Override
    protected Class[] getPagesClasses() {
        return new Class[] {
                ToolbarBehaviorFragment.class,
                RecyclerViewBehaviorFragment.class,
                TabLayoutBehaviorFragment.class,
                BottomNavigationViewBehaviorFragment.class,
                ComplexDetailsPageFragment.class
        };
    }

    /**
     * 条目点击
     *
     * @param position
     */
    @Override
    protected void onItemClick(int position) {
        if (position == 0 || position == 4 ) {
            openNewPage(getSimpleDataItem(position));
        } else {
            openPage(getSimpleDataItem(position));
        }
    }
}
