package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.ChangePasswordListener;
import com.aode.buyoapp.LL.Listener.LoginListener;
import com.aode.buyoapp.LL.Listener.RegisterListener;
import com.aode.buyoapp.LL.Listener.ShowChangeListener;
import com.aode.buyoapp.LL.Listener.ShowListener;
import com.aode.buyoapp.LL.bean.User;
import com.aode.buyoapp.LL.url;
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
    private url url = new url();

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
                .url(url.getUrl() + "/tb/admin/user/login")
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
                .url(url.getUrl() + "/tb/admin/user/add")
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

    @Override
    public void show(String id, final ShowListener showListener) {
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
                .url(url.getUrl() + "/tb/admin/user/updateUI")
                .addParams("id", id)
                .build()
                .execute(new UserCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        showListener.showFailed();
                        System.out.println("错误:" + e);
                    }

                    @Override
                    public void onResponse(User response) {
                        if (response != null)
                            showListener.showSuccess(response);
                        else
                            showListener.showFailed();
                    }

                    @Override
                    public User parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });
    }

    @Override
    public void change(User user, final ShowChangeListener showChangeListener) {
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/user/update")
                .addParams("id", user.getId())
                .addParams("LoginName", user.getLoginName())
                .addParams("phoneNumber", user.getPhoneNumber())
                .addParams("email", user.getEmail())
                .addParams("gender", user.getGender())
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        showChangeListener.changeFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        showChangeListener.changeSuccess();
                    }
                });
    }

    @Override
    public void changePassword(String id, String oldPassword, String newPassword, final ChangePasswordListener changePasswordListener) {
        abstract class IntegerCallback extends Callback<Integer> {
            @Override
            public Integer parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Integer integer = Integer.valueOf(string);
                return integer;
            }
        }
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/user/updatePassword")
                .addParams("id", id)
                .addParams("oldPassword", oldPassword)
                .addParams("newPassword", newPassword)
                .build()
                .execute(new IntegerCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        changePasswordListener.changeFailed();
                    }

                    @Override
                    public void onResponse(Integer response) {
                        if(response.intValue() == 0){
                            changePasswordListener.changeSuccess();
                        }else{
                            changePasswordListener.changeFailed();
                        }
                    }
                    @Override
                    public Integer parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });


                }
    }
