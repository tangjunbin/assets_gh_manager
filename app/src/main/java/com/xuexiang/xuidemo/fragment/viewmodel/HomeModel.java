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

package com.xuexiang.xuidemo.fragment.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alibaba.fastjson.JSONObject;
import com.xuexiang.xuidemo.ConfigBase;
import com.xuexiang.xuidemo.fragment.viewmodel.model.HomeData;
import com.xuexiang.xuidemo.utils.TokenUtils;
import com.xuexiang.xuidemo.utils.XToastUtils;
import com.xuexiang.xuidemo.utils.api.OKHttpApiHttpService;

import java.util.Map;
import java.util.TreeMap;

public class HomeModel extends ViewModel {
    private String TAG = "HomeModel";
    public final MutableLiveData<HomeData> mHomeLiveData = new MutableLiveData<>();

    public HomeModel() {
        finishRefresh();
    }

    public void finishRefresh(){
        finishRefresh(new OKHttpApiHttpService.Callback() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG,"Home初始化数据");
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
    /**
     * 获取数据
     */
    public void finishRefresh(OKHttpApiHttpService.Callback callBack){
        OKHttpApiHttpService http = new OKHttpApiHttpService();
        Map<String, Object> params = new TreeMap<>();
        String token = TokenUtils.getToken();
        params.put("token",token);
        http.asyncGet(ConfigBase.host_url+ConfigBase.Api.GetHomePage_url,params, new OKHttpApiHttpService.Callback() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG,"成功===="+result);
                JSONObject resultObj = JSONObject.parseObject(result);
                Integer code = (Integer) resultObj.get("Code");
                String message = resultObj.getString("Msg");
                JSONObject data_a  = resultObj.getJSONObject("Data");
                if(code == 0) {
                    HomeData homeData = new HomeData();
                    homeData.init(data_a);
                    mHomeLiveData.setValue(homeData);
                }else{
                    XToastUtils.error("code="+code+","+message);
                }
                callBack.onSuccess("数据处理成功");
            }

            @Override
            public void onError(Throwable throwable) {
                XToastUtils.error("网络请求失败,"+throwable.getMessage());
            }
        });
    }
}
