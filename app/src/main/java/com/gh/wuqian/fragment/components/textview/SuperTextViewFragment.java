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

package com.gh.wuqian.fragment.components.textview;

import com.gh.wuqian.base.ComponentContainerFragment;
import com.gh.wuqian.fragment.components.textview.supertextview.SuperButtonFragment;
import com.gh.wuqian.fragment.components.textview.supertextview.SuperClickFragment;
import com.gh.wuqian.fragment.components.textview.supertextview.SuperNetPictureLoadingFragment;
import com.gh.wuqian.fragment.components.textview.supertextview.SuperTextCommonUseFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * SuperTextView演示
 *
 * @author xuexiang
 * @since 2018/11/29 上午12:29
 */
@Page(name = "可拓展的TextView")
public class SuperTextViewFragment extends ComponentContainerFragment {
    @Override
    public Class[] getPagesClasses() {
        return new Class[]{
                SuperClickFragment.class,
                SuperButtonFragment.class,
                SuperTextCommonUseFragment.class,
                SuperNetPictureLoadingFragment.class
        };
    }

}
