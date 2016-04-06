package com.aode.buyoapp.qinxiaoshou.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessAddProductFragment;


/**
 * 商家添加商品
 */
public class BusinessAddNewProductActivity extends FragmentActivity {

    private BusinessAddProductFragment businessAddProductFragment;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_layout);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        businessAddProductFragment = new BusinessAddProductFragment();
        toolbar = (Toolbar)findViewById(R.id.toolbar_g_product_appbar);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        transaction.add(R.id.fl_g_framelayout, businessAddProductFragment).commit();


    }


}
