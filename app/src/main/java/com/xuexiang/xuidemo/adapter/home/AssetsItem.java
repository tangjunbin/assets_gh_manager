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

import android.view.ViewGroup;

import com.xuexiang.xuidemo.adapter.menu.DrawerAdapter;

/**
 * 首页资产item
 *
 * @author wuqian
 * @since 2019/1/6 上午12:28
 */
public abstract class AssetsItem<T extends AssetsAdapter.ViewHolder> {

    protected boolean isChecked;
    protected String typeCode;

    public String getCode() {
        return typeCode;
    }

    public AssetsItem setCode(String code) {
        this.typeCode = code;
        return this;
    }



    public abstract T createViewHolder(ViewGroup parent);

    public abstract void bindViewHolder(T holder);

    public AssetsItem setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isSelectable() {
        return true;
    }
}
