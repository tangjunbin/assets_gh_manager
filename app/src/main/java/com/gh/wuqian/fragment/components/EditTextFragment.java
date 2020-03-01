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

package com.gh.wuqian.fragment.components;

import com.gh.wuqian.base.ComponentContainerFragment;
import com.xuexiang.xpage.annotation.Page;
import com.gh.wuqian.R;
import com.gh.wuqian.fragment.components.edittext.CustomEditTextFragment;
import com.gh.wuqian.fragment.components.edittext.EditTextStyleFragment;
import com.gh.wuqian.fragment.components.edittext.MaterialEditTextFragment;
import com.gh.wuqian.fragment.components.edittext.VerifyCodeEditTextFragment;

/**
 * 输入框组件
 *
 * @author xuexiang
 * @since 2018/11/26 下午5:48
 */
@Page(name = "输入框", extra = R.drawable.ic_widget_edittext)
public class EditTextFragment extends ComponentContainerFragment {

    @Override
    public Class[] getPagesClasses() {
        return new Class[]{
                EditTextStyleFragment.class,
                CustomEditTextFragment.class,
                MaterialEditTextFragment.class,
                VerifyCodeEditTextFragment.class
        };
    }
}
