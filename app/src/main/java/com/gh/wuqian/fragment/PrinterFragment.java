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

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.gh.wuqian.R;
import com.gh.wuqian.activity.MainActivity;
import com.gh.wuqian.base.BaseFragment;
import com.gh.wuqian.utils.SettingSPUtils;
import com.gh.wuqian.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

@Page(name = "选择打印机")
public class PrinterFragment extends BaseFragment{
    private String TAG = "PrinterFragment";
    @BindView(R.id.sprinter_gp)
    SuperTextView stvPrinterGp;

    private BluetoothAdapter mBluetoothAdapter;

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_print;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

    }

    @OnClick({R.id.sprinter_gp})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.sprinter_gp:
                showBluetoothList();
                break;
            default:
                break;
        }
    }

    /**
     * 显示蓝牙列表
     */
    private void showBluetoothList(){

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            //弹出对话框提示用户是后打开
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);
            //  startActivityForResult(intent, REQUEST_ENABLE);
            //不做提示，强行打开
            // mBluetoothAdapter.enable();
        }

        new MaterialDialog.Builder(getContext())
                .title("蓝牙列表")
                .items(R.array.menu_values)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        XToastUtils.toast(position + ": " + text);
                    }
                })
                .show();
    }
}
