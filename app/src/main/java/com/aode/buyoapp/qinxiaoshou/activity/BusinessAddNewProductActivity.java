package com.aode.buyoapp.qinxiaoshou.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessAddProductFragment;


/**
 * 友好商家界面
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessAddNewProductActivity extends FragmentActivity {

    private BusinessAddProductFragment businessAddProductFragment;
    private Toolbar toolbar;
    //完成按钮
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_layout);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        button = (Button) findViewById(R.id.btn_right_text);
        //步骤二：用add()方法加上Fragment的对象rightFragment
        businessAddProductFragment = new BusinessAddProductFragment(button);
        toolbar = (Toolbar)findViewById(R.id.toolbar_g_product_appbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        transaction.add(R.id.fl_g_framelayout, businessAddProductFragment).commit();


    }


}
