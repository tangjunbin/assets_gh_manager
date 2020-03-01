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

package com.gh.wuqian.fragment.expands;

import com.gh.wuqian.base.ComponentContainerFragment;
import com.xuexiang.xpage.annotation.Page;
import com.gh.wuqian.R;
import com.gh.wuqian.fragment.expands.calendar.ChineseCalendarFragment;
import com.gh.wuqian.fragment.expands.calendar.DingDingCalendarFragment;
import com.gh.wuqian.fragment.expands.calendar.MaterialDesignCalendarFragment;
import com.gh.wuqian.fragment.expands.calendar.SimpleCalendarFragment;

/**
 * @author xuexiang
 * @since 2019-05-29 19:48
 */
@Page(name = "日历", extra = R.drawable.ic_expand_calendar)
public class CalendarFragment extends ComponentContainerFragment {
    /**
     * 获取页面的类集合[使用@Page注解进行注册的页面]
     *
     * @return
     */
    @Override
    protected Class[] getPagesClasses() {
        return new Class[] {
                SimpleCalendarFragment.class,
                DingDingCalendarFragment.class,
                MaterialDesignCalendarFragment.class,
                ChineseCalendarFragment.class

        };
    }
}
