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

import com.xuexiang.xpage.model.PageInfo;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.gh.wuqian.R;

import java.util.List;

/**
 * @author XUE
 * @date 2017/9/10 15:28
 */
public class WidgetItemAdapter extends BaseRecyclerAdapter<PageInfo> {

    public WidgetItemAdapter(List<PageInfo> list) {
        super(list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.layout_widget_item;
    }

    @Override
    public void bindData(@NonNull RecyclerViewHolder holder, int position, PageInfo item) {
        holder.text(R.id.item_name, item.getName());
        if (item.getExtra() != 0) {
            holder.image(R.id.item_icon, item.getExtra());
        }
    }

}
