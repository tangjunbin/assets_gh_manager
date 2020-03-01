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

package com.gh.wuqian.fragment.expands;

import com.gh.wuqian.activity.SettingsActivity;
import com.gh.wuqian.base.BaseActivity;
import com.gh.wuqian.base.BaseSimpleListFragment;
import com.xuexiang.xpage.annotation.Page;
import com.gh.wuqian.R;
import com.gh.wuqian.fragment.expands.materialdesign.BehaviorFragment;
import com.gh.wuqian.fragment.expands.materialdesign.BottomSheetDialogFragment;
import com.gh.wuqian.fragment.expands.materialdesign.DrawerLayoutFragment;
import com.gh.wuqian.fragment.expands.materialdesign.ToolBarFragment;
import com.xuexiang.xutil.app.ActivityUtils;

import java.util.List;

/**
 * @author xuexiang
 * @since 2019-05-07 23:30
 */
@Page(name = "Material Design", extra = R.drawable.ic_expand_material_design)
public class MaterialDesignFragment extends BaseSimpleListFragment {
    /**
     * 初始化例子
     *
     * @param lists
     * @return
     */
    @Override
    protected List<String> initSimpleData(List<String> lists) {
        lists.add("ToolBar使用");
        lists.add("Behavior\n手势行为");
        lists.add("DrawerLayout + NavigationView\n常见主页布局");
        lists.add("BottomSheetDialog");
        lists.add("设置页面");
        return lists;
    }

    /**
     * 条目点击
     *
     * @param position
     */
    @Override
    protected void onItemClick(int position) {
        switch(position) {
            case 0:
                openPage(ToolBarFragment.class);
                break;
            case 1:
                openPage(BehaviorFragment.class);
                break;
            case 2:
                openNewPage(DrawerLayoutFragment.class, BaseActivity.KEY_SUPPORT_SLIDE_BACK, false);
                break;
            case 3:
                openPage(BottomSheetDialogFragment.class);
                break;
            case 4:
                ActivityUtils.startActivity(SettingsActivity.class);
                break;
            default:
                break;
        }
    }
}
