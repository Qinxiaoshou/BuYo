package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.UserOrdersAddPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.view.IUserOrdersAddView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.ConsumerOrderListActivity;
import com.aode.buyoapp.qinxiaoshou.adapter.ConsumerAddOrderDataRecyclerViewAdapter;
import com.aode.buyoapp.qinxiaoshou.fragment.ConsumerAddOrderDetailsFragment;

import java.util.logging.Handler;


/**
 * 用户添加订单详情activity
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ConsumerAddOrderDetailsActivity extends AppCompatActivity implements IUserOrdersAddView{

    private ConsumerAddOrderDetailsFragment productItemDetailsFragment;
    private TextView tv_g_add_product_title;
    private Button button;
    private Toolbar toolbar;
    private RadioGroup rg_h_open_permission;
    private TextView tv_rg_name;
    private Cloth cloth;
    private Orders orders;

    UserOrdersAddPresenter userOrdersAddPresenter = new UserOrdersAddPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_layout);

        //获取商品信息
        Intent intent = getIntent();
        cloth = (Cloth) intent.getSerializableExtra("cloth");


        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        productItemDetailsFragment = new ConsumerAddOrderDetailsFragment(cloth);
        toolbar = (Toolbar) findViewById(R.id.toolbar_g_product_appbar);
        tv_rg_name = (TextView) findViewById(R.id.tv_rg_name);
        tv_g_add_product_title = (TextView) findViewById(R.id.tv_g_add_product_title);
        button = (Button) findViewById(R.id.btn_right_text);
        rg_h_open_permission = (RadioGroup) findViewById(R.id.rg_h_open_permission);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_g_add_product_title.setText("添加订单");
        rg_h_open_permission.setVisibility(View.VISIBLE);
        tv_rg_name.setText("提交订单");
        button.setVisibility(View.GONE);
        transaction.add(R.id.fl_g_framelayout, productItemDetailsFragment).commit();

         //提交订单
        rg_h_open_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orders =  productItemDetailsFragment.ConsumerAddOrderDataRecyclerViewAdapter.getOrders();
                System.out.println("跳转提交订单信息：" + orders);
                userOrdersAddPresenter.ordersAdd();
            }
        });

    }

    @Override
    public Orders PutOrders() {
        System.out.println("%%%%--》"+orders);
        return orders;
    }

    @Override
    public void toMainActivity() {
        Intent intent1 = new Intent(getApplicationContext(), ConsumerOrderListActivity.class);
        startActivity(intent1);
        finish();
    }

    @Override
    public void showFailedError() {
       Toast.makeText(this,"提交订单失败",Toast.LENGTH_SHORT).show();
    }
}
