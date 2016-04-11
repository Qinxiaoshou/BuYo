package com.aode.buyoapp.LL;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.BusinessLoginPresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.view.IBusinessLoginView;
import com.aode.buyoapp.R;

public class Login_business extends AppCompatActivity implements IBusinessLoginView {
    private String name, password;
    private Button btn_login_login, btn_register;

    /**
     * 这里是view层.其中 IUserLoginView 登录页面接口
     * Presenter层,实现Model和View之间交互
     */
    private BusinessLoginPresenter mBusinessLoginPresenter = new BusinessLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_person_login);
        //登录和注册方法
        Login();
        register();
    }

    //个人登录功能
    public void Login() {

        btn_login_login = (Button) findViewById(R.id.btn_login_login);
        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ((EditText) findViewById(R.id.et_loginName)).getText().toString();
                password = ((EditText) findViewById(R.id.et_password)).getText().toString();
               /* UserBiz userBiz = new UserBiz();
                userBiz.login(name, password, );*/
                //交互接口-登录操作
                mBusinessLoginPresenter.Login();
            }
        });
    }

    public void register() {
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Register_person.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public String getBusinessName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void toMainActivity(Business business) {
        System.out.println(business);
        //进入主页
        finish();
        Intent intent = new Intent(this,Home_business.class);
        Home_business.loginName = business.getLoginName();
        startActivity(intent);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplication(), "登录失败,请检查网络密码账号!", Toast.LENGTH_LONG).show();
    }

}