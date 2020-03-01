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

package com.gh.wuqian.fragment.expands.chart.bar;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

/**
 *
 *
 * @author xuexiang
 * @since 2019/4/10 下午11:34
 */
public class MoneyValueFormatter extends ValueFormatter {

    private final DecimalFormat mFormat;
    private String suffix;

    public MoneyValueFormatter(String suffix) {
        mFormat = new DecimalFormat("###,###,###,##0.0");
        this.suffix = suffix;
    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value) + suffix;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        if (axis instanceof XAxis) {
            return mFormat.format(value);
        } else if (value > 0) {
            return mFormat.format(value) + suffix;
        } else {
            return mFormat.format(value);
        }
    }
}
