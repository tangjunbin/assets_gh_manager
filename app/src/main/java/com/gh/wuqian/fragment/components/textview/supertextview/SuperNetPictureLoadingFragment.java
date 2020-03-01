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

package com.gh.wuqian.fragment.components.textview.supertextview;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.gh.wuqian.R;
import com.gh.wuqian.base.BaseFragment;

import butterknife.BindView;

/**
 *
 *
 * @author xuexiang
 * @since 2018/11/29 上午12:09
 */
@Page(name = "带网络图片的SuperTextView")
public class SuperNetPictureLoadingFragment extends BaseFragment {
    @BindView(R.id.super_tv1)
    SuperTextView superTextView;
    @BindView(R.id.super_tv2)
    SuperTextView superTextView2;
    @BindView(R.id.super_tv3)
    SuperTextView superTextView3;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_super_picture_loading;
    }

    @Override
    protected void initViews() {
        String url1 = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3860616424,1789830124&fm=80&w=179&h=119&img.PNG";
        String url2 = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=219781665,3032880226&fm=80&w=179&h=119&img.JPEG";
        String url3 = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3860616424,1789830124&fm=80&w=179&h=119&img.PNG";

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_head_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this)
                .load(url1)
                .apply(options)
                .into(superTextView.getLeftIconIV());
        Glide.with(this)
                .load(url2)
                .apply(options)
                .into(superTextView2.getRightIconIV());
        Glide.with(this)
                .load(url3)
                .apply(options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        superTextView3.setRightTvDrawableRight(resource);
                    }
                });
    }

    @Override
    protected void initListeners() {

    }
}
