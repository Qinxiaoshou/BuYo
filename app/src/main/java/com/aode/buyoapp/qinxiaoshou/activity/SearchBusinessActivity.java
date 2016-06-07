package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessDeleteProductDataFragment;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessStoreMessageFragment;

import java.util.List;

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
    private List<Cloth> cloths;
    private BusinessStoreMessageFragment businessStoreMessageFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_business);

        //获取商品信息
        Intent intent = getIntent();
        business = (Business) intent.getSerializableExtra("business");
        cloths = (List<Cloth>) intent.getSerializableExtra("clothlist");

        init();
        back();
    }

    public void init() {

        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        if (business != null) {
            businessStoreMessageFragment = new BusinessStoreMessageFragment(cloths,business);
            transaction.add(R.id.fl_business_store, businessStoreMessageFragment).commit();
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






