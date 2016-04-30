package com.aode.buyoapp.qinxiaoshou;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.ProductItemListFragment;


/**
 * 用户的商品列表activity
 * @// FIXME: 2016/4/7
 * @author 覃培周
 */
public class ConsumerProductListActivity extends ActionBarActivity {

    private ProductItemListFragment productItemListFragment;
    private TextView tv_g_add_product_title;
    private Button button;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.business_add_product_layout);
        //步骤一：添加一个FragmentTransaction的实例
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //步骤二：用add()方法加上Fragment的对象rightFragment
        productItemListFragment = new ProductItemListFragment();
        toolbar = (Toolbar)findViewById(R.id.toolbar_business_product_details);
        tv_g_add_product_title = (TextView) findViewById(R.id.tv_g_add_product_title);
        button = (Button) findViewById(R.id.btn_right_text);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_g_add_product_title.setText("商品列表");
        button.setVisibility(View.GONE);
        transaction.add(R.id.fl_g_framelayout, productItemListFragment).commit();


    }
}
