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

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.gh.wuqian.R;

/**
 * 柱状图的数字指示器
 *
 * @author xuexiang
 * @since 2019/4/10 下午11:34
 */
@SuppressLint("ViewConstructor")
public class XYMarkerView extends MarkerView {

    private final TextView tvContent;
    private final ValueFormatter xAxisValueFormatter;
    private final ValueFormatter yAxisValueFormatter;

    public XYMarkerView(Context context, ValueFormatter xAxisValueFormatter, ValueFormatter yAxisValueFormatter) {
        super(context, R.layout.marker_view_xy);
        this.xAxisValueFormatter = xAxisValueFormatter;
        this.yAxisValueFormatter = yAxisValueFormatter;
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(String.format("x: %s, y: %s", xAxisValueFormatter.getFormattedValue(e.getX()), yAxisValueFormatter.getFormattedValue(e.getY())));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() >> 1), -getHeight());
    }
}
