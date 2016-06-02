package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.R;

/**
 * 布约app搜索界面
 *
 * @author 覃培周
 * @// FIXME: 2016/5/7
 */
public class SearchBusinessActivity extends AppCompatActivity {

    public Business business;
    private ImageView iv_back;
    private TextView tv_search_business_name, tv_search_business_description, tv_search_business_phone, tv_search_business_license, tv_search_business_address, tv_search_business_sales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_business);

        //获取商品信息
        Intent intent = getIntent();
        business = (Business) intent.getSerializableExtra("business");

        init();
        set();
        back();
    }

    public void init() {
        tv_search_business_name = (TextView) findViewById(R.id.tv_search_business_name);
        tv_search_business_description = (TextView) findViewById(R.id.tv_search_business_description);
        tv_search_business_phone = (TextView) findViewById(R.id.tv_search_business_phone);
        tv_search_business_license = (TextView) findViewById(R.id.tv_search_business_license);
        tv_search_business_address = (TextView) findViewById(R.id.tv_search_business_address);
        tv_search_business_sales = (TextView) findViewById(R.id.tv_search_business_sales);
    }

    public void set() {
        if (business != null) {
            tv_search_business_name.setText("店铺名:"+business.getName());
            tv_search_business_description.setText("店铺描述:" + business.getDescription());
            tv_search_business_phone.setText("电话:" + business.getPhoneNumber());
            tv_search_business_license.setText("营业执照:" + business.getLicense());
            tv_search_business_address.setText("地址:" + business.getAddress());
            tv_search_business_sales.setText("销量:" + business.getSales());
        } else {
            Toast.makeText(this, "获取商家信息失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void back() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        //返回键事件
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}






