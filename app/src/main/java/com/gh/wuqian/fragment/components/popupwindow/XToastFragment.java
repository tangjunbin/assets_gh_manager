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

package com.gh.wuqian.fragment.components.popupwindow;

import android.view.Gravity;

import com.gh.wuqian.base.BaseSimpleListFragment;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.toast.XToast;
import com.gh.wuqian.R;

import java.util.List;

/**
 * @author XUE
 * @since 2019/3/26 10:06
 */
@Page(name = "XToast\n多重样式的Toast显示")
public class XToastFragment extends BaseSimpleListFragment {

    @Override
    protected void initArgs() {
        super.initArgs();
        XToast.Config.get()
                //位置设置为居中
                .setGravity(Gravity.CENTER);
    }

    /**
     * 初始化例子
     *
     * @param lists
     * @return
     */
    @Override
    protected List<String> initSimpleData(List<String> lists) {
        lists.add("ERROR_TOAST");
        lists.add("SUCCESS_TOAST");
        lists.add("INFO_TOAST");
        lists.add("WARNING_TOAST");
        lists.add("NORMAL_TOAST");
        return lists;
    }

    /**
     * 条目点击
     *
     * @param position
     */
    @Override
    protected void onItemClick(int position) {
        switch (position) {
            case 0:
                XToastUtils.error(R.string.error_message);
                break;
            case 1:
                XToastUtils.success(R.string.success_message);
                break;
            case 2:
                XToastUtils.info(R.string.info_message);
                break;
            case 3:
                XToastUtils.warning(R.string.warning_message);
                break;
            case 4:
                XToastUtils.toast(R.string.normal_message_without_icon);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        XToast.Config.get()
                //位置还原
                .resetGravity();
        super.onDestroyView();
    }
}
