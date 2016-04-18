package com.aode.buyoapp.LL.Personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessMessageChangePresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.view.IBusinessMessageChangeView;
import com.aode.buyoapp.R;


public class Business_Message_update extends AppCompatActivity implements IBusinessMessageChangeView {
    private Button button;
    private String change_bLoginName, change_readlName, change_bPhone, change_bAddress, change_bIntroduce;
    private Business business;

    /**
     * 修改商家资料交互接口
     */
    BusinessMessageChangePresenter businessMessageChangePresenter = new BusinessMessageChangePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_message_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_personMessage);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        update();
    }

    public void update() {
        button = (Button) findViewById(R.id.btn_update_businessMessage_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得view的值存入一个对象返回过去
                change_bLoginName = ((EditText) findViewById(R.id.change_bLoginName)).getText().toString();
                change_readlName = ((EditText) findViewById(R.id.change_RealName)).getText().toString();
                change_bPhone = ((EditText) findViewById(R.id.change_bPhone)).getText().toString();
                change_bAddress = ((EditText) findViewById(R.id.change_bAddress)).getText().toString();
                change_bIntroduce = ((EditText) findViewById(R.id.change_bIntroduce)).getText().toString();
                business = new Business();
                business.setId(Home_business.business.getId());
                business.setLoginName(change_bLoginName);
                business.setName(change_readlName);
                business.setPhoneNumber(change_bPhone);
                business.setAddress(change_bAddress);
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
