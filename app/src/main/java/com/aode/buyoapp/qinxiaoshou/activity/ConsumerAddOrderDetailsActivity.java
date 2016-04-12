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

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.ConsumerAddOrderDetailsFragment;
import com.aode.buyoapp.qinxiaoshou.fragment.ProductItemDetailsFragment;

import org.w3c.dom.Text;


/**
 * 用户添加详情activity
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ConsumerAddOrderDetailsActivity extends AppCompatActivity {

    private ConsumerAddOrderDetailsFragment productItemDetailsFragment;
    private TextView tv_g_add_product_title;
    private Button button;
    private Toolbar toolbar;
    private RadioGroup rg_h_open_permission;
    private TextView tv_rg_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取商品信息
        Intent intent = getIntent();
        Cloth cloth = (Cloth) intent.getSerializableExtra("cloth");

        setContentView(R.layout.business_add_product_layout);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        productItemDetailsFragment = new ConsumerAddOrderDetailsFragment();
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
                Toast.makeText(getApplicationContext(),"提交订单",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
