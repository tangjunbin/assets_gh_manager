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

package com.gh.wuqian.activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gh.wuqian.ConfigBase;
import com.gh.wuqian.adapter.AssetsListAdapter;
import com.gh.wuqian.adapter.FlowTagAdapter;
import com.gh.wuqian.base.BaseAppCompatActivity;
import com.gh.wuqian.utils.TokenUtils;
import com.gh.wuqian.utils.Utils;
import com.gh.wuqian.utils.XToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;
import com.gh.wuqian.adapter.entity.AssetsData;
import com.xuexiang.xui.widget.searchview.MaterialSearchView;
import com.gh.wuqian.R;
import com.gh.wuqian.utils.api.OKHttpApiHttpService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author 五千
 * @date 2019/01/02 09点42分
 */
public class SearchViewActivity extends BaseAppCompatActivity {

    private String TAG="SearchViewActivity";
    @BindView(R.id.action_clear_btn)
    AppCompatImageButton mClearBtn;

    @BindView(R.id.home_left_scan)
    ImageButton homeLeftScan;

    @BindView(R.id.search_text_in)
    EditText searchText;

    @BindView(R.id.search_close)
    TextView searchClose;

    @BindView(R.id.tag_list)
    LinearLayout tag_list;

    @BindView(R.id.recycler_search_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.searchll_stateful)
    StatefulLayout mLlStateful;
    @BindView(R.id.search_list_layout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.search_history_display)
    FlowTagLayout mDisplayFlowTagLayout;

    @BindView(R.id.page_total)
    TextView page_total;

    private Unbinder mUnbinder;
    private CharSequence mOldQueryText;
    private CharSequence mUserQuery;

    private AssetsListAdapter mAdapter;

    private FlowTagAdapter tagAdapter;
    private CharSequence keywords_query;

    private MaterialSearchView.OnQueryTextListener mOnQueryChangeListener;
    private MaterialSearchView.SearchViewListener mSearchViewListener;

    private Integer p = 1;
    private Integer limit = 10;//每此获取的条数

    private enum Status {
        SUCCESS,
        EMPTY,
        ERROR,
        NO_NET,
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        mUnbinder = ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        homeLeftScan.setImageDrawable(Utils.tintDrawable(this,R.mipmap.icon_scan, ColorStateList.valueOf(Color.BLACK)));

        initTag();
        initSearchView();
        initList();
    }

    private void initTag(){
        //初始化 历史搜索标签
        String falgs = TokenUtils.getHistoryFlag();
        tagAdapter = new FlowTagAdapter(this);
        mDisplayFlowTagLayout.setAdapter(tagAdapter);
        if(!falgs.equals("")) {
            tag_list.setVisibility(View.VISIBLE);
            tagAdapter.addTags(falgs.split(","));
        }
        mDisplayFlowTagLayout.setOnTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                //XToastUtils.toast("点击了：" + parent.getAdapter().getItem(position));

                keywords_query = (CharSequence) parent.getAdapter().getItem(position);
                searchText.setText(keywords_query);
                mRefreshLayout.autoRefresh();

            }
        });
    }

    /**
     * 初始化搜索列表
     */
    private void initList(){
        WidgetUtils.initRecyclerView(mRecyclerView);

        mRecyclerView.setAdapter(mAdapter = new AssetsListAdapter());

        //下拉刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                p = 1;//刷新 页数 赋 0
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getHttpData(refreshLayout, new Callback() {
                            @Override
                            public void onSuccess(String jsonString) {
                                List<AssetsData> list = JSONObject.parseArray(jsonString, AssetsData.class);
                                mAdapter.refresh(list);
                                mRefreshLayout.resetNoMoreData();//setNoMoreData(false);
                                mLlStateful.showContent();
                                mRefreshLayout.setEnableLoadMore(true);
                            }
                            @Override
                            public void onReturnEmpty() {
                            }
                        });
                    }
                }, 100);
            }
        });
        //上拉加载
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getHttpData(refreshLayout, new Callback() {
                                    @Override
                                    public void onSuccess(String jsonString) {
                                        List<AssetsData> list = JSONObject.parseArray(jsonString, AssetsData.class);
                                        mAdapter.loadMore(list);
                                        refreshLayout.finishLoadMore();
                                    }

                                    @Override
                                    public void onReturnEmpty() {
                                    }
                        });

                    }
                }, 100);
            }
        });
        //mRefreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }

    private void getHttpData(final RefreshLayout refreshLayout,Callback callBack){
        OKHttpApiHttpService http = new OKHttpApiHttpService();
        Map<String, Object> params = new TreeMap<>();
        params.put("keywords",keywords_query);
        params.put("p",p);
        params.put("limit",limit);
        http.asyncGet(ConfigBase.host_url + ConfigBase.Api.Assets_query,
                params, new OKHttpApiHttpService.Callback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG,"成功===="+params+result);

                        JSONObject resultObj = JSONObject.parseObject(result);
                        Integer code = (Integer) resultObj.get("Code");
                        String message = resultObj.getString("Msg");
                        JSONObject data_a  = resultObj.getJSONObject("Data");
                        Integer totalNumber = data_a.getInteger("totalNumber");
                        JSONArray result_list = data_a.getJSONArray("lists");
                        if(code == 1) {
                            page_total.setText("共"+totalNumber+"条记录");
                            p ++;//翻页 +1
                            if(result_list.size()>0){
                                callBack.onSuccess(JSONObject.toJSONString(result_list));
                            }else{
                                XToastUtils.toast("数据全部加载完毕");
                                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                                callBack.onReturnEmpty();
                            }

                        }else{
                            showError();
                        }
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        showError();
                        refreshLayout.finishRefresh();
                    }
                });
    }
    /**
     * 初始化 搜索框
     */
    private void initSearchView() {
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                onSubmitQuery();
                return true;
            }
        });

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                startFilter(s);?
                SearchViewActivity.this.onTextChanged(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
//                    showKeyboard(mSearchSrcTextView);
//                    showSuggestions();
                }
            }
        });
    }
    private void onSubmitQuery() {
        CharSequence query = searchText.getText();
        if (query != null && TextUtils.getTrimmedLength(query) > 0) {
            if (mOnQueryChangeListener == null || !mOnQueryChangeListener.onQueryTextSubmit(query.toString())) {
                closeSearch();
                tag_list.setVisibility(View.GONE);
                TokenUtils.addHistoryFlag(query.toString());
                tagAdapter.addTag(query.toString());
                keywords_query = query;
                mRefreshLayout.autoRefresh();
            }
        }
    }
    /**
     * 关闭搜索框
     * Close search view.
     */
    public void closeSearch() {
        hideKeyboard(searchText);
        searchText.clearFocus();
    }
    private void onTextChanged(CharSequence newText) {
        CharSequence text = searchText.getText();
        mUserQuery = text;
        boolean hasText = !TextUtils.isEmpty(text);
        if (hasText) {
            mClearBtn.setVisibility(View.VISIBLE);
            tag_list.setVisibility(View.GONE);
        } else {
            mClearBtn.setVisibility(View.GONE);
            tag_list.setVisibility(View.VISIBLE);
        }

        if (mOnQueryChangeListener != null && !TextUtils.equals(newText, mOldQueryText)) {
            mOnQueryChangeListener.onQueryTextChange(newText.toString());
        }
        mOldQueryText = newText.toString();
    }

    /**
     * 设置查询监听
     * Set this listener to listen to Query Change events.
     *
     * @param listener
     */
    public void setOnQueryTextListener(MaterialSearchView.OnQueryTextListener listener) {
        mOnQueryChangeListener = listener;
    }

    /**
     * 设置搜索框打开关闭监听
     * Set this listener to listen to Search View open and close events
     *
     * @param listener
     */
    public void setOnSearchViewListener(MaterialSearchView.SearchViewListener listener) {
        mSearchViewListener = listener;
    }
    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void showOffline() {
        mLlStateful.showOffline(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshLayout.autoRefresh();
            }
        });
        mRefreshLayout.setEnableLoadMore(false);
    }

    private void showError() {
        mLlStateful.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshLayout.autoRefresh();
            }
        });
        mRefreshLayout.setEnableLoadMore(false);
    }

    @OnClick(R.id.search_close)
    public void onClick() {
        closeSearch();
        finish();
    }
    @OnClick({R.id.btn_clear_log_tag,R.id.action_clear_btn})
    void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_clear_log_tag:
                mDisplayFlowTagLayout.clearTags();
                TokenUtils.clearHistoryFlag();
                tag_list.setVisibility(View.GONE);
                break;
            case R.id.action_clear_btn:
                searchText.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        if (mSearchView.isSearchOpen()) {
//            mSearchView.closeSearch();
//        } else {
//            super.onBackPressed();
//        }
    }


    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    /**
     * 网络请求回调
     */
    public interface Callback {
        /**
         * 结果回调
         *
         * @param result 结果
         */
        void onSuccess(String result);

        /**
         * 结果回调 没有数据返回
         *
         */
        void onReturnEmpty();

    }
}
