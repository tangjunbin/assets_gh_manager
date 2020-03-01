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
import com.gh.wuqian.fragment.components.progress.ArcLoadingViewFragment;
import com.gh.wuqian.fragment.components.progress.BeautifulProgressBarFragment;
import com.gh.wuqian.fragment.components.progress.DeterminateCircularFragment;
import com.gh.wuqian.fragment.components.progress.MaterialProgressBarFragment;
import com.gh.wuqian.fragment.components.progress.RatingBarFragment;
import com.gh.wuqian.fragment.components.progress.RotateLoadingViewFragment;
import com.xuexiang.xpage.annotation.Page;
import com.gh.wuqian.R;

/**
 * @author xuexiang
 * @since 2018/11/26 下午1:47
 */
@Page(name = "进度条", extra = R.drawable.ic_widget_loading)
public class ProgressBarFragment extends ComponentContainerFragment {
    @Override
    public Class[] getPagesClasses() {
        return new Class[]{
                ArcLoadingViewFragment.class,
                RotateLoadingViewFragment.class,
                MaterialProgressBarFragment.class,
                DeterminateCircularFragment.class,
                RatingBarFragment.class,
                BeautifulProgressBarFragment.class
        };
    }
}
