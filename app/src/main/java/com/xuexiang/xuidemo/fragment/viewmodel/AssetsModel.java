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

package com.xuexiang.xuidemo.fragment.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xuexiang.xuidemo.ConfigBase;
import com.xuexiang.xuidemo.adapter.entity.AssetsData;
import com.xuexiang.xuidemo.fragment.viewmodel.model.AssetsPageData;
import com.xuexiang.xuidemo.fragment.viewmodel.model.HomeData;
import com.xuexiang.xuidemo.utils.TokenUtils;
import com.xuexiang.xuidemo.utils.XToastUtils;
import com.xuexiang.xuidemo.utils.api.OKHttpApiHttpService;

import java.util.Map;
import java.util.TreeMap;

public class AssetsModel extends ViewModel {
    private String TAG = "AssetsModel";
    protected AssetsPageData assetsPageData;
    public final MutableLiveData<AssetsPageData> mAssetsLiveData = new MutableLiveData<>();

    public AssetsModel() {
    }
    public void refresh(int limit,Map<String, Object> orderMap,Callback callback){
        assetsPageData = new AssetsPageData();
        assetsPageData.setStatus(AssetsPageData.Status.REFESH);

        getAssetsList("",1,limit,orderMap,callback);
    }
    public void loadMore(int p,int limit,Map<String, Object> orderMap,Callback callback){

        assetsPageData = new AssetsPageData();
        assetsPageData.setStatus(AssetsPageData.Status.LORDMORE);

        getAssetsList("",p,limit,orderMap,callback);
    }

    public void getAssetsList(String query,int p,int limit,Map<String, Object> orderMap,AssetsModel.Callback callback){

        OKHttpApiHttpService http = new OKHttpApiHttpService();
        Map<String, Object> params = new TreeMap<>();
        params.put("keywords",query);
        params.put("p",p);
        params.put("limit",limit);
        params.putAll(orderMap);

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
                        if(code == 0) {
                            callback.onCodeSuccess();
                            if(result_list.size()>0){
                                assetsPageData.setOnePageAssets(JSONObject.parseArray(JSONObject.toJSONString(result_list), AssetsData.class));
                                mAssetsLiveData.setValue(assetsPageData);
                                callback.onSuccess(JSONObject.toJSONString(result_list));
                            }else{
                                callback.onDataComplete();
                            }

                        }else{
                            callback.onCodeError();
                        }
                        callback.onSuccessComplete();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        callback.onNetworkError();
                    }
                });
    }
    public void getAssetsList(String query,int p,int limit,AssetsModel.Callback callback){
        Map<String, Object> orderMap = new TreeMap<>();
        getAssetsList(query,p,limit,orderMap,callback);
    }
    /**
     * 网络请求回调
     */
    public interface Callback {
        /**
         * 状态成功
         */
        void onCodeSuccess();
        /**
         * 结果回调,有数据成功返回
         *
         * @param result 结果
         */
        void onSuccess(String result);

        /**
         * 结果回调 返回错误
         *
         */
        void onCodeError();

        /**
         * 数据请求完成
         */
        void onDataComplete();

        /**
         * 请求成功 完成
         */
        void onSuccessComplete();

        /**
         * 网络错误
         */
        void onNetworkError();

    }
}
