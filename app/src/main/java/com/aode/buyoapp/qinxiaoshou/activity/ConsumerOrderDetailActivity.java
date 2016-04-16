package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.ConsumerOrderDetailsFragment;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        order=(Orders)intent.getSerializableExtra("order");


        setContentView(R.layout.business_add_product_layout);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        consumerOrderDetailsFragment = new ConsumerOrderDetailsFragment(order);
        toolbar = (Toolbar)findViewById(R.id.toolbar_g_product_appbar);
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
        transaction.add(R.id.fl_g_framelayout, consumerOrderDetailsFragment).commit();
    }
}
