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

package com.gh.wuqian.fragment.expands;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;

import com.gh.wuqian.base.BaseSimpleListFragment;
import com.gh.wuqian.utils.Utils;
import com.xuexiang.xfloatview.permission.FloatWindowPermission;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.gh.wuqian.R;
import com.gh.wuqian.fragment.expands.floatview.service.AppMonitorService;

import java.util.List;

/**
 * @author xuexiang
 * @since 2019/1/21 上午11:32
 */
@Page(name = "悬浮窗", extra = R.drawable.ic_expand_floatview)
public class FloatViewFragment extends BaseSimpleListFragment {

    @Override
    protected void initArgs() {
        super.initArgs();
        FloatWindowPermission.getInstance().applyFloatWindowPermission(getContext());
    }

    /**
     * 初始化例子
     *
     * @param lists
     * @return
     */
    @Override
    protected List<String> initSimpleData(List<String> lists) {
        lists.add("开启悬浮窗");
        lists.add("关闭悬浮窗");
        return lists;
    }

    /**
     * 条目点击
     *
     * @param position
     */
    @Override
    protected void onItemClick(int position) {
        Dialog dialog = FloatWindowPermission.getInstance().applyFloatWindowPermission(getContext());
        if  (dialog != null) {
            //需要申请权限
            return;
        }

        switch(position) {
            case 0:
                AppMonitorService.start(getContext(), null);
                gotoHome(getActivity());
                break;
            case 1:
                AppMonitorService.stop(getContext());
                break;
            default:
                break;
        }
    }

    /**
     * 防止华为机型未加入白名单时按返回键回到桌面再锁屏后几秒钟进程被杀
     */
    public static void gotoHome(Activity activity) {
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.addCategory(Intent.CATEGORY_HOME);
        activity.startActivity(launcherIntent);
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.TextAction("Github") {
            @Override
            public void performAction(View view) {
                Utils.goWeb(getContext(), "https://github.com/xuexiangjys/XFloatView");
            }
        });
        return titleBar;
    }

}
