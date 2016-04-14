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
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessSettingBusinessPerssionFragment;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessUpdateBusinessPerssionFragment;

import java.util.List;


/**
 * 商家选择商品对于某个商家的权限开放
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */

public class BusinessUpdateBusinessAndPermissionActivity extends FragmentActivity implements IBusinessProductView{

    BusinessQueryAllProductsPresenter businessQueryAllProductsPresenter = new BusinessQueryAllProductsPresenter(this);

    private BusinessUpdateBusinessPerssionFragment businessUpdateBusinessPerssionFragment;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private TextView tv_rg_name;
    private Button toolbarRigntButton;
    private RadioGroup radioGroup;
    private FragmentTransaction transaction;
    private RadioGroup rg_h_open_permission;
    private String bId;
    private  List<Business> cloths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_layout);
        //或得需要设置权限商家的id
        Intent intent  = getIntent();
        cloths = (List<Business>) intent.getSerializableExtra("businesses");
        System.out.println("所点击的该商家拥有本店权限查看本店的商品-->"+cloths);

        rg_h_open_permission = (RadioGroup) findViewById(R.id.rg_h_open_permission);

        toolbarTitle = (TextView) findViewById(R.id.tv_g_add_product_title);
        toolbarRigntButton = (Button) findViewById(R.id.btn_right_text);
        radioGroup = (RadioGroup) findViewById(R.id.rg_h_open_permission);
        tv_rg_name = (TextView) findViewById(R.id.tv_rg_name);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment

        toolbar = (Toolbar)findViewById(R.id.toolbar_g_product_appbar);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbarTitle.setText("选择修权限的商品");
        tv_rg_name.setText("修改权限");
        toolbarTitle.setTextSize(20);
        toolbarRigntButton.setText("全选");
        toolbarRigntButton.setVisibility(View.GONE);
        radioGroup.setVisibility(View.VISIBLE);
        businessQueryAllProductsPresenter.QueryAllProduct();
    }

    @Override
    public void toMainActivity(List<Cloth> clothlist) {
        businessUpdateBusinessPerssionFragment  = new BusinessUpdateBusinessPerssionFragment(clothlist,bId,rg_h_open_permission);
        transaction.add(R.id.fl_g_framelayout, businessUpdateBusinessPerssionFragment).commit();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(),"查询商品失败，请检查网络设置",Toast.LENGTH_SHORT).show();
    }
}
