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

package com.xuexiang.xuidemo.adapter;

import androidx.annotation.NonNull;

import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xuidemo.R;
import com.xuexiang.xuidemo.adapter.entity.AssetsCollarUseData;
import com.xuexiang.xuidemo.adapter.entity.AssetsRetreatData;

/**
 * @author wuqian
 * @since 2019/5/9 10:41
 */
public class AssetsRetreatListAdapter extends BaseRecyclerAdapter<AssetsRetreatData> {

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.adapter_assets_card_view_retreat_item;
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, AssetsRetreatData model) {
        if (model != null) {
            holder.text(R.id.retreat_sign, model.getSign());
            holder.text(R.id.retreat_billno, model.getRetreatBillNo());
            holder.text(R.id.retreat_createuser, model.getCreateUser());
            holder.text(R.id.retreat_collarnum, model.getRetreatNum());
            if(model.getRetreatDate()!=null)
                holder.text(R.id.retreat_collardate, model.getRetreatDate());
            if(model.getUseComplay()!=null)
                holder.text(R.id.retreat_usecomplay, model.getUseComplay());
            if(model.getRetreatRegion()!=null)
                holder.text(R.id.collar_useregion, model.getRetreatRegion());
            if(model.getRetreatPlace()!=null)
                holder.text(R.id.collar_useplace, model.getRetreatPlace());
        }
    }

}
