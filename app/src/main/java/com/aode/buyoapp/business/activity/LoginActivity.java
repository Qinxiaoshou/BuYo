package com.aode.buyoapp.business.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.R;

/**
 * 登录界面
 * @// FIXME: 2016/4/7
 * @author 陈映苗
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * 注册按钮的onClick()方法
     * @param v
     */
    public void register(View v){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    /**
     * 登录按钮的onClick()方法
     * @param v
     */
    public void login(View v){
        startActivity(new Intent(LoginActivity.this, Home_business.class));
    }
}
