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

package com.xuexiang.xuidemo.fragment.assets;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.wuqian.xui.widget.spinner.DropDownMenu;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;
import com.xuexiang.xuidemo.R;
import com.xuexiang.xuidemo.activity.MainActivity;
import com.xuexiang.xuidemo.activity.SearchViewActivity;
import com.xuexiang.xuidemo.adapter.AssetsListAdapter;
import com.xuexiang.xuidemo.base.BaseFragment;
import com.xuexiang.xuidemo.fragment.AboutFragment;
import com.xuexiang.xuidemo.fragment.components.tabbar.tabsegment.MultiPage;
import com.xuexiang.xuidemo.fragment.viewmodel.AssetsModel;
import com.xuexiang.xuidemo.fragment.viewmodel.model.AssetsPageData;
import com.xuexiang.xuidemo.utils.XToastUtils;

import butterknife.BindView;

import static com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE;

/**
 * 资产管理系统
 *
 * @author 五千
 * @since 2019/11/14 下午2:22
 */
@Page(name = "资产列表", anim = CoreAnim.none)
public class AssetsCleanUpListFragment extends XPageFragment {

    @BindView(R.id.ddm_content)
    DropDownMenu mRecyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_assets_list;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }
}
