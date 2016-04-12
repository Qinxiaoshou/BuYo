package com.aode.buyoapp.LL.Personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.Presenter.UserMessagePresenter;
import com.aode.buyoapp.LL.bean.User;
import com.aode.buyoapp.LL.view.IUserMessageView;
import com.aode.buyoapp.R;

public class Person_Message extends AppCompatActivity implements IUserMessageView {
    private Button button;
    private TextView tv_person_name, tv_person_phone, tv_person_email, tv_person_gender;

    private UserMessagePresenter userMessagePresenter = new UserMessagePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_personMessage);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //获取个人信息
        msg();
        //修改信息按钮事件
        update();
    }

    public void update() {
        button = (Button) findViewById(R.id.btn_update_personMessage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(Home_person.id)) {
                    Toast.makeText(getApplication(), "错误，请登录后进行修改", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplication(), Person_Message_update.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void msg() {
        tv_person_name = (TextView) findViewById(R.id.tv_person_name);
        tv_person_phone = (TextView) findViewById(R.id.tv_person_phone);
        tv_person_email = (TextView) findViewById(R.id.tv_person_email);
        tv_person_gender = (TextView) findViewById(R.id.tv_person_gender);

        userMessagePresenter.Show();

    }

    @Override
    public String getId() {
        return Home_person.id;
    }

    @Override
    public void toMainActivity(User user) {
        //显示数据
        tv_person_name.setText(user.getLoginName());
        tv_person_phone.setText(user.getPhoneNumber());
        tv_person_email.setText(user.getEmail());
        tv_person_gender.setText(user.getGender());
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplication(), "资料加载失败", Toast.LENGTH_SHORT).show();
    }
}