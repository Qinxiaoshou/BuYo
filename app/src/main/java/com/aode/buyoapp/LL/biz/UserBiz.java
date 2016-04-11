package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.bean.User;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务接口实现类
 */
public class UserBiz implements IUserBiz {

    //登录功能
    @Override
    public void login(final String name, final String password, final LoginListener loginListener) {

        abstract class UserCallback extends Callback<User> {
            @Override
            public User parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                User user = new Gson().fromJson(string, User.class);
                return user;
            }
        }
        OkHttpUtils
                .post()
                .url("http://192.168.43.242:8080/tb/admin/user/login")
                .addParams("loginName", name)
                .addParams("password", password)
                .build()
                .execute(new UserCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        loginListener.loginFailed();
                        System.out.println("错误:" + e);
                    }

                    @Override
                    public void onResponse(User response) {
                        if (response != null)
                            loginListener.loginSuccess(response);
                        else
                            loginListener.loginFailed();
                    }

                    @Override
                    public User parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);

                    }
                });

    }

    //注册功能
    @Override
    public void register(String name, String password, final RegisterListener registerListener) {
        OkHttpUtils
                .post()
                .url("http://192.168.43.242:8080/tb/admin/user/add")
                .addParams("loginName", name)
                .addParams("password", password)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        registerListener.registerFailed();
                        System.out.println("错误:" + e);
                    }

                    @Override
                    public void onResponse(Object response) {
                        registerListener.registerSuccess();
                    }
                });
    }
}
