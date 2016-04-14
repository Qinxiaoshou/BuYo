package com.aode.buyoapp.qinxiaoshou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.LL.Listener.BBusinessFriendListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.view.IBusinessFriendView;
import com.aode.buyoapp.LL.view.IBusinessSearchView;
import com.aode.buyoapp.R;

import java.util.List;


/**
 * 商家管理商品对于其他商家的权限适配器
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessHavePermissonProductRecyclerViewAdapter extends RecyclerView.Adapter<BusinessHavePermissonProductRecyclerViewAdapter.ViewHolder>{

    private Context mContext;
    List<Business> toBList;

    public BusinessHavePermissonProductRecyclerViewAdapter(Context mContext, List<Business> toBList) {
        this.mContext = mContext;
        this.toBList = toBList;
    }
    //列表页面的布局实现
    @Override
    public BusinessHavePermissonProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_check_who_hava_permisson_product_item_layout, parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BusinessHavePermissonProductRecyclerViewAdapter.ViewHolder holder, int position) {
      /*  holder.iv_pictue.setImageResource(R.drawable.cheese_3);
        holder.tv_title.setText(toBList.get(position).getCloths().get(position).getTitle());
        holder.tv_price.setText("￥" + toBList.get(position).getCloths().get(position).getPrice());
        holder.tv_stock.setText("库存:" + toBList.get(position).getCloths().get(position).getStock());*/
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
