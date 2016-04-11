package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.bean.Business;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务接口实现类
 */
public class BusinessBiz implements IBusinessBiz {

    //登录功能
    @Override
    public void login(final String name, final String password, final BLoginListener bLoginListener) {

        abstract class BusinessCallback extends Callback<Business> {
            @Override
            public Business parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Business business = new Gson().fromJson(string, Business.class);
                return business;
            }
        }
        OkHttpUtils
                .post()
                .url("http://192.168.43.242:8080/tb/admin/business/login")
                .addParams("name", name)
                .addParams("password", password)
                .build()
                .execute(new BusinessCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        bLoginListener.bLoginFailed();
                        System.out.println("错误:" + e);
                    }

                    @Override
                    public void onResponse(Business response) {
                        if (response != null)
                            bLoginListener.bLoginSuccess(response);
                        else
                            bLoginListener.bLoginFailed();
                    }

                    @Override
                    public Business parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);

                    }
                });

    }

    //注册功能
    @Override
    public void register(String name, String password, final BRegisterListener bRegisterListener) {
        OkHttpUtils
                .post()
                .url("http://192.168.43.242:8080/tb/admin/business/add")
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
                        bRegisterListener.bRegisterFailed();
                        System.out.println("错误:" + e);
                    }

                    @Override
                    public void onResponse(Object response) {
                        bRegisterListener.bRegisterSuccess();
                    }
                });
    }
}
