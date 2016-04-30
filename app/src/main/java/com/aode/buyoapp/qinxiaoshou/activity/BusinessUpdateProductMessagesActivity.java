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

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessUpdateProductDataFragment;


/**
 * 修改商品信息
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessUpdateProductMessagesActivity extends FragmentActivity {

    private BusinessUpdateProductDataFragment businessUpdateProductDataFragment;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private RadioGroup rg_h;
    private Cloth cloth;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_layout);
        //获取前面页面传递过来的数据
        Intent intent = this.getIntent();
        cloth=(Cloth)intent.getSerializableExtra("cloth");

        button = (Button) findViewById(R.id.btn_right_text);



        toolbarTitle = (TextView) findViewById(R.id.tv_g_add_product_title);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        businessUpdateProductDataFragment = new BusinessUpdateProductDataFragment(cloth,button);
        toolbar = (Toolbar)findViewById(R.id.toolbar_business_product_details);
        rg_h = (RadioGroup) findViewById(R.id.rg_h_open_permission);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rg_h.setVisibility(View.INVISIBLE);
        toolbarTitle.setText("修改商品信息");

        transaction.add(R.id.fl_g_framelayout, businessUpdateProductDataFragment).commit();


    }
}
