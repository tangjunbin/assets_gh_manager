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

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xuidemo.R;
import com.xuexiang.xuidemo.adapter.entity.AssetsData;
import com.xuexiang.xuidemo.adapter.entity.NewInfo;

/**
 * @author wuqian
 * @since 2019/5/9 10:41
 */
public class AssetsListAdapter extends BaseRecyclerAdapter<AssetsData> {

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.adapter_assets_card_view_list_item;
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, AssetsData model) {
        if (model != null) {
            holder.text(R.id.tv_assets_name, model.getName());
            holder.text(R.id.assest_no, model.getNo());

            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.ic_error_outline_white_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(holder.getContext())
                    .load(model.getAssetsImage())
                    .apply(options)
                    .into(holder.getImageView(R.id.iv_assets_image));

            holder.text(R.id.assets_type, model.getAssetsType());
            holder.text(R.id.assets_use_company, model.getUseCompany());
            holder.text(R.id.assets_use_department, model.getUseDepartment());
            holder.text(R.id.assets_use_name, model.getUseUserName());
            holder.text(R.id.assets_storage_location, model.getStorageLocation());

            holder.text(R.id.assets_status,model.getStatus());
            holder.text(R.id.sign_status,model.getSign());

            if (position==0)
                holder.getView(R.id.total_info).setVisibility(View.VISIBLE);
            else
                holder.getView(R.id.total_info).setVisibility(View.GONE);


        }
    }

}
