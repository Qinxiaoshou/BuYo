package com.aode.buyoapp.business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.Login_business;
import com.aode.buyoapp.R;

/**
 * 启动布约app界面
 * @// FIXME: 2016/3/31
 * @author 陈映苗
 */
public class StartActivity extends Activity {

    /**
     * 启动界面中的商家端按钮
     */
    private Button btn_start_business;
    /**
     * 启动界面的用户端按钮
     */
    private Button btn_start_customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        /**
         * 商家端按钮和用户端按钮的透明度动画
         */
        btn_start_business = (Button) findViewById(R.id.btn_start_business);
        btn_start_customer = (Button) findViewById(R.id.btn_start_customer);
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(3000);
        btn_start_business.startAnimation(animation);
        btn_start_customer.startAnimation(animation);

    }

    /**
     * 点击商家按钮进入商家端登录界面
     *
     */
    public void startBusiness(View v){
        startActivity(new Intent(StartActivity.this,Login_business.class));
    }

    /**
     * 点击用户按钮进入用户端首页界面
     *
     */
    public void startCustomer(View v){
        startActivity(new Intent(StartActivity.this,Home_person.class));
    }
}
