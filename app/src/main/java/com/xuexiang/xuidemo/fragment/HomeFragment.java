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

package com.xuexiang.xuidemo.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xuidemo.R;
import com.xuexiang.xuidemo.activity.MainActivity;
import com.xuexiang.xuidemo.adapter.home.AItem;
import com.xuexiang.xuidemo.adapter.home.AssetsAdapter;
import com.xuexiang.xuidemo.adapter.home.AssetsItem;
import com.xuexiang.xuidemo.adapter.menu.SimpleItem;
import com.xuexiang.xuidemo.fragment.expands.chart.BaseChartFragment;
import com.xuexiang.xuidemo.fragment.viewmodel.HomeModel;
import com.xuexiang.xuidemo.fragment.viewmodel.model.DataBase;
import com.xuexiang.xuidemo.fragment.viewmodel.model.HomeData;
import com.xuexiang.xuidemo.utils.DynamicTimeFormat;
import com.xuexiang.xuidemo.utils.api.OKHttpApiHttpService;

import butterknife.BindView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 主界面
 *
 * @author 五千
 * @since 2019/11/14 下午2:22
 */
@Page(name = "固定资产管理系统", anim = CoreAnim.none)
public class HomeFragment extends BaseChartFragment implements OnChartValueSelectedListener, AssetsAdapter.OnItemSelectedListener{

    private String TAG="HomeFragment";
    private HomeModel homeViewModel;
    private ClassicsHeader mClassicsHeader;
    private Drawable mDrawableProgress;
    private AssetsAdapter mAdapter;
    private String[] mAItemTitles;

    private static final int POS_USED = 0;
    private static final int POS_IDLE = 1;
    private static final int POS_BORROW = 2;
    private static final int POS_CLEARSCRAP = 3;

    @BindView(R.id.assets_chart1)
    PieChart assets_chart1;

    @BindView(R.id.inventory_cnt)
    TextView inventory_cnt;

    @BindView(R.id.approval_cnt)
    TextView approval_cnt;

    @BindView(R.id.home_list)
    RecyclerView list_v;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            return;
        }else {
            Log.i(TAG, "framage状态切换");
            homeViewModel.finishRefresh(new OKHttpApiHttpService.Callback() {
                @Override
                public void onSuccess(String result) {
                    Log.i(TAG, result);
                    homerefreshLayout.finishRefresh();
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
        }
    }

    @BindView(R.id.sign_cnt)
    TextView sign_cnt;

    @BindView(R.id.homerefreshLayout)
    SmartRefreshLayout homerefreshLayout;


    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.setLeftImageResource(R.drawable.ic_action_menu);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            @SingleClick
            public void onClick(View v) {
                getContainer().openMenu();
            }
        });
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.icon_action_about) {
            @Override
            @SingleClick
            public void performAction(View view) {
                openNewPage(AboutFragment.class);
            }
        });
        return titleBar;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
    /**
     * 初始化图表的样式
     */
    protected void initChartStyle(Integer total) {
//使用百分百显示
        //assets_chart1.setUsePercentValues(true);
        assets_chart1.getDescription().setEnabled(false);
        assets_chart1.setExtraOffsets(5, 10, 5, 5);

        //设置拖拽的阻尼，0为立即停止
        assets_chart1.setDragDecelerationFrictionCoef(0.95f);

        //设置图标中心文字
        assets_chart1.setCenterText(generateCenterSpannableText(total));
        assets_chart1.setDrawCenterText(true);
        //设置图标中心空白，空心
        assets_chart1.setDrawHoleEnabled(true);
        //设置空心圆的弧度百分比，最大100
        assets_chart1.setHoleRadius(58f);
        assets_chart1.setHoleColor(Color.WHITE);
        //设置透明弧的样式
        assets_chart1.setTransparentCircleColor(Color.WHITE);
        assets_chart1.setTransparentCircleAlpha(110);
        assets_chart1.setTransparentCircleRadius(61f);

        //设置可以旋转
        assets_chart1.setRotationAngle(0);
        assets_chart1.setRotationEnabled(true);
        assets_chart1.setHighlightPerTapEnabled(true);
    }

    @Override
    protected void initChartStyle() {

    }

    /**
     * 初始化图表的 标题
     */
    @Override
    protected void initChartLabel() {
        Legend l = assets_chart1.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        assets_chart1.setEntryLabelColor(Color.WHITE);
        assets_chart1.setEntryLabelTextSize(12f);
    }

    @Override
    protected void setChartData(int count, float range) {

    }
    /**
     * 生成饼图中间的文字
     *
     * @return
     */
    private SpannableString generateCenterSpannableText(Integer total) {
        //中间文字样式
        SpannableString s = new SpannableString(total+"\n固定资产数量");
        s.setSpan(new RelativeSizeSpan(3f), 0, 2, 0);
        //s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        //s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        //s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        //s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        //s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }
    public MainActivity getContainer() {
        return (MainActivity) getActivity();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    //
    /**
     * 设置图表数据
     *
     */
    protected void setChartPieData(List<DataBase> dataChart) {
        List<PieEntry> entries = new ArrayList<>();

        for(DataBase item:dataChart){
            //设置数据源
            entries.add(new PieEntry(item.getCount(), item.getName(), getResources().getDrawable(R.drawable.ic_star_green)));
        }

        PieDataSet dataSet = new PieDataSet(entries,"");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        List<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(assets_chart1));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        assets_chart1.setData(data);

        // undo all highlights
        assets_chart1.highlightValues(null);
        assets_chart1.invalidate();
    }
    private void initViewsList(List<DataBase> list){

        Integer sumNumber = list.get(POS_USED).getCount()+
                list.get(POS_IDLE).getCount()+list.get(POS_BORROW).getCount()+list.get(POS_CLEARSCRAP).getCount();
        mAdapter = new AssetsAdapter(Arrays.asList(
                createItemFor(list.get(POS_USED),sumNumber,ColorTemplate.PASTEL_COLORS[POS_USED]).setCode(list.get(POS_USED).getCode()),
                createItemFor(list.get(POS_IDLE),sumNumber,ColorTemplate.PASTEL_COLORS[POS_IDLE]).setCode(list.get(POS_IDLE).getCode()),
                createItemFor(list.get(POS_BORROW),sumNumber,ColorTemplate.PASTEL_COLORS[POS_BORROW]).setCode(list.get(POS_BORROW).getCode()),
                createItemFor(list.get(POS_CLEARSCRAP),sumNumber,ColorTemplate.PASTEL_COLORS[POS_CLEARSCRAP]).setCode(list.get(POS_CLEARSCRAP).getCode())
        ));
        mAdapter.setListener(this);
//
//        list_v.setNestedScrollingEnabled(false);
        list_v.setLayoutManager(new LinearLayoutManager(getActivity()));
        list_v.setAdapter(mAdapter);
    }
    private AssetsItem createItemFor(DataBase item,Integer number,Integer color){

        return new AItem(item,number,color)
                .withTextTint(ThemeUtils.resolveColor(getActivity(), R.attr.xui_config_color_content_text));
    }

    @Override
    protected void initViews() {

        mClassicsHeader = (ClassicsHeader) homerefreshLayout.getRefreshHeader();
        int delta = new Random().nextInt(7 * 24 * 60 * 60 * 1000);
        mClassicsHeader.setLastUpdateTime(new Date(System.currentTimeMillis() - delta));
        mClassicsHeader.setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));
        mClassicsHeader.setTimeFormat(new DynamicTimeFormat("更新于 %s"));

        mDrawableProgress = ((ImageView) mClassicsHeader.findViewById(ClassicsHeader.ID_IMAGE_PROGRESS)).getDrawable();
        if (mDrawableProgress instanceof LayerDrawable) {
            mDrawableProgress = ((LayerDrawable) mDrawableProgress).getDrawable(0);
        }

        //监听数据获取
        homeViewModel = ViewModelProviders.of(this).get(HomeModel.class);

        homeViewModel.mHomeLiveData.observe(this, new Observer<HomeData>() {
            @Override
            public void onChanged(HomeData homeData) {
                Log.e(TAG,"ModeView 数据获取成功");
                inventory_cnt.setText(homeData.getBillCntByCode("inventory").toString());
                approval_cnt.setText(homeData.getBillCntByCode("approval").toString());
                sign_cnt.setText(homeData.getBillCntByCode("sign").toString());

                initChartStyle(homeData.getTotal());
                initChartLabel();
                //setChartData(4,1);
                setChartPieData(homeData.getAssetsInfo());
                initViewsList(homeData.getAssetsInfo());
            }
        });
        assets_chart1.animateY(1400, Easing.EaseInOutQuad);
        assets_chart1.setOnChartValueSelectedListener(this);

        homerefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.e(TAG,"xxxxxxxxxxxxxxxxxxxxx=========");
                homeViewModel.finishRefresh(new OKHttpApiHttpService.Callback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG,result);
                        homerefreshLayout.finishRefresh();
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                });

            }
        });

    }

    /**
     * 点击首页资产分类list
     * @param typeCode
     */
    @Override
    public void onItemSelected(String typeCode) {
        Log.e(TAG,"asdjajsdkfjlasdjf__"+typeCode);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.switchPage(AssetsFragment.class);
    }
}
