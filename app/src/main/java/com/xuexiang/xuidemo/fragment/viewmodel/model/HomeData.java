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

package com.xuexiang.xuidemo.fragment.viewmodel.model;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeData implements Serializable {
    private String TAG = "HomeData";
    private ArrayList<DataBase> billList = new ArrayList<DataBase>();
    private ArrayList<DataBase> assetsInfo = new ArrayList<DataBase>();

    public List<DataBase> getBillList() {
        return billList;
    }

    public void setBillList(ArrayList<DataBase> billList) {
        this.billList = billList;
    }

    public List<DataBase> getAssetsInfo() {
        return assetsInfo;
    }
    public void setAssetsInfo(ArrayList<DataBase> assetsInfo) {
        this.assetsInfo = assetsInfo;
    }
    /**
     * 获取资产总数
     * @return
     */
    public Integer getTotal(){
        Integer total = 0;
        for(DataBase item :this.assetsInfo){
            total += item.getCount();
        }
        return total;
    }
    /**
     *  根据code 获取单据 数量
     * @param code
     * @return
     */
    public Integer getBillCntByCode(String code){
        for(DataBase data:billList){
            if(data.getCode().equals(code)){
                return data.getCount();
            }
        }
        return 0;
    }
    public void init(JSONObject json){
        if(json.containsKey("billList")) {

            JSONArray billListArr = json.getJSONArray("billList");
            for(int i=0;i<billListArr.size();i++){
                JSONObject baseData = billListArr.getJSONObject(i);
                DataBase baseData1 = new DataBase();
                baseData1.setCode(baseData.getString("code"));
                baseData1.setName(baseData.getString("name"));
                baseData1.setCount(baseData.getInteger("count"));
                billList.add(baseData1);
            }
        }


        if(json.containsKey("assetsInfo")) {
            JSONArray assetsInfoArr = json.getJSONArray("assetsInfo");
            for(int i=0;i<assetsInfoArr.size();i++){
                JSONObject baseData = assetsInfoArr.getJSONObject(i);
                DataBase baseData1 = new DataBase();
                baseData1.setCode(baseData.getString("code"));
                baseData1.setName(baseData.getString("name"));
                baseData1.setCount(baseData.getInteger("count"));
                assetsInfo.add(baseData1);
            }
        }
    }
}
