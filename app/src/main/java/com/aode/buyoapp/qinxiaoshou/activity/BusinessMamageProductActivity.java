package com.aode.buyoapp.qinxiaoshou.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessDeleteProductDataFragment;


/**
 *商家管理商品界面
 */
public class BusinessMamageProductActivity extends FragmentActivity {

    private BusinessDeleteProductDataFragment businessDeleteProductDataFragment;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_layout);
        toolbarTitle = (TextView) findViewById(R.id.tv_g_add_product_title);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        businessDeleteProductDataFragment = new BusinessDeleteProductDataFragment();
        toolbar = (Toolbar)findViewById(R.id.toolbar_g_product_appbar);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbarTitle.setText("管理商品");

        transaction.add(R.id.fl_g_framelayout, businessDeleteProductDataFragment).commit();


    }
}
