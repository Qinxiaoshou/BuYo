package com.aode.buyoapp.LL.Personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessMessageChangePresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.view.IBusinessMessageChangeView;
import com.aode.buyoapp.R;

/**
 * Created by LiLei on 2016/5/20.Go.
 */

public class Business_Message_update extends AppCompatActivity implements IBusinessMessageChangeView {
    private Button button;
    private EditText bLoginName, Name, contacts, bPhone, bAddress, identities, payment, bIntroduce;
    private String change_bLoginName, change_Name, change_contacts, change_bPhone, change_bAddress, change_identities, change_payment, change_bIntroduce;
    private Business before_business, business;

    /**
     * 修改商家资料交互接口
     */
    BusinessMessageChangePresenter businessMessageChangePresenter = new BusinessMessageChangePresenter(this);
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_message_update);
        //返回键
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_personMessage);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //获取输入框
        getEdit();
        //回显数据
        setMsg();
        //更新数据
        upDate();
    }

    public void getEdit() {

        bLoginName = (EditText) findViewById(R.id.change_bLoginName);
        Name = (EditText) findViewById(R.id.change_Name);
        contacts = (EditText) findViewById(R.id.change_contacts);
        bPhone = (EditText) findViewById(R.id.change_bPhone);
        bAddress = (EditText) findViewById(R.id.change_bAddress);
        identities = (EditText) findViewById(R.id.change_bIntroduce);
        payment = (EditText) findViewById(R.id.change_identities);
        bIntroduce = (EditText) findViewById(R.id.change_payment);
    }

    public void setMsg() {
        Intent intent = getIntent();
        before_business = (Business) intent.getSerializableExtra("before_business");
        if (before_business != null && !"".equals(before_business)) {
            System.out.println("回显:" + before_business.toString());
            bLoginName.setText(before_business.getLoginName());
            Name.setText(before_business.getName());
            contacts.setText(before_business.getContacts());
            bPhone.setText(before_business.getPhoneNumber());
            bAddress.setText(before_business.getAddress());
            identities.setText(before_business.getIdentities());
            payment.setText(before_business.getPayment());
            bIntroduce.setText(before_business.getDescription());
        }
    }

    public void upDate() {
        button = (Button) findViewById(R.id.btn_update_businessMessage_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得view的值存入一个对象返回过去
                change_bLoginName = bLoginName.getText().toString();
                change_Name = Name.getText().toString();
                change_contacts = contacts.getText().toString();
                change_bPhone = bPhone.getText().toString();
                change_bAddress = bAddress.getText().toString();
                change_identities = payment.getText().toString();
                change_payment = bIntroduce.getText().toString();
                change_bIntroduce = identities.getText().toString();

                business = new Business();
                business.setId(Home_business.business.getId());
                business.setLoginName(change_bLoginName);
                business.setName(change_Name);
                business.setContacts(change_contacts);
                business.setPhoneNumber(change_bPhone);
                business.setAddress(change_bAddress);
                business.setIdentities(change_identities);
                business.setPayment(change_payment);
                business.setDescription(change_bIntroduce);
                if (!"".equals(business.getLoginName()) && business.getLoginName() != null) {
                    businessMessageChangePresenter.change();
                } else {
                    Toast.makeText(getApplication(), "名字不能为空!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public Business getBusiness() {
        return business;
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(getApplication(), "修改成功!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplication(), "修改失败,请检查网络和内容!", Toast.LENGTH_SHORT).show();
    }
}
