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

package com.gh.wuqian.adapter;

import com.gh.wuqian.DemoDataProvider;
import com.gh.wuqian.utils.PlaceholderHelper;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.xuexiang.xui.widget.imageview.ImageLoader;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.gh.wuqian.R;
import com.gh.wuqian.adapter.base.BroccoliRecyclerAdapter;
import com.gh.wuqian.adapter.entity.NewInfo;

import me.samlss.broccoli.Broccoli;

/**
 * @author xuexiang
 * @since 2019/4/7 下午12:06
 */
public class NewsListAdapter extends BroccoliRecyclerAdapter<NewInfo> {
    /**
     * 是否是加载占位
     */
    private boolean mIsAnim;

    public NewsListAdapter(boolean isAnim) {
        super(DemoDataProvider.getEmptyNewInfo(), R.layout.adapter_news_list_item);
        mIsAnim = isAnim;
    }

    /**
     * 绑定控件
     *
     * @param holder
     * @param model
     * @param position
     */
    @Override
    protected void onBindData(SmartViewHolder holder, NewInfo model, int position) {
        holder.text(R.id.tv_user_name, model.getUserName());
        holder.text(R.id.tv_tag, model.getTag());
        holder.text(R.id.tv_title, model.getTitle());
        holder.text(R.id.tv_summary, model.getSummary());
        holder.text(R.id.tv_praise, model.getPraise() == 0 ? "点赞" : String.valueOf(model.getPraise()));
        holder.text(R.id.tv_comment, model.getComment() == 0 ? "评论" : String.valueOf(model.getComment()));
        holder.text(R.id.tv_read, "阅读量 " + model.getRead());

        RadiusImageView imageView = holder.findViewById(R.id.iv_image);
        ImageLoader.get().loadImage(imageView, model.getImageUrl());
    }

    /**
     * 绑定占位控件
     *
     * @param holder
     * @param broccoli
     */
    @Override
    protected void onBindBroccoli(SmartViewHolder holder, Broccoli broccoli) {
        if (mIsAnim) {
            broccoli.addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.iv_avatar)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_user_name)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_tag)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_title)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_summary)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.iv_image)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.iv_praise)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.iv_comment)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_praise)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_comment)))
                    .addPlaceholder(PlaceholderHelper.getParameter(holder.findView(R.id.tv_read)));
        } else {
            broccoli.addPlaceholders(
                    holder.findView(R.id.iv_avatar),
                    holder.findView(R.id.tv_user_name),
                    holder.findView(R.id.tv_tag),
                    holder.findView(R.id.tv_title),
                    holder.findView(R.id.tv_summary),
                    holder.findView(R.id.iv_image),
                    holder.findView(R.id.iv_praise),
                    holder.findView(R.id.tv_praise),
                    holder.findView(R.id.iv_comment),
                    holder.findView(R.id.tv_comment),
                    holder.findView(R.id.tv_read)
            );
        }
    }

}
