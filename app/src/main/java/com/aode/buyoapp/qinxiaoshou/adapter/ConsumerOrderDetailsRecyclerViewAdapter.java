package com.aode.buyoapp.qinxiaoshou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 用户订单详情适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ConsumerOrderDetailsRecyclerViewAdapter extends RecyclerView.Adapter<ConsumerOrderDetailsRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private Orders orders;

    public ConsumerOrderDetailsRecyclerViewAdapter(Context mContext, Orders orders) {
        this.mContext = mContext;
        this.orders = orders;
    }
    public ConsumerOrderDetailsRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //列表页面的布局实现
    @Override
    public ConsumerOrderDetailsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_manage_customer_order_details_pager_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ConsumerOrderDetailsRecyclerViewAdapter.ViewHolder holder, int position) {
        System.out.println(orders.getCloth());
        //时间转换
        Date data = orders.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(data);
        holder.tv_d_buynum.setText("订单号:" + orders.getId());
        holder.tv_buy_state.setText(orders.getState());
        holder.tv_d_username.setText("买家:"+orders.getBuyer());
        holder.tv_d_phone.setText("");  //数据库字段没有存进phone
        holder.tv_address.setText(orders.getAddress());
        holder.tv_store_name.setText("卖家:"+orders.getCloth().getTitle());
        holder.iv_d_product.setImageResource(R.drawable.cheese_3);
        holder.vt_d_product_title.setText(orders.getCloth().getTitle());
        holder.tv_price.setText("￥"+orders.getCloth().getPrice()+"/米");
        holder.tv_d_product_length.setText("购买长度:"+orders.getLength()+"米");
        holder.tv_all_price.setText("￥"+orders.getPrice());
        holder.tv_real_price.setText("￥"+orders.getPrice());
        holder.tv_d_buytime.setText(dateString);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView tv_d_buynum;
        public TextView tv_buy_state;
        public TextView tv_d_username;
        public TextView tv_d_phone;
        public TextView tv_address;
        public TextView tv_store_name;
        public ImageView iv_d_product;
        public TextView vt_d_product_title;
        public TextView tv_price;
        public TextView tv_d_product_length;
        public TextView tv_all_price;
        public TextView tv_real_price;
        public TextView tv_d_buytime;


        public ViewHolder(View view) {
            super(view);
            //订单号
            tv_d_buynum = (TextView) view.findViewById(R.id.tv_d_buynum);
            //交易状态
            tv_buy_state = (TextView) view.findViewById(R.id.tv_buy_state);
            //用户名
            tv_d_username = (TextView) view.findViewById(R.id.tv_d_username);
            //用户电话号码
            tv_d_phone = (TextView) view.findViewById(R.id.tv_d_phone);
            //收货地址
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            //店铺名称
            tv_store_name = (TextView) view.findViewById(R.id.tv_store_name);
            //商品图片
            iv_d_product = (ImageView) view.findViewById(R.id.iv_d_product);
            //商品标题
            vt_d_product_title = (TextView) view.findViewById(R.id.vt_d_product_title);
            //商品单价
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            //商品长度
            tv_d_product_length = (TextView) view.findViewById(R.id.tv_d_product_length);
            //商品总额
            tv_all_price = (TextView) view.findViewById(R.id.tv_all_price);
            //实际付款
            tv_real_price = (TextView) view.findViewById(R.id.tv_real_price);
            //购买时间
            tv_d_buytime = (TextView) view.findViewById(R.id.tv_d_buytime);
            mView = view;
        }
    }
}
