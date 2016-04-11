package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.url;
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
    private url url = new url();

    //登录功能
    @Override
    public void login(final String loginName, final String password, final BLoginListener bLoginListener) {

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
                .url(url.getUrl() + "/tb/admin/business/login")
                .addParams("loginName", loginName)
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
    public void register(String loginName, String name, String password, final BRegisterListener bRegisterListener) {
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/add")
                .addParams("name", name)
                .addParams("loginName", loginName)
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

    @Override
    public void show(String id, final BShowListener BShowListener) {

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
                .url(url.getUrl() + "/tb/admin/business/show")
                .addParams("id", id)
                .build()
                .execute(new BusinessCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        BShowListener.showFailed();
                        System.out.println("错误:" + e);
                    }

                    @Override
                    public void onResponse(Business response) {
                        if (response != null)
                            BShowListener.showSuccess(response);
                        else
                            BShowListener.showFailed();
                    }

                    @Override
                    public Business parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });
    }

    @Override
    public void change(Business business,final BShowChangeListener bShowChangeListener) {
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/update")
                .addParams("id", business.getId())
                .addParams("LoginName",business.getLoginName())
                .addParams("phoneNumber",business.getPhoneNumber())
                .addParams("address",business.getAddress())
                .addParams("description",business.getDescription())
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        bShowChangeListener.changeFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bShowChangeListener.changeSuccess();
                    }
                });
    }
}
