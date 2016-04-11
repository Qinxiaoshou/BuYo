package com.aode.buyoapp.LL;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.UserLoginPresenter;
import com.aode.buyoapp.LL.bean.User;
import com.aode.buyoapp.LL.view.IUserLoginView;
import com.aode.buyoapp.R;

public class Login_person extends Fragment implements IUserLoginView {
    private View view;
    private String name, password;
    private Button btn_login_login,btn_register;

    private onNameListener listener;

    /**
     * 这里是view层.其中 IUserLoginView 登录页面接口
     * Presenter层,实现Model和View之间交互
     */
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_login, container, false);
        Login();
        register();
        return view;

    }

    //个人登录功能
    public void Login() {

        btn_login_login = (Button) view.findViewById(R.id.btn_login_login);
        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ((EditText) view.findViewById(R.id.et_loginName)).getText().toString();
                password = ((EditText) view.findViewById(R.id.et_password)).getText().toString();
               /* UserBiz userBiz = new UserBiz();
                userBiz.login(name, password, );*/
                //交互接口-登录操作
                mUserLoginPresenter.Login();
            }
        });
    }

    public void register(){
        btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Register_person.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void toMainActivity(User user) {
        System.out.println(user);
        //保存登录状态
        Home_person.result = true;
        //更新个人页面并进入
        listener.onTest(user.getLoginName());
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getActivity(), "登录失败,请检查网络密码账号!", Toast.LENGTH_LONG).show();
    }


    //fragment和activity交互接口
    public interface onNameListener {
        public void onTest(String name);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (onNameListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}