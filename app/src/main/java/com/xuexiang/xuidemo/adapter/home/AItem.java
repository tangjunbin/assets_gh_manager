/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
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

package com.xuexiang.xuidemo.adapter.home;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuexiang.xuidemo.R;
import com.xuexiang.xuidemo.fragment.viewmodel.model.DataBase;

/**
 * 首页-资产固定
 *
 * @author wuqian
 * @since 2019/1/6 上午12:29
 */
public class AItem extends AssetsItem<AItem.ViewHolder> {

    private int selectedItemIconTint;
    private int selectedItemTextTint;

    private int normalItemIconTint;
    private int normalItemTextTint;

    private Integer count;
    private Integer color;
    private String name;
    private String prop;


    public AItem(DataBase item, Integer sumNumber, Integer color) {
        this.count = item.getCount();
        this.name = item.getName();
        this.prop = String.format("%.2f", (float)item.getCount()/sumNumber*100)+"%";
        this.color = color;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.home_item_option, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.type_name.setText(name);
        holder.ass_type_number.setText(count+"个");
        holder.color_item.setBackgroundColor(color);
        holder.item_prop.setText(prop);

//        holder.title.setTextColor(isChecked ? selectedItemTextTint : normalItemTextTint);
//        holder.icon.setColorFilter(isChecked ? selectedItemIconTint : normalItemIconTint);
    }

    public AItem withSelectedIconTint(int selectedItemIconTint) {
        this.selectedItemIconTint = selectedItemIconTint;
        return this;
    }

    public AItem withSelectedTextTint(int selectedItemTextTint) {
        this.selectedItemTextTint = selectedItemTextTint;
        return this;
    }

    public AItem withIconTint(int normalItemIconTint) {
        this.normalItemIconTint = normalItemIconTint;
        return this;
    }

    public AItem withTextTint(int normalItemTextTint) {
        this.normalItemTextTint = normalItemTextTint;
        return this;
    }

    static class ViewHolder extends AssetsAdapter.ViewHolder {

        private TextView type_name;
        private TextView ass_type_number;
        private TextView item_prop;
        private ImageView color_item;
        public ViewHolder(View itemView) {
            super(itemView);
            type_name = (TextView) itemView.findViewById(R.id.type_name);
            ass_type_number = (TextView) itemView.findViewById(R.id.ass_type_number);
            color_item = (ImageView)itemView.findViewById(R.id.color_item);
            item_prop = (TextView) itemView.findViewById(R.id.item_prop);
        }
    }
}
