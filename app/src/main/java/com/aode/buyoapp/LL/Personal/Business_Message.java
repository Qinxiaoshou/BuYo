package com.aode.buyoapp.LL.Personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessMessagePresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.view.IBusinessMessageView;
import com.aode.buyoapp.R;

public class Business_Message extends AppCompatActivity implements IBusinessMessageView {
    private Button button;
    private TextView tv_business_name, tv_business_readlName, tv_business_phone, tv_business_address, tv_business_introduce;

    private BusinessMessagePresenter businessMessagePresenter = new BusinessMessagePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_personMessage);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        msg();
        update();
    }

    public void update() {
        button = (Button) findViewById(R.id.btn_update_BusinessMessage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(Home_business.business.getId())) {
                    Toast.makeText(getApplication(), "错误，请登录后进行修改", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplication(), Business_Message_update.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void msg() {
        tv_business_name = (TextView) findViewById(R.id.tv_business_name);
        tv_business_phone = (TextView) findViewById(R.id.tv_business_phone);
        tv_business_address = (TextView) findViewById(R.id.tv_business_address);
        tv_business_introduce = (TextView) findViewById(R.id.tv_business_introduce);
        tv_business_readlName = (TextView) findViewById(R.id.tv_business_readlName);
        businessMessagePresenter.Show();

    }

    @Override
    public String getId() {
        return Home_business.business.getId();
    }

    @Override
    public void toMainActivity(Business business) {
        //显示数据
        tv_business_name.setText(business.getLoginName());
        tv_business_readlName.setText(business.getName());
        tv_business_phone.setText(business.getPhoneNumber());
        tv_business_address.setText(business.getAddress());
        tv_business_introduce.setText(business.getDescription());
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplication(), "资料加载失败", Toast.LENGTH_SHORT).show();
    }
}
