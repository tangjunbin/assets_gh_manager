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

import android.app.Activity;

import com.gh.wuqian.DemoDataProvider;
import com.xuexiang.xui.widget.activity.BaseGuideActivity;

import java.util.List;

/**
 *  启动引导页
 *
 * @author xuexiang
 * @since 2018/11/28 上午12:52
 */
public class UserGuideActivity extends BaseGuideActivity {
    @Override
    protected List<Object> getGuideResourceList() {
        return DemoDataProvider.getUsertGuides();
    }

    @Override
    protected Class<? extends Activity> getSkipClass() {
        return LoginActivity.class;
    }

}
