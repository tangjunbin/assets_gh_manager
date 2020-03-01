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

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.gh.wuqian.R;

import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019-05-08 00:13
 */
@Page(name = "ToolBar使用")
public class ToolBarFragment extends BaseFragment {
    @BindView(R.id.tool_bar1)
    Toolbar toolBar1;
    @BindView(R.id.tool_bar_2)
    Toolbar toolBar2;
    @BindView(R.id.tool_bar_3)
    Toolbar toolBar3;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.tool_bar_4)
    Toolbar toolBar4;

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_toolbar;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        initToolbar1();
        initToolbar2();
        initToolbar3();
        initToolbar4();
    }

    private void initToolbar1(){
        //设置NavigationIcon
        toolBar1.setNavigationIcon(R.drawable.ic_navigation_menu);
        // 设置 NavigationIcon 点击事件
        toolBar1.setNavigationOnClickListener(onClickListener);
        toolBar1.setContentInsetStartWithNavigation(0);
        // 设置 toolbar 背景色
        toolBar1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        // 设置 Title
        toolBar1.setTitle(R.string.title_toolbar);
        //  设置Toolbar title文字颜色
        toolBar1.setTitleTextColor(getResources().getColor(R.color.white));
        // 设置Toolbar subTitle
        toolBar1.setSubtitle(R.string.title_toolbar_sub);
        toolBar1.setSubtitleTextColor(getResources().getColor(R.color.white));
        // 设置logo
        toolBar1.setLogo(R.mipmap.ic_launcher);

        //设置 Toolbar menu
        toolBar1.inflateMenu(R.menu.menu_custom);
        // 设置溢出菜单的图标
        toolBar1.setOverflowIcon(getResources().getDrawable(R.drawable.ic_navigation_more));
        // 设置menu item 点击事件
        toolBar1.setOnMenuItemClickListener(menuItemClickListener);
    }

    private void initToolbar2(){
        toolBar2.setNavigationOnClickListener(onClickListener);
        toolBar2.inflateMenu(R.menu.menu_setting);
        toolBar2.setOnMenuItemClickListener(menuItemClickListener);

    }

    private void initToolbar3(){
        toolBar3.setNavigationOnClickListener(onClickListener);
        toolBar3.inflateMenu(R.menu.menu_setting);
        toolBar3.setOnMenuItemClickListener(menuItemClickListener);
    }

    private void initToolbar4(){
        toolBar4.setNavigationOnClickListener(onClickListener);
        toolBar4.inflateMenu(R.menu.menu_search);
        toolBar4.setOnMenuItemClickListener(menuItemClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            XToastUtils.toast("点击了NavigationIcon");
        }
    };

    Toolbar.OnMenuItemClickListener menuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            XToastUtils.toast("点击了:" + item.getTitle());
            switch (item.getItemId()){
                case R.id.item_setting:
                    //点击设置
                    break;
            }
            return false;
        }
    };
}
