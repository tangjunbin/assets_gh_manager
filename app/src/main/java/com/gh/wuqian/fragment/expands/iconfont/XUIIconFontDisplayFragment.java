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

package com.gh.wuqian.fragment.expands.iconfont;

import androidx.recyclerview.widget.RecyclerView;

import com.gh.wuqian.adapter.IconFontGridAdapter;
import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.widget.iconfont.XUIIconFont;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.gh.wuqian.R;

import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019-10-13 18:55
 */
@Page(name = "XUIIconFont展示")
public class XUIIconFontDisplayFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xui_iconfont_display;
    }

    @Override
    protected void initViews() {
        WidgetUtils.initGridRecyclerView(recyclerView, 3, DensityUtils.dp2px(2));

        recyclerView.setAdapter(new IconFontGridAdapter(XUIIconFont.Icon.values()));
    }

}
