package com.aode.buyoapp.LL;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aode.buyoapp.LL.model.User;
import com.aode.buyoapp.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class Login_person extends Fragment {
    private View view, view2;
    private String name, password;
    private Button btn_login_login, btn_login_logout;

    private User user;

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_login, container, false);
        view2 = inflater.inflate(R.layout.fragment_person_personal, container, false);
        init();
        return view;

    }

    public void init() {

        btn_login_login = (Button) view.findViewById(R.id.btn_login_login);
        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ((EditText) view.findViewById(R.id.et_loginName)).getText().toString();
                password = ((EditText) view.findViewById(R.id.et_password)).getText().toString();
                OkHttpUtils
                        .post()
                        .url("http://192.168.155.6:8080/tb/admin/user/login")
                        .addParams("loginName", name)
                        .addParams("password", password)
                        .build()
                        .execute(new UserCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                System.out.println("错误:" + e);
                            }

                            @Override
                            public void onResponse(User response) {
                                user = response;

                                System.out.println(response);
                            }

                            @Override
                            public User parseNetworkResponse(Response response) throws IOException {
                                return super.parseNetworkResponse(response);

                            }
                        });
            }

            abstract class UserCallback extends Callback<User> {
                @Override
                public User parseNetworkResponse(Response response) throws IOException {
                    String string = response.body().string();
                    User user = new Gson().fromJson(string, User.class);
                    return user;
                }
            }
        });
        btn_login_logout = (Button) view.findViewById(R.id.btn_login_logout);
        btn_login_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post().url("http://192.168.155.6:8080/tb/admin/user/logout").build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("错误:" + e);
                    }

                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                });
            }
        });
    }
}