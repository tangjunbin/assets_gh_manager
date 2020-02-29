package com.xuexiang.xuidemo.fragment.components.textview.supertextview;

import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.layout.ExpandableLayout;
import com.xuexiang.xui.widget.textview.badge.Badge;
import com.xuexiang.xui.widget.textview.badge.BadgeView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.xuexiang.xuidemo.R;
import com.xuexiang.xuidemo.base.BaseFragment;
import com.xuexiang.xuidemo.utils.XToastUtils;

import butterknife.BindView;

@Page(name = "SuperTextView点击事件")
public class SuperClickFragment extends BaseFragment {
    @BindView(R.id.super_tv)
    SuperTextView superTextView;
    @BindView(R.id.super_cb_tv)
    SuperTextView superTextView_cb;
    @BindView(R.id.super_switch_tv)
    SuperTextView superTextView_switch;
    @BindView(R.id.super_message_tv)
    SuperTextView stvMessage;
    @BindView(R.id.stv_expandable)
    SuperTextView stvExpandable;
    @BindView(R.id.expandable_layout)
    ExpandableLayout mExpandableLayout;
    @BindView(R.id.stv_name)
    SuperTextView stvName;
    @BindView(R.id.stv_phone)
    SuperTextView stvPhone;

    private Badge mBadge;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_super_click;
    }

    @Override
    protected void initViews() {
        //设置空字符串用于占位
        stvMessage.setRightString("      ");
        mBadge = new BadgeView(getContext()).bindTarget(stvMessage.getRightTextView())
                .setBadgeGravity(Gravity.END | Gravity.CENTER)
                .setBadgePadding(3, true)
                .setBadgeTextSize(9, true)
                .setBadgeNumber(3);

    }

    @Override
    protected void initListeners() {
        /**
         * 根据实际需求对需要的View设置点击事件
         */
        superTextView.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                XToastUtils.toast("整个item的点击事件");
            }
        }).setLeftTopTvClickListener(new SuperTextView.OnLeftTopTvClickListener() {
            @Override
            public void onClick(TextView textView) {
                XToastUtils.toast(superTextView.getLeftTopString());
            }
        }).setLeftTvClickListener(new SuperTextView.OnLeftTvClickListener() {
            @Override
            public void onClick(TextView textView) {
                XToastUtils.toast(superTextView.getLeftString());
            }
        }).setLeftBottomTvClickListener(new SuperTextView.OnLeftBottomTvClickListener() {
            @Override
            public void onClick(TextView textView) {
                XToastUtils.toast(superTextView.getLeftBottomString());
            }
        }).setCenterTopTvClickListener(new SuperTextView.OnCenterTopTvClickListener() {
            @Override
            public void onClick(TextView textView) {
                XToastUtils.toast(superTextView.getCenterTopString());
            }
        }).setCenterTvClickListener(new SuperTextView.OnCenterTvClickListener() {
            @Override
            public void onClick(TextView textView) {
                XToastUtils.toast(superTextView.getCenterString());
            }
        }).setCenterBottomTvClickListener(new SuperTextView.OnCenterBottomTvClickListener() {
            @Override
            public void onClick(TextView textView) {
                XToastUtils.toast(superTextView.getCenterBottomString());
            }
        }).setRightTopTvClickListener(new SuperTextView.OnRightTopTvClickListener() {
            @Override
            public void onClick(TextView textView) {
                XToastUtils.toast(superTextView.getRightTopString());
            }
        }).setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
            @Override
            public void onClick(TextView textView) {
                XToastUtils.toast(superTextView.getRightString());
            }
        }).setRightBottomTvClickListener(new SuperTextView.OnRightBottomTvClickListener() {
            @Override
            public void onClick(TextView textView) {
                XToastUtils.toast(superTextView.getRightBottomString());
            }
        }).setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClick(ImageView imageView) {
            }
        }).setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClick(ImageView imageView) {
            }
        });


        superTextView_cb.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                superTextView.setCheckBoxChecked(!superTextView.getCheckBoxIsChecked());
            }
        }).setCheckBoxCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                XToastUtils.toast("isChecked : " + isChecked);
            }
        });

        superTextView_switch.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false);
            }
        }).setSwitchCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                XToastUtils.toast("isChecked : " + isChecked);
            }
        });

        stvMessage.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                mBadge.hide(true);
            }
        });

        stvName.setCenterEditTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                XToastUtils.toast("聚焦变化：" + hasFocus);
            }
        });

        stvPhone.setCenterEditTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XToastUtils.toast("点击监听");
            }
        });


        mExpandableLayout.setOnExpansionChangedListener(new ExpandableLayout.OnExpansionChangedListener() {
            @Override
            public void onExpansionChanged(float expansion, int state) {
                if (stvExpandable != null && stvExpandable.getRightIconIV() != null) {
                    stvExpandable.getRightIconIV().setRotation(expansion * 90);
                }
            }
        });
        stvExpandable.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                if (mExpandableLayout != null) {
                    mExpandableLayout.toggle();
                }
            }
        });


    }

}
