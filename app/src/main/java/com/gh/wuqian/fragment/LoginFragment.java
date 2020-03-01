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

package com.gh.wuqian.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.gh.wuqian.ConfigBase;
import com.gh.wuqian.activity.MainActivity;
import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.TokenUtils;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.gh.wuqian.R;
import com.gh.wuqian.utils.api.OKHttpApiHttpService;
import com.xuexiang.xutil.app.ActivityUtils;

import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 登录页面
 *
 * @author xuexiang
 * @since 2019-11-17 22:15
 */
@Page(anim = CoreAnim.none)
public class LoginFragment extends BaseFragment {
    private String TAG = "LoginFragment";
    @BindView(R.id.et_phone_number)
    MaterialEditText etPhoneNumber;
    @BindView(R.id.et_verify_code)
    MaterialEditText etVerifyCode;
    @BindView(R.id.btn_get_verify_code)
    RoundButton btnGetVerifyCode;

    private CountDownButtonHelper mCountDownHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle()
                .setImmersive(true);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        titleBar.setTitle("");
        titleBar.setLeftImageDrawable(ResUtils.getVectorDrawable(getContext(), R.drawable.ic_login_close));
        return titleBar;
    }

    @Override
    protected void initViews() {
        mCountDownHelper = new CountDownButtonHelper(btnGetVerifyCode, 60);

    }

    @SingleClick
    @OnClick({R.id.btn_get_verify_code, R.id.btn_login, R.id.tv_other_login, R.id.tv_forget_password, R.id.tv_user_protocol, R.id.tv_privacy_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_verify_code:
                if (etPhoneNumber.validate()) {
                    getVerifyCode(etPhoneNumber.getEditValue());
                }
                break;
            case R.id.btn_login:
                if (etPhoneNumber.validate()) {
                    if (etVerifyCode.validate()) {
                        loginByVerifyCode(etPhoneNumber.getEditValue(), etVerifyCode.getEditValue());
                    }
                }
                break;
            case R.id.tv_other_login:
                XToastUtils.info("其他登录方式");
                break;
            case R.id.tv_forget_password:
                XToastUtils.info("忘记密码");
                break;
            case R.id.tv_user_protocol:
                XToastUtils.info("用户协议");
                break;
            case R.id.tv_privacy_protocol:
                XToastUtils.info("隐私政策");
                break;
            default:
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getVerifyCode(String phoneNumber) {
        OKHttpApiHttpService http = new OKHttpApiHttpService();
        Map<String, Object> params = new TreeMap<>();

        http.asyncGet(ConfigBase.host_url + ConfigBase.Api.GetVerifyCode_url,
                params,
                new OKHttpApiHttpService.Callback() {
                    @Override
                    public void onSuccess(String result) {

                        Log.i(TAG,"验证码获取成功===="+result);
                        mCountDownHelper.start();//倒计时按钮开始
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        XToastUtils.error("验证码获取失败，请检查网络");
                        Log.e(TAG,"验证码获取失败===="+throwable.getMessage());
                    }
                });
    }

    /**
     * 根据验证码登录
     *
     * @param phoneNumber 手机号
     * @param verifyCode  验证码
     */
    private void loginByVerifyCode(String phoneNumber, String verifyCode) {
        OKHttpApiHttpService http = new OKHttpApiHttpService();
        Map<String, Object> params = new TreeMap<>();
        params.put("phoneNumber",phoneNumber);
        params.put("verifyCode",verifyCode);
        Log.e(TAG, ConfigBase.host_url + ConfigBase.Api.Login_url);
        http.asyncPost(ConfigBase.host_url + ConfigBase.Api.Login_url,
                params,
                new OKHttpApiHttpService.Callback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG,"Login成功===="+result);
                        JSONObject resultObj = JSONObject.parseObject(result);
                        Integer code = (Integer) resultObj.get("Code");
                        String token = resultObj.getString("Token");
                        String message = resultObj.getString("Msg");
                        String userJson = resultObj.getString("Data");
                        if(code == 0 && !token.isEmpty()){
                            //String token = RandomUtils.getRandomNumbersAndLetters(16);
                            if (TokenUtils.handleLoginSuccess(token,userJson)) {
                                popToBack();
                                ActivityUtils.startActivity(MainActivity.class);
                            }
                        }else {
                            XToastUtils.error("code="+code+","+message);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        XToastUtils.error("Login失败，请检查网络");
                        Log.e(TAG,"Login失败===="+throwable.getMessage());
                    }
                });

    }


    @Override
    public void onDestroyView() {
        if (mCountDownHelper != null) {
            mCountDownHelper.recycle();
        }
        super.onDestroyView();
    }
}
