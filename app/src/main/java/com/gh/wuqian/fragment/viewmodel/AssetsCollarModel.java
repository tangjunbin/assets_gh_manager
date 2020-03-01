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

package com.gh.wuqian.fragment.viewmodel;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gh.wuqian.ConfigBase;
import com.gh.wuqian.adapter.entity.AssetsCollarUseData;
import com.gh.wuqian.utils.api.OKHttpApiHttpService;

import java.util.Map;
import java.util.TreeMap;

public class AssetsCollarModel extends AssetsModel {
    private String TAG = "AssetsCollarModel";
    @Override
    public void getAssetsList(String query, int p, int limit, Map<String, Object> orderMap, Callback callback) {
        OKHttpApiHttpService http = new OKHttpApiHttpService();
        Map<String, Object> params = new TreeMap<>();
        params.put("keywords",query);
        params.put("p",p);
        params.put("limit",limit);
        params.putAll(orderMap);

        http.asyncGet(ConfigBase.host_url + ConfigBase.Api.Assets_collar_query,
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
                                assetsPageData.setListAssetsCollarBill(JSONObject.parseArray(JSONObject.toJSONString(result_list), AssetsCollarUseData.class));
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
}
