package com.aode.buyoapp.qinxiaoshou.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessSettingBusinessPerssionFragment;


/**
 * 商家选择商品对于某个商家的权限开放
 */
public class BusinessChooseBusinessAndPermissionActivity extends FragmentActivity {

    private BusinessSettingBusinessPerssionFragment catalogue8SettingBusinessPerssionFragment;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private Button toolbarRigntButton;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_layout);
        toolbarTitle = (TextView) findViewById(R.id.tv_g_add_product_title);
        toolbarRigntButton = (Button) findViewById(R.id.btn_right_text);
        radioGroup = (RadioGroup) findViewById(R.id.rg_h_open_permission);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        catalogue8SettingBusinessPerssionFragment = new BusinessSettingBusinessPerssionFragment();
        toolbar = (Toolbar)findViewById(R.id.toolbar_g_product_appbar);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbarTitle.setText("选择开放权限的商品");
        toolbarTitle.setTextSize(20);
        toolbarRigntButton.setText("全选");
        radioGroup.setVisibility(View.VISIBLE);
        transaction.add(R.id.fl_g_framelayout, catalogue8SettingBusinessPerssionFragment).commit();


    }

}
