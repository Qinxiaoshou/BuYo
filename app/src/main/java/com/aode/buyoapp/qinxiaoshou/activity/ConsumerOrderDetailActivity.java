package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.QueryProductByIdPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.view.QueryProductBuyIdView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.ConsumerOrderDetailsRecyclerViewAdapter;
import com.aode.buyoapp.qinxiaoshou.fragment.ConsumerOrderDetailsFragment;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 用户订单详情
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ConsumerOrderDetailActivity extends FragmentActivity {

    private ConsumerOrderDetailsFragment consumerOrderDetailsFragment;

    private TextView tv_g_add_product_title;
    private Button button;
    private Toolbar toolbar;
    private Orders order;
    private RecyclerView myRecyclerView;
    private Cloth cloth;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        order=(Orders)intent.getSerializableExtra("order");

        setContentView(R.layout.consumer_order_detail_layout);
        //订单号
        tv_d_buynum = (TextView) findViewById(R.id.tv_d_buynum);
        //交易状态
        tv_buy_state = (TextView)findViewById(R.id.tv_buy_state);
        //用户名
        tv_d_username = (TextView) findViewById(R.id.tv_d_username);
        //用户电话号码
        tv_d_phone = (TextView)findViewById(R.id.tv_d_phone);
        //收货地址
        tv_address = (TextView) findViewById(R.id.tv_address);
        //店铺名称
        tv_store_name = (TextView) findViewById(R.id.tv_store_name);
        //商品图片
        iv_d_product = (ImageView) findViewById(R.id.iv_d_product);
        //商品标题
        vt_d_product_title = (TextView) findViewById(R.id.vt_d_product_title);
        //商品单价
        tv_price = (TextView)findViewById(R.id.tv_price);
        //商品长度
        tv_d_product_length = (TextView)findViewById(R.id.tv_d_product_length);
        //商品总额
        tv_all_price = (TextView) findViewById(R.id.tv_all_price);
        //实际付款
        tv_real_price = (TextView)findViewById(R.id.tv_real_price);
        //购买时间
        tv_d_buytime = (TextView)findViewById(R.id.tv_d_buytime);

        toolbar = (Toolbar)findViewById(R.id.toolbar_business_product_details);
        tv_g_add_product_title = (TextView) findViewById(R.id.tv_g_add_product_title);
        button = (Button) findViewById(R.id.btn_right_text);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        tv_g_add_product_title.setText("订单详情");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        button.setVisibility(View.GONE);

        Date data = order.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(data);
        tv_d_buynum.setText("订单号:" + order.getId());
        tv_buy_state.setText(order.getState());
        tv_d_username.setText("买家:"+order.getBuyer());
        tv_d_phone.setText("");  //数据库字段没有存进phone
        tv_address.setText(order.getAddress());
        tv_store_name.setText("卖家:"+order.getBusiness().getName());
        iv_d_product.setImageResource(R.drawable.cheese_3);
        vt_d_product_title.setText(order.getCloth().getTitle());
        tv_price.setText("￥"+order.getCloth().getPrice()+"/米");
        tv_d_product_length.setText("购买长度:"+order.getLength()+"米");
        tv_all_price.setText("￥"+order.getPrice());
        tv_real_price.setText("￥"+order.getPrice());
        tv_d_buytime.setText(dateString);
    }


}
