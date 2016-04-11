package com.aode.buyoapp.LL;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.UserRegisterPresenter;
import com.aode.buyoapp.LL.view.IUserRegisterView;
import com.aode.buyoapp.R;

/**
 * 注册界面
 *
 * @author 陈映苗
 * @// FIXME: 2016/4/7
 */
public class Register_person extends AppCompatActivity implements IUserRegisterView {
    private Button btn_register_register;
    private String username, password, confirm_password;

    /**
     * 这里是view层.其中 IUserRegisterView 注册页面接口
     * Presenter层,实现Model和View之间交互
     */
    private UserRegisterPresenter mUserRegisterPresenter = new UserRegisterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Register();
    }

    public void Register() {
        btn_register_register = (Button) findViewById(R.id.btn_register_register);
        btn_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ((EditText) findViewById(R.id.et_register_username)).getText().toString();
                password = ((EditText) findViewById(R.id.et_register_password)).getText().toString();
                confirm_password = ((EditText) findViewById(R.id.et_register_confirm_password)).getText().toString();
                if ("".equals(username)||"".equals(password)||"".equals(confirm_password)) {
                    Toast.makeText(getApplication(), "不能有空，请认真输入!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!password.equals(confirm_password)) {
                        Toast.makeText(getApplication(), "密码不一致，请重新输入!", Toast.LENGTH_SHORT).show();
                    } else {
                        mUserRegisterPresenter.Register();
                    }
                }
            }
        });
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void toMainActivity() {
        finish();
        Toast.makeText(getApplication(),"注册成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplication(),"注册失败",Toast.LENGTH_LONG).show();
    }
}
