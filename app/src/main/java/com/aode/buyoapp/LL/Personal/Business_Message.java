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

/**
 * Created by LiLei on 2016/5/20.Go.
 */

public class Business_Message extends AppCompatActivity implements IBusinessMessageView {
    private Button button;
    private TextView tv_business_loginName, tv_business_name, tv_business_phone, tv_business_address, tv_business_contacts, tv_business_identities, tv_business_payment, tv_business_introduce;
    private Business business;
    private BusinessMessagePresenter businessMessagePresenter = new BusinessMessagePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_personMessage);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        msg();
        upDate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        businessMessagePresenter.Show();
    }

    public void upDate() {
        button = (Button) findViewById(R.id.btn_update_BusinessMessage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(Home_business.business.getId())) {
                    Toast.makeText(getApplication(), "错误，请登录后进行修改", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplication(), Business_Message_update.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("before_business", business);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    public void msg() {
        tv_business_loginName = (TextView) findViewById(R.id.tv_business_loginName);
        tv_business_name = (TextView) findViewById(R.id.tv_business_Name);
        tv_business_contacts = (TextView) findViewById(R.id.tv_business_contacts);
        tv_business_phone = (TextView) findViewById(R.id.tv_business_phone);
        tv_business_address = (TextView) findViewById(R.id.tv_business_address);
        tv_business_identities = (TextView) findViewById(R.id.tv_business_identities);
        tv_business_payment = (TextView) findViewById(R.id.tv_business_payment);
        tv_business_introduce = (TextView) findViewById(R.id.tv_business_introduce);

        businessMessagePresenter.Show();

    }

    @Override
    public String getId() {
        return Home_business.business.getId();
    }

    @Override
    public void toMainActivity(Business business) {
        if (business != null && !"".equals(business)) {
            this.business = business;
        }
        //显示数据
        tv_business_loginName.setText(business.getLoginName());
        tv_business_name.setText(business.getName());
        tv_business_contacts.setText(business.getContacts());
        tv_business_phone.setText(business.getPhoneNumber());
        tv_business_address.setText(business.getAddress());
        tv_business_identities.setText(business.getIdentities());
        tv_business_payment.setText(business.getPayment());
        tv_business_introduce.setText(business.getDescription());

    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplication(), "资料加载失败", Toast.LENGTH_SHORT).show();
    }
}
