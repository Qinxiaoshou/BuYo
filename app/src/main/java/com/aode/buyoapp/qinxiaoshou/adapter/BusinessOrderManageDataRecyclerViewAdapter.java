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
import com.aode.buyoapp.qinxiaoshou.activity.BusinessManageConsumerOrderDetailActivity;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessUpdateProductMessagesActivity;


/**
 * 列表条目信息的适配器
 */
public class BusinessOrderManageDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessOrderManageDataRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    public BusinessOrderManageDataRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }


    //列表页面的布局实现
    @Override
    public BusinessOrderManageDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_manage_customer_order_ltem_layout, parent, false);
       return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final BusinessOrderManageDataRecyclerViewAdapter.ViewHolder holder, int position) {

        final View view = holder.mView;
        view.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 5, 0); //上下移动
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                       //跳转到商家查看用户订单详情的界面
                      mContext.startActivity(new Intent(mContext, BusinessManageConsumerOrderDetailActivity.class));
                    }
                });
                animator.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
