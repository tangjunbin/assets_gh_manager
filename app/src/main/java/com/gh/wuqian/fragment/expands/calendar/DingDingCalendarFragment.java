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

package com.gh.wuqian.fragment.expands.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.codbking.calendar.CaledarAdapter;
import com.codbking.calendar.CalendarDate;
import com.codbking.calendar.CalendarDateView;
import com.codbking.calendar.CalendarView;
import com.gh.wuqian.DemoDataProvider;
import com.gh.wuqian.adapter.SimpleRecyclerAdapter;
import com.gh.wuqian.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.DensityUtils;
import com.gh.wuqian.R;

import java.util.Date;

import butterknife.BindView;

/**
 * @author xuexiang
 * @since 2019-05-29 19:53
 */
@Page(name = "叮叮日历")
public class DingDingCalendarFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.calendarDateView)
    CalendarDateView calendarDateView;
    @BindView(R.id.listview)
    ListView listview;

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ding_ding_calendar;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        calendarDateView.setAdapter(new CaledarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarDate calendarDate) {
                TextView textView;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.adapter_calendar_item, null);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtils.dp2px(48), DensityUtils.dp2px(48));
                    convertView.setLayoutParams(params);
                }

                textView = convertView.findViewById(R.id.tv_text);
                textView.setBackgroundResource(R.drawable.bg_calendar_ding_ding_item);

                textView.setText(String.valueOf(calendarDate.day));
                if (calendarDate.monthFlag != 0) {
                    textView.setTextColor(0xFF9299A1);
                } else {
                    textView.setTextColor(0xFFFFFFFF);
                }

                return convertView;
            }
        });

        calendarDateView.setOnCalendarSelectedListener(new CalendarView.OnCalendarSelectedListener() {
            @Override
            public void onCalendarSelected(View view, int position, CalendarDate calendarDate) {
                tvTitle.setText(String.format("%d/%d/%d", calendarDate.year, calendarDate.month, calendarDate.day));
            }
        });

        calendarDateView.setOnMonthChangedListener(new CalendarDateView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(View view, int postion, CalendarDate date) {
                tvTitle.setText(String.format("%d/%d/%d", date.year, date.month, date.day));
            }
        });

        CalendarDate data = CalendarDate.get(new Date());
        tvTitle.setText(String.format("%d/%d/%d", data.year, data.month, data.day));


        listview.setAdapter(new SimpleRecyclerAdapter(DemoDataProvider.getDemoData()));

    }


}