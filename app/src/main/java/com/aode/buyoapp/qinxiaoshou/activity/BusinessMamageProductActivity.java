package com.aode.buyoapp.qinxiaoshou.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessDeleteProductDataFragment;

import java.util.List;


/**
 *商家管理商品界面
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessMamageProductActivity extends FragmentActivity{

    private BusinessDeleteProductDataFragment businessDeleteProductDataFragment;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private List<Cloth> cloths;
    private View btn_right_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_delete_product_layout);
        toolbarTitle = (TextView) findViewById(R.id.tv_g_add_product_title);
        btn_right_text = findViewById(R.id.btn_right_text);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        businessDeleteProductDataFragment = new BusinessDeleteProductDataFragment();
        toolbar = (Toolbar)findViewById(R.id.toolbar_business_product_details);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_right_text.setVisibility(View.INVISIBLE);
        toolbarTitle.setText("管理商品");

        transaction.add(R.id.fl_g_framelayout, businessDeleteProductDataFragment).commit();


    }

}
