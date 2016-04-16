package com.aode.buyoapp.qinxiaoshou.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessManageConsumerOrderDetailActivity;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 商家管理用户订单适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessOrderManageDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessOrderManageDataRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Orders> orderses;
    private String state;

    public BusinessOrderManageDataRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BusinessOrderManageDataRecyclerViewAdapter(Context mContext, List<Orders> orderses) {
        this.mContext = mContext;
        this.orderses = orderses;
    }

    //列表页面的布局实现
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_manage_customer_order_ltem_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final View view = holder.mView;
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(orderses.get(position).getDate());
        holder.tv_buyer.setText("购买者" + orderses.get(position).getBuyer());
        holder.tv_state.setText(orderses.get(position).getState());
        holder.tv_title.setText(orderses.get(position).getDescription());
        holder.tv_price.setText("初始价钱:" + String.valueOf(orderses.get(position).getPrice()));
        holder.tv_realprice.setText("实际价钱:" + String.valueOf(orderses.get(position).getPrice()));
        holder.tv_length.setText("布长:" + orderses.get(position).getLength());
        holder.tv_date.setText("日期:" + date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 5, 0); //上下移动
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //跳转到商家查看用户订单详情的界面
                        Intent intent = new Intent(mContext, BusinessManageConsumerOrderDetailActivity.class);
                        int pos = holder.getLayoutPosition();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("orders", orderses.get(pos));
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);

                    }
                });
                animator.start();
            }
        });
        //3个状态,未发货,已发货,已收货
        if ("未发货".equals(orderses.get(position).getState())) {
            holder.btn_check.setText("已发货");
            holder.btn_check.setEnabled(false); //不可点击
            state = "已发货";
        } else if ("已收货".equals(orderses.get(position).getState())) {
            holder.btn_check.setEnabled(false);
            //  holder.btn_right.setEnabled(false);
            holder.btn_check.setText("已收货");
        }
    }

    @Override
    public int getItemCount() {
        return orderses.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tv_buyer;
        TextView tv_state;
        TextView tv_title;
        TextView tv_price;
        TextView tv_realprice;
        TextView tv_length;
        TextView tv_date;
        Button btn_check;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_buyer = (TextView) view.findViewById(R.id.tv_buyer);
            tv_state = (TextView) view.findViewById(R.id.tv_state);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_realprice = (TextView) view.findViewById(R.id.tv_realprice);
            tv_length = (TextView) view.findViewById(R.id.tv_length);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            btn_check = (Button) view.findViewById(R.id.bt_2);
        }
    }
}
