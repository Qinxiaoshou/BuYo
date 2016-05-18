package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.BusinessQueryAllProductsPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessSettingBusinessPerssionFragment;

import java.util.List;


/**
 * 商家选择商品对于某个商家的权限开放
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */

public class BusinessChooseBusinessAndPermissionActivity extends FragmentActivity implements IBusinessProductView {

    BusinessQueryAllProductsPresenter businessQueryAllProductsPresenter = new BusinessQueryAllProductsPresenter(this);

    private BusinessSettingBusinessPerssionFragment catalogue8SettingBusinessPerssionFragment;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private Button toolbarRigntButton;
    private RadioGroup radioGroup;
    private FragmentTransaction transaction;
    private RadioGroup rg_h_open_permission;
    private String bId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_search_other_business_layout);
        //或得需要设置权限商家的id
        Intent intent  = getIntent();
        bId = intent.getStringExtra("bId");

        rg_h_open_permission = (RadioGroup) findViewById(R.id.rg_h_open_permission);

        toolbarTitle = (TextView) findViewById(R.id.tv_g_add_product_title);
        toolbarRigntButton = (Button) findViewById(R.id.btn_right_text);
        radioGroup = (RadioGroup) findViewById(R.id.rg_h_open_permission);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment

        toolbar = (Toolbar)findViewById(R.id.toolbar_business_product_details);
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
        toolbarRigntButton.setVisibility(View.GONE);
        radioGroup.setVisibility(View.VISIBLE);
        businessQueryAllProductsPresenter.QueryAllProduct();
    }

    @Override
    public void toMainActivity(List<Cloth> clothlist) {
        catalogue8SettingBusinessPerssionFragment = new BusinessSettingBusinessPerssionFragment(clothlist,bId,rg_h_open_permission);
        transaction.add(R.id.fl_g_framelayout, catalogue8SettingBusinessPerssionFragment).commit();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(),"查询商品失败，请检查网络设置",Toast.LENGTH_SHORT).show();
    }
}
