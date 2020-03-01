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

package com.gh.wuqian.fragment.assets;


import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.wuqian.xui.widget.spinner.DropDownMenu;
import com.gh.wuqian.R;

import butterknife.BindView;

/**
 * 资产管理系统
 *
 * @author 五千
 * @since 2019/11/14 下午2:22
 */
@Page(name = "资产列表", anim = CoreAnim.none)
public class AssetsBorrowListFragment extends XPageFragment {


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
