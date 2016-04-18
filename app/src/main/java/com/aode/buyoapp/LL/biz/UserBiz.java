package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.BQueryPermissionListener;
import com.aode.buyoapp.LL.Listener.ChangePasswordListener;
import com.aode.buyoapp.LL.Listener.LoginListener;
import com.aode.buyoapp.LL.Listener.OrdersAddListener;
import com.aode.buyoapp.LL.Listener.OrdersShowListener;
import com.aode.buyoapp.LL.Listener.OrdersUpDateListener;
import com.aode.buyoapp.LL.Listener.QueryProductListener;
import com.aode.buyoapp.LL.Listener.RegisterListener;
import com.aode.buyoapp.LL.Listener.ShowChangeListener;
import com.aode.buyoapp.LL.Listener.ShowListener;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.bean.User;
import com.aode.buyoapp.LL.url;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

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
                .addParams("name",user.getName())
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
                        if (response.intValue() == 0) {
                            changePasswordListener.changeSuccess();
                        } else {
                            changePasswordListener.changeFailed();
                        }
                    }

                    @Override
                    public Integer parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });


    }

    @Override
    public void queryAllProduct(final QueryProductListener queryProductListener) {
        abstract class ClothCallback extends Callback<List<Cloth>> {
            @Override
            public List<Cloth> parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type listType = new TypeToken<LinkedList<Cloth>>() {
                }.getType();
                List<Cloth> cloths = new Gson().fromJson(string, listType);
                return cloths;
            }
        }
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/")
                .build()
                .execute(new ClothCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        queryProductListener.loginFailed();
                    }

                    @Override
                    public void onResponse(List<Cloth> response) {
                        queryProductListener.loginSuccess(response);
                    }

                    @Override
                    public List<Cloth> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });
    }

    /**
     * 个人添加订单
     *
     * @param orders
     * @param ordersAddListener
     */
    @Override
    public void OrdersAdd(Orders orders, final OrdersAddListener ordersAddListener) {
        String json = new Gson().toJson(orders);
        System.out.println("用户订单json：" + json);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/user/orders/add")
                .addParams("ordersStr", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        ordersAddListener.OrdersAddFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        ordersAddListener.OrdersAddSuccess();
                    }
                });
    }

    /**
     * 个人订单展示
     *
     * @param id
     * @param ordersShowListener
     */
    @Override
    public void OrdersShow(String id, final OrdersShowListener ordersShowListener) {
        abstract class OrdersCallback extends Callback<List<Orders>> {
            @Override
            public List<Orders> parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type listType = new TypeToken<List<Orders>>() {
                }.getType();
                System.out.println("OrdersShow string："+string);
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create();
                List<Orders> orderses = gson.fromJson(string, listType);
                return orderses;
            }
        }

        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/user/orders/list")
                .addParams("id", id)
                .build()
                .execute(new OrdersCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("错误" + e);
                        ordersShowListener.OrdersShowFailed();
                    }

                    @Override
                    public void onResponse(List<Orders> response) {
                        if (response != null && !response.isEmpty()){
                            ordersShowListener.OrdersShowSuccess(response);
                        }else {
                            ordersShowListener.OrdersShowNo();
                        }

                    }

                    @Override
                    public List<Orders> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }

                });
    }

    /**
     * 个人修改订单(订单来更改订单状态)
     *
     * @param orders
     * @param ordersUpDateListener
     */
    @Override
    public void OrdersUpDate(Orders orders, final OrdersUpDateListener ordersUpDateListener) {
        String json = new Gson().toJson(orders);
        System.out.println("后台个人修改订单："+json);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/user/orders/updateState")
                .addParams("ordersStr", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        ordersUpDateListener.OrdersUpDateFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        ordersUpDateListener.OrdersUpDateSuccess();
                    }
                });
    }
    /**
     * 用户查看个人权限
     * @param id
     * @param bQueryPermissionListener
     */
    @Override
    public void QuseryPermission(String id, final BQueryPermissionListener bQueryPermissionListener) {
        abstract class PermissionCallback extends Callback<List<User>> {
            @Override
            public List<User> parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type listType = new TypeToken<List<User>>() {
                }.getType();
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create();
                List<User> users = gson.fromJson(string, listType);
                return users;
            }
        }

        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/user/index")
                .addParams("id", id)
                .build()
                .execute(new PermissionCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        bQueryPermissionListener.getFailed();
                    }

                    @Override
                    public void onResponse(List<User> response) {
                        bQueryPermissionListener.getSuccess(response);
                    }


                    @Override
                    public List<User> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }

                });
    }
}
