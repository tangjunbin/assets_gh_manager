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

package com.gh.wuqian.adapter;

import androidx.annotation.NonNull;

import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.gh.wuqian.R;
import com.gh.wuqian.adapter.entity.AssetsCollarUseData;

/**
 * @author wuqian
 * @since 2019/5/9 10:41
 */
public class AssetsCollarUseListAdapter extends BaseRecyclerAdapter<AssetsCollarUseData> {

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.adapter_assets_card_view_collar_list_item;
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, AssetsCollarUseData model) {
        if (model != null) {
            holder.text(R.id.collar_sign, model.getSign());
            holder.text(R.id.collar_billno, model.getCollarBillNo());
            holder.text(R.id.collar_createuser, model.getCreateUser());
            holder.text(R.id.collar_collaruser, model.getCollarUser());
            holder.text(R.id.collar_collarnum, model.getCollarNum());
            holder.text(R.id.collar_collardate, model.getCollarDate());
            holder.text(R.id.collar_usecomplay, model.getUseComplay());
            if(model.getUseRegion()!=null)
                holder.text(R.id.collar_useregion, model.getUseRegion());
            if(model.getUsePlace()!=null)
                holder.text(R.id.collar_useplace, model.getUsePlace());
        }
    }

}
