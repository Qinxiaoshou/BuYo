package com.aode.buyoapp.qinxiaoshou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.ConsumerOrderListActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;

/**
 * 用户商品详情适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ConsumerAddOrderDataRecyclerViewAdapter extends RecyclerView.Adapter<ConsumerAddOrderDataRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private Orders orders; //用户订单对象
    private Cloth cloth;
    public final int UPDATE = 0;
    public TextView tv_d_allPrice_text;
    public TextView tv_d_realpay_text;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE:
                    Bundle bundle = msg.getData();
                    int length = bundle.getInt("length");
                    tv_d_allPrice_text.setText("￥" + cloth.getPrice() * Long.valueOf(length));
                    tv_d_realpay_text.setVisibility(View.VISIBLE);
                    tv_d_realpay_text.setText("￥" + cloth.getPrice() * Long.valueOf(length));
                    orders.setPrice(length * cloth.getPrice());
                    orders.setLength(length);
                    break;
                default:
                    break;
            }

        }
    };

    public ConsumerAddOrderDataRecyclerViewAdapter() {

    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }


    public ConsumerAddOrderDataRecyclerViewAdapter(Context mContext, Cloth cloth) {
        this.mContext = mContext;
        this.cloth = cloth;
    }


    //订单详情页面的布局实现
    @Override
    public ConsumerAddOrderDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_add_order_details_pager_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ConsumerAddOrderDataRecyclerViewAdapter.ViewHolder holder, int position) {
        orders = new Orders();
        holder.tv_d_username.setText("买家:" + Home_person.loginName);
        holder.tv_d_phone.setText(Home_person.phone);
        holder.iv_d_product.setImageResource(R.drawable.cheese_3); //默认图片
        holder.tv_d_allPrice_text.setText("￥" + cloth.getPrice()); //默认价格
        holder.tv_sotre_name.setText("卖家:" + cloth.getBusiness().getName());
        holder.vt_d_product_title.setText(cloth.getTitle());
        holder.tv_price.setText("￥" + cloth.getPrice());
        this.tv_d_allPrice_text = holder.tv_d_allPrice_text;
        this.tv_d_realpay_text = holder.tv_d_realpay_text;
        holder.et_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {//在输入数据时监听

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,//输入数据之前的监听
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {//输入数据之后监听
                if (s.toString() == null) {
                    holder.et_length.setHint("请输入米数");
                } else {
                    try {
                        int length = Integer.valueOf(holder.et_length.getText().toString());
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putInt("length", length);
                        msg.what = UPDATE;
                        msg.setData(data);
                        handler.sendMessage(msg);
                    }catch (Exception e){
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putInt("length", 1);
                        msg.what = UPDATE;
                        msg.setData(data);
                        handler.sendMessage(msg);
                       // e.printStackTrace();
                    }

                }
            }
        });


        holder.et_address.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {//输入数据之后监听
                String address = holder.et_address.getText().toString().trim();
                orders.setAddress(address);
            }
        });

        holder.et_order_description.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {//输入数据之后监听
                String et_order_description = holder.et_order_description.getText().toString().trim();
                orders.setDescription(et_order_description);
            }
        });

        orders.setbId(cloth.getbId() + "");
        orders.setuId(Home_person.id);
        orders.setBuyer(Home_person.loginName);
        orders.setcId(cloth.getId());
        orders.setPrice(Double.valueOf(cloth.getPrice()));
        orders.setState("未发货");
        setOrders(orders); //设置回掉order对象

    }

    @Override

    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView tv_d_username;
        public TextView tv_d_phone;
        public EditText et_address;
        public EditText et_length;
        public EditText et_order_description;
        public TextView tv_sotre_name;
        public ImageView iv_d_product;
        public TextView vt_d_product_title;
        public TextView tv_price;
        public TextView tv_d_allPrice_text;
        public TextView tv_d_realpay_text;
        public TextView tv_date;

        public ViewHolder(View view) {
            super(view);
            //用户名称
            tv_d_username = (TextView) view.findViewById(R.id.tv_d_username);
            //用户手机号码
            tv_d_phone = (TextView) view.findViewById(R.id.tv_d_phone);
            //用户地址
            et_address = (EditText) view.findViewById(R.id.et_address);
            //购买的布匹长度
            et_length = (EditText) view.findViewById(R.id.et_length);
            //订单描述
            et_order_description = (EditText) view.findViewById(R.id.et_order_description);
            //商店名称
            tv_sotre_name = (TextView) view.findViewById(R.id.tv_sotre_name);
            //商品图片
            iv_d_product = (ImageView) view.findViewById(R.id.iv_d_product);
            //商品标题
            vt_d_product_title = (TextView) view.findViewById(R.id.vt_d_product_title);
            //商品价格
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            //总额
            tv_d_allPrice_text = (TextView) view.findViewById(R.id.tv_d_allPrice_text);
            //实际付款
            tv_d_realpay_text = (TextView) view.findViewById(R.id.tv_d_price);
            //下单时间
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            mView = view;
        }
    }
}
