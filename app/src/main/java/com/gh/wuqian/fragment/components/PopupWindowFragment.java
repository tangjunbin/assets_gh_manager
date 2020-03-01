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
import com.gh.wuqian.fragment.components.popupwindow.CookieBarFragment;
import com.gh.wuqian.fragment.components.popupwindow.EasyPopFragment;
import com.gh.wuqian.fragment.components.popupwindow.PopupWindowStyleFragment;
import com.gh.wuqian.fragment.components.popupwindow.SnackbarFragment;
import com.gh.wuqian.fragment.components.popupwindow.ViewTipFragment;
import com.gh.wuqian.fragment.components.popupwindow.XToastFragment;
import com.gh.wuqian.fragment.components.popupwindow.XUIPopupFragment;

/**
 * @author xuexiang
 * @date 2017/10/29 下午7:44
 */
@Page(name = "弹出窗", extra = R.drawable.ic_widget_popupwindow)
public class PopupWindowFragment extends ComponentContainerFragment {

    @Override
    public Class[] getPagesClasses() {
        return new Class[]{
                PopupWindowStyleFragment.class,
                ViewTipFragment.class,
                EasyPopFragment.class,
                XUIPopupFragment.class,
                SnackbarFragment.class,
                CookieBarFragment.class,
                XToastFragment.class
        };
    }
}
