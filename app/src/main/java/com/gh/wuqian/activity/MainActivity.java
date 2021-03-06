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

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gh.wuqian.base.BaseActivity;
import com.gh.wuqian.utils.TokenUtils;
import com.gh.wuqian.utils.Utils;
import com.gh.wuqian.utils.XToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.guidview.GuideCaseQueue;
import com.xuexiang.xui.widget.guidview.GuideCaseView;
import com.gh.wuqian.R;
import com.gh.wuqian.adapter.menu.DrawerAdapter;
import com.gh.wuqian.adapter.menu.DrawerItem;
import com.gh.wuqian.adapter.menu.SimpleItem;
import com.gh.wuqian.fragment.AboutFragment;
import com.gh.wuqian.fragment.AssetsFragment;
import com.gh.wuqian.fragment.ComponentsFragment;
import com.gh.wuqian.fragment.ExpandsFragment;
import com.gh.wuqian.fragment.HomeFragment;
import com.gh.wuqian.fragment.InventoryFragment;
import com.gh.wuqian.fragment.QRCodeFragment;
import com.gh.wuqian.fragment.SettingFragment;
import com.gh.wuqian.fragment.UtilitysFragment;
import com.xuexiang.xutil.common.ClickUtils;
import com.xuexiang.xutil.system.DeviceUtils;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragStateListener;

import java.util.Arrays;

import butterknife.BindView;

/**
 * 项目壳工程
 *
 * @author wuqian
 * @since 2018/11/13 下午5:20
 */
public class MainActivity extends BaseActivity implements DrawerAdapter.OnItemSelectedListener, ClickUtils.OnClick2ExitListener {
    private static final int POS_HOME = 0;
    private static final int POS_ASSETS = 1;
    private static final int POS_INVENTORY = 2;
    private static final int POS_COMPONENTS = 3;
    private static final int POS_UTILITYS = 4;
    private static final int POS_EXPANDS = 5;
    private static final int POS_ABOUT = 6;
    private static final int POS_LOGOUT = 7;



    @BindView(R.id.fragment_container)
    FrameLayout test;


    private SlidingRootNav mSlidingRootNav;
    private LinearLayout mLLMenu;
    private String[] mMenuTitles;
    private Drawable[] mMenuIcons;
    private DrawerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //登记一下
        MobclickAgent.onProfileSignIn(DeviceUtils.getAndroidID());

        initSlidingMenu(savedInstanceState);

        initViews();
    }

    @Override
    protected boolean isSupportSlideBack() {
        return false;
    }

    private void initViews() {
        switchPage(HomeFragment.class);

        //静默检查版本更新
        Utils.checkUpdate(this, false);
    }

    public void openMenu() {
        if (mSlidingRootNav != null) {
            mSlidingRootNav.openMenu();
        }
    }

    public void closeMenu() {
        if (mSlidingRootNav != null) {
            mSlidingRootNav.closeMenu();
        }
    }

    public boolean isMenuOpen() {
        if (mSlidingRootNav != null) {
            return mSlidingRootNav.isMenuOpened();
        }
        return false;
    }

    private void initSlidingMenu(Bundle savedInstanceState) {
        mMenuTitles = ResUtils.getStringArray(R.array.menu_titles);
        mMenuIcons = ResUtils.getDrawableArray(this, R.array.menu_icons);

        mSlidingRootNav = new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        TokenUtils.handleUserInfo(mSlidingRootNav.getLayout(),this);


        mLLMenu = mSlidingRootNav.getLayout().findViewById(R.id.ll_menu);
        final AppCompatImageView ivQrcode = mSlidingRootNav.getLayout().findViewById(R.id.iv_qrcode);
        ivQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewPage(QRCodeFragment.class);
            }
        });

        final AppCompatImageView ivSetting = mSlidingRootNav.getLayout().findViewById(R.id.iv_setting);
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewPage(SettingFragment.class);
            }
        });

        mAdapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_ASSETS),
                createItemFor(POS_INVENTORY),
                createItemFor(POS_COMPONENTS),
                createItemFor(POS_UTILITYS),
                createItemFor(POS_EXPANDS),
                createItemFor(POS_ABOUT),
                createItemFor(POS_LOGOUT)));
        mAdapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(mAdapter);

        mAdapter.setSelected(POS_HOME);
        mSlidingRootNav.setMenuLocked(false);
        mSlidingRootNav.getLayout().addDragStateListener(new DragStateListener() {
            @Override
            public void onDragStart() {

            }

            @Override
            public void onDragEnd(boolean isMenuOpened) {
                if (isMenuOpened) {
                    if (!GuideCaseView.isShowOnce(MainActivity.this, getString(R.string.guide_key_sliding_root_navigation))) {
                        final GuideCaseView guideStep1 = new GuideCaseView.Builder(MainActivity.this)
                                .title("点击进入，可切换主题样式哦～～")
                                .titleSize(18, TypedValue.COMPLEX_UNIT_SP)
                                .focusOn(ivSetting)
                                .build();

                        final GuideCaseView guideStep2 = new GuideCaseView.Builder(MainActivity.this)
                                .title("点击进入，扫码关注哦～～")
                                .titleSize(18, TypedValue.COMPLEX_UNIT_SP)
                                .focusOn(ivQrcode)
                                .build();

                        new GuideCaseQueue()
                                .add(guideStep1)
                                .add(guideStep2)
                                .show();
                        GuideCaseView.setShowOnce(MainActivity.this, getString(R.string.guide_key_sliding_root_navigation));
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(int position) {
        switch (position) {
            case POS_HOME:
                switchPage(HomeFragment.class);
                mSlidingRootNav.closeMenu();
                break;
            case POS_ASSETS:
                switchPage(AssetsFragment.class);
                mSlidingRootNav.closeMenu();
                break;
            case POS_INVENTORY://盘点
                switchPage(InventoryFragment.class);
                mSlidingRootNav.closeMenu();
                break;
            case POS_COMPONENTS:
                switchPage(ComponentsFragment.class);
                mSlidingRootNav.closeMenu();
                break;
            case POS_UTILITYS:
                switchPage(UtilitysFragment.class);
                mSlidingRootNav.closeMenu();
                break;
            case POS_EXPANDS:
                switchPage(ExpandsFragment.class);
                mSlidingRootNav.closeMenu();
                break;
            case POS_ABOUT:
                openNewPage(AboutFragment.class);
                break;
            case POS_LOGOUT:
                DialogLoader.getInstance().showConfirmDialog(
                        this,
                        getString(R.string.lab_logout_confirm),
                        getString(R.string.lab_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                TokenUtils.handleLogoutSuccess();
                                finish();
                            }
                        },
                        getString(R.string.lab_no),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }
                );
                break;
            default:
                break;
        }
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(mMenuIcons[position], mMenuTitles[position])
                .withIconTint(ThemeUtils.resolveColor(this, R.attr.xui_config_color_content_text))
                .withTextTint(ThemeUtils.resolveColor(this, R.attr.xui_config_color_content_text))
                .withSelectedIconTint(ThemeUtils.resolveColor(this, R.attr.colorAccent))
                .withSelectedTextTint(ThemeUtils.resolveColor(this, R.attr.colorAccent));
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isMenuOpen()) {
                closeMenu();
            } else {
                ClickUtils.exitBy2Click(2000, this);
            }
        }
        return true;
    }

    /**
     * 再点击一次
     */
    @Override
    public void onRetry() {
        XToastUtils.toast("再按一次退出程序");
    }

    /**
     * 退出
     */
    @Override
    public void onExit() {
        moveTaskToBack(true);
    }
}
