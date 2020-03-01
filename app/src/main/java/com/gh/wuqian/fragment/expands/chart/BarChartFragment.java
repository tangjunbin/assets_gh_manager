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

package com.gh.wuqian.fragment.expands.chart;

import com.gh.wuqian.base.ComponentContainerFragment;
import com.xuexiang.xpage.annotation.Page;
import com.gh.wuqian.fragment.expands.chart.bar.BasicBarChartFragment;
import com.gh.wuqian.fragment.expands.chart.bar.SimpleBarChartFragment;
import com.gh.wuqian.fragment.expands.chart.bar.HorizontalBarChartFragment;

/**
 * @author xuexiang
 * @since 2019/4/10 上午12:00
 */
@Page(name = "BarChart\n柱状图")
public class BarChartFragment extends ComponentContainerFragment {
    /**
     * 获取页面的类集合[使用@Page注解进行注册的页面]
     *
     * @return
     */
    @Override
    protected Class[] getPagesClasses() {
        return new Class[] {
                BasicBarChartFragment.class,
                SimpleBarChartFragment.class,
                HorizontalBarChartFragment.class
        };
    }
}
