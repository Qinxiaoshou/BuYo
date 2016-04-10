package com.aode.buyoapp.qinxiaoshou.adapter;

import android.content.Context;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.model.SearcherBean;
import com.aode.buyoapp.qinxiaoshou.util.CommonAdapter;
import com.aode.buyoapp.qinxiaoshou.util.ViewHolder;

import java.util.List;

/**
 * 商家搜索商家适配器
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class MySearchAdapter extends CommonAdapter<SearcherBean> {
    public MySearchAdapter(Context context, List<SearcherBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        holder.setImageResource(R.id.item_search_iv_icon,mData.get(position).getIconId())
                .setText(R.id.item_search_tv_title,mData.get(position).getTitle())
                .setText(R.id.item_search_tv_content,mData.get(position).getContent())
                .setText(R.id.item_search_tv_comments,mData.get(position).getComments());
    }
}
