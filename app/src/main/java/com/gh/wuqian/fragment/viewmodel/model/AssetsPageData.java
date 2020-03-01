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

package com.gh.wuqian.fragment.viewmodel.model;

import com.gh.wuqian.adapter.entity.AssetsCollarUseData;
import com.gh.wuqian.adapter.entity.AssetsData;
import com.gh.wuqian.adapter.entity.AssetsRetreatData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AssetsPageData implements Serializable {
    private String TAG = "AssetsPageData";
    /**
     * 总数量
     */
    private int totalNum = 0;

    private Status status = Status.REFESH;

    public enum Status {
        REFESH,
        LORDMORE
    }
    private List<AssetsData> listAssets = new ArrayList<AssetsData>();
    private List<AssetsCollarUseData> listAssetsCollarBill = new ArrayList<AssetsCollarUseData>();//领用单
    private List<AssetsRetreatData> listAssetsRetreatBill = new ArrayList<AssetsRetreatData>();//退库单

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<AssetsData> getOnePageAssets() {
        return listAssets;
    }

    public void setOnePageAssets(List<AssetsData> onePageAssets) {
        this.listAssets = onePageAssets;
    }

    public List<AssetsRetreatData> getListAssetsRetreatBill() {
        return listAssetsRetreatBill;
    }

    public void setListAssetsRetreatBill(List<AssetsRetreatData> listAssetsRetreatBill) {
        this.listAssetsRetreatBill = listAssetsRetreatBill;
    }

    public List<AssetsCollarUseData> getListAssetsCollarBill() {
        return listAssetsCollarBill;
    }

    public void setListAssetsCollarBill(List<AssetsCollarUseData> listAssetsCollarBill) {
        this.listAssetsCollarBill = listAssetsCollarBill;
    }

    /**
     * 获取资产数量 当页
     * @return
     */
    public int getPageNumber(){
        return this.listAssets.size();
    }

    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }
}
