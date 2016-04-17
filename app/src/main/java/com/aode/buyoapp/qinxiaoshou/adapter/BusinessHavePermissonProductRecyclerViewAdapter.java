package com.aode.buyoapp.qinxiaoshou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
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
    public ImageView iv_pictue;
    public TextView tv_title;
    public TextView tv_price;
    public TextView tv_stock;

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
        System.out.println("toList@@@@@-->" + toBList);
        if (toBList!=null) {
            //取出所有商品
            holder.rl_to_friend_product.setVisibility(View.VISIBLE);
            holder.ll_i_product_list.removeView(holder.ll_product_content);//移除默认view
            holder.tv_store_name.setText("店铺:" + toBList.get(position).getName());

            for (Cloth cloth : toBList.get(position).getCloths()) {
                //添加子view
                LinearLayout childLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.business_check_who_hava_permisson_product_item_content, null);
                //设置显示商品的商品条目详情
                iv_pictue = (ImageView) childLayout.findViewById(R.id.iv_pictue);
                tv_title = (TextView) childLayout.findViewById(R.id.tv_title);
                tv_price = (TextView) childLayout.findViewById(R.id.tv_price);
                tv_stock = (TextView) childLayout.findViewById(R.id.tv_stock);
                iv_pictue.setImageResource(R.drawable.cheese_3);  //默认图片
                tv_title.setText(cloth.getTitle());
                tv_price.setText("￥" + cloth.getPrice());
                tv_stock.setText("库存:" + cloth.getStock());
                //在商铺条目中添加子商品条目
                holder.ll_i_product_list.addView(childLayout);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (toBList==null) {
            return 1;
        } else {
            return toBList.size();
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public LinearLayout ll_i_product_list;
        public TextView tv_store_name;

        public LinearLayout ll_product_content;
        public RelativeLayout rl_to_friend_product;

        public ViewHolder(View view) {
            super(view);
            //整个条目的布局
            rl_to_friend_product = (RelativeLayout) view.findViewById(R.id.rl_to_friend_product);
            //商铺动态添加条目的布局
            ll_i_product_list = (LinearLayout) view.findViewById(R.id.ll_i_product_list);
            //商铺名称
            tv_store_name = (TextView) view.findViewById(R.id.tv_store_name);
            //商铺动态添加的商品默认条目内容
            ll_product_content = (LinearLayout) view.findViewById(R.id.ll_product_content);
            mView = view;
        }
    }
}
