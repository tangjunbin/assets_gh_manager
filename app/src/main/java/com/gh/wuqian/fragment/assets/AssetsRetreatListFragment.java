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

package com.gh.wuqian.fragment.assets;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gh.wuqian.adapter.AssetsRetreatListAdapter;
import com.gh.wuqian.adapter.dropdownmenu.CityDropDownAdapter;
import com.xuexiang.xui.utils.ResUtils;
import com.wuqian.xui.widget.spinner.DropDownMenu;
import com.gh.wuqian.R;
import com.gh.wuqian.fragment.viewmodel.AssetsRetreatModel;
import com.gh.wuqian.fragment.viewmodel.model.AssetsPageData;

import butterknife.BindView;

/**
 *
 * 退库
 * @author 五千
 * @since 2019/11/14 下午2:22
 */

public class AssetsRetreatListFragment extends AssetsListFragment {
    @BindView(R.id.ddm_content)
    protected DropDownMenu mDropDownMenu;

    private AssetsRetreatListAdapter mAdapter;

    @Override
    protected void initArgs() {
        mHeaders = new String[]{"默认排序","签字任务"};

        mSort = ResUtils.getStringArray(R.array.asstes_collar_order_entry);
        mSign = ResUtils.getStringArray(R.array.asstes_sign_entry);
    }

    @Override
    protected void initTabLayout() {
        final ListView sortView = new ListView(getContext());
        mSortAdapter = new CityDropDownAdapter(getContext(), mSort);
        sortView.setDividerHeight(0);
        sortView.setAdapter(mSortAdapter);

        //init age menu
        final ListView aSignView = new ListView(getContext());
        aSignView.setDividerHeight(0);
        mSignAdapter = new CityDropDownAdapter(getContext(), mSign);
        aSignView.setAdapter(mSignAdapter);

        mPopupViews.add(sortView);
        mPopupViews.add(aSignView);
        //add item click event
        sortView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSortAdapter.setSelectPosition(position);
                mDropDownMenu.setTabMenuText(position == 0 ? mHeaders[0] : mSort[position],position);

                orderMap.put("sort",mSort[position]);
                getHttpData();
                mDropDownMenu.closeMenu();
            }
        });

        aSignView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSignAdapter.setSelectPosition(position);
                mDropDownMenu.setTabMenuText(position == 0 ? mHeaders[1] : mSign[position],position);

                orderMap.put("sign",mSign[position]);
                getHttpData();
                mDropDownMenu.closeMenu();
            }
        });
    }

    @Override
    protected void initListAdapter() {
        mRecyclerView.setAdapter(mAdapter = new AssetsRetreatListAdapter());
        assetsViewModel = ViewModelProviders.of(this).get(AssetsRetreatModel.class);
        assetsViewModel.mAssetsLiveData.observe(this, new Observer<AssetsPageData>() {
            @Override
            public void onChanged(AssetsPageData assetsPageData) {
                if(assetsPageData.getStatus() == AssetsPageData.Status.LORDMORE) {
                    mAdapter.loadMore(assetsPageData.getListAssetsRetreatBill());
                }else{
                    mAdapter.refresh(assetsPageData.getListAssetsRetreatBill());
                    mRefreshLayout.resetNoMoreData();
                    mLlStateful.showContent();
                    mRefreshLayout.setEnableLoadMore(true);
                }
            }
        });
    }
}