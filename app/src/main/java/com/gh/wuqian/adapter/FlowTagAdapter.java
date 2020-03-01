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

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xuexiang.xui.widget.flowlayout.BaseTagAdapter;
import com.gh.wuqian.R;

/**
 * @author xuexiang
 * @date 2017/11/21 上午10:44
 */
public class FlowTagAdapter extends BaseTagAdapter<String, TextView> {

    public FlowTagAdapter(Context context) {
        super(context);
    }

    @Override
    protected TextView newViewHolder(View convertView) {
        return (TextView) convertView.findViewById(R.id.tv_tag);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.adapter_item_tag;
    }

    @Override
    protected void convert(TextView textView, String item, int position) {
        textView.setText(item);
    }
}
