package com.aode.buyoapp.LL.Personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.Presenter.UserPasswordChangePresenter;
import com.aode.buyoapp.LL.view.IUserPasswordChangeView;
import com.aode.buyoapp.R;

public class Person_password_update extends AppCompatActivity implements IUserPasswordChangeView {
    private Button btn_update_personPassword;
    private String change_oldPassword, change_newPassword;
    private UserPasswordChangePresenter userPasswordChangePresenter = new UserPasswordChangePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_password_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_personMessage);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //获取并修改密码
        password();
    }

    public void password() {
        btn_update_personPassword = (Button) findViewById(R.id.btn_update_personPassword);
        btn_update_personPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_oldPassword = ((EditText) findViewById(R.id.change_oldPassword)).getText().toString();
                change_newPassword = ((EditText) findViewById(R.id.change_newPassword)).getText().toString();

                if ("".equals(Home_person.id)) {
                    Toast.makeText(getApplication(), "错误，请登录后进行修改", Toast.LENGTH_SHORT).show();
                } else {
                    if ("".equals(change_oldPassword) || "".equals(change_newPassword)) {
                        Toast.makeText(getApplication(), "密码不能为空!请正确输入!", Toast.LENGTH_SHORT).show();
                    } else {
                        userPasswordChangePresenter.change();
                    }
                }
            }
        });
    }

    @Override
    public String getId() {
        return Home_person.id;
    }

    @Override
    public String getOldPassword() {
        return change_oldPassword;
    }

    @Override
    public String getNewPassword() {
        return change_newPassword;
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(getApplication(), "密码修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public void showFailedError() {
        Toast.makeText(getApplication(), "密码修改失败,请确定旧密码和网络", Toast.LENGTH_SHORT).show();
    }
}
