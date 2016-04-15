package com.aode.buyoapp.qinxiaoshou.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessUpdateProductMessagesActivity;
import com.aode.buyoapp.qinxiaoshou.activity.ConsumerProductDetailsActivity;


/**
 * 用户查看商品列表适配器
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ProductItemListRecyclerViewAdapter extends RecyclerView.Adapter<ProductItemListRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    public ProductItemListRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }


    //列表页面的布局实现
    @Override
    public ProductItemListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_product_list_item_content, parent, false);
       return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ProductItemListRecyclerViewAdapter.ViewHolder holder, int position) {
        final View view = holder.mView;

        view.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
            @Override
            public void onClick(View v) {
                        //跳转到商品详情页
                        mContext.startActivity(new Intent(mContext, ConsumerProductDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
