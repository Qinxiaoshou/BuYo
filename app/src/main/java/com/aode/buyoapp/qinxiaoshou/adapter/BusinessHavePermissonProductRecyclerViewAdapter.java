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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aode.buyoapp.LL.Listener.BBusinessFriendListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.view.IBusinessFriendView;
import com.aode.buyoapp.LL.view.IBusinessSearchView;
import com.aode.buyoapp.R;

import java.util.List;


/**
 * 商家管理商品对于其他商家的权限适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessHavePermissonProductRecyclerViewAdapter extends RecyclerView.Adapter<BusinessHavePermissonProductRecyclerViewAdapter.ViewHolder> {

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
        System.out.println("测试："+toBList);
            holder.tv_store_name.setText("店铺:"+toBList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return toBList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public LinearLayout ll_i_product_list;
        public TextView tv_store_name;
        public ImageView iv_pictue;
        public TextView tv_title;
        public TextView tv_price;
        public TextView tv_stock;

        public ViewHolder(View view) {
            super(view);
            //动态添加条目的布局
            ll_i_product_list = (LinearLayout) view.findViewById(R.id.ll_i_product_list);
            //商铺名称
            tv_store_name = (TextView) view.findViewById(R.id.tv_store_name);
            iv_pictue = (ImageView) view.findViewById(R.id.iv_pictue);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_stock = (TextView) view.findViewById(R.id.tv_stock);

            mView = view;
        }
    }
}
