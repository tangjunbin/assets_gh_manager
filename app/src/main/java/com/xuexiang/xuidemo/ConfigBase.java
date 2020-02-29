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

package com.xuexiang.xuidemo;

public class ConfigBase {
    static public String host_url="http://192.168.3.229:58080/TEST/assets";

    static public class Api{
        //登入接口
        static public String Login_url = "/login.php";

        //获取验证码接口
        static public String GetVerifyCode_url = "/getverifycode.json";

        //首页数据接口
        static public String GetHomePage_url = "/gethomepage.php";

        static public String Assets_query = "/getassets_query.php";

        static public String Assets_collar_query = "/getassets_collar_query.php";

        static public String  Assets_retreat_query = "/getassets_retreat_query.php";
    }
}
