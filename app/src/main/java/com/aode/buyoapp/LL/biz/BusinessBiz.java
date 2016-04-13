package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Listener.BAddProductListener;
import com.aode.buyoapp.LL.Listener.BDeleteProductListener;
import com.aode.buyoapp.LL.Listener.BLoginListener;
import com.aode.buyoapp.LL.Listener.BProductChangeListener;
import com.aode.buyoapp.LL.Listener.BQueryBusinessPermissionListener;
import com.aode.buyoapp.LL.Listener.BQueryProductListener;
import com.aode.buyoapp.LL.Listener.BRegisterListener;
import com.aode.buyoapp.LL.Listener.BSearchListener;
import com.aode.buyoapp.LL.Listener.BShowChangeListener;
import com.aode.buyoapp.LL.Listener.BShowListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.url;
import com.google.gson.Gson;
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
 * 商家业务接口实现类
 */
public class BusinessBiz implements IBusinessBiz {
    private url url = new url();

    /**
     * 登录功能
     * @param loginName
     * @param password
     * @param bLoginListener
     */
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

    /**
     * 展示商家资料
     *
     * @param id
     * @param BShowListener
     */
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

    /**
     * 修改商家资料
     *
     * @param business
     * @param bShowChangeListener
     */
    @Override
    public void change(Business business, final BShowChangeListener bShowChangeListener) {
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/update")
                .addParams("id", business.getId())
                .addParams("LoginName", business.getLoginName())
                .addParams("phoneNumber", business.getPhoneNumber())
                .addParams("address", business.getAddress())
                .addParams("description", business.getDescription())
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

    /**
     * 增加商品
     *
     * @param cloth
     * @param bAddProductListener
     */
    @Override
    public void addProduct(Cloth cloth, final BAddProductListener bAddProductListener) {
        String json = new Gson().toJson(cloth);
        System.out.println(json);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/cloth/save")
                .addParams("clothStr", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        bAddProductListener.addFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bAddProductListener.addSuccess();
                    }
                });
    }

    /**
     * 删除商品
     *
     * @param cloth
     * @param bDeleteProductListener
     */
    @Override
    public void deleteProduct(Cloth cloth, final BDeleteProductListener bDeleteProductListener) {
        String json = new Gson().toJson(cloth);
        System.out.println(json);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/cloth/delete")
                .addParams("clothStr", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        bDeleteProductListener.deleteFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bDeleteProductListener.deleteSuccess();
                    }
                });
    }

    /**
     * 获取商品
     *
     * @param bQueryProductListener
     */
    @Override
    public void getProduct(final BQueryProductListener bQueryProductListener) {
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
                .url(url.getUrl() + "/tb/admin/cloth/listByBus")
                .addParams("id", Home_business.business.getId())
                .build()
                .execute(new ClothCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        bQueryProductListener.getFailed();
                    }

                    @Override
                    public void onResponse(List<Cloth> response) {
                        bQueryProductListener.getSuccess(response);
                    }

                    @Override
                    public List<Cloth> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }
                });
    }

    /**
     * 修改商品
     *
     * @param cloth
     * @param bProductChangeListener
     */
    @Override
    public void ProductChange(Cloth cloth, final BProductChangeListener bProductChangeListener) {
        String json = new Gson().toJson(cloth);
        System.out.println(json);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/cloth/update")
                .addParams("clothStr", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("失败:" + e);
                        bProductChangeListener.changeFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bProductChangeListener.changeSuccess();
                    }
                });
    }

    /**
     * 搜索商家
     *
     * @param name
     * @param bSearchListener
     */
    @Override
    public void SearchBusiness(String name, final BSearchListener bSearchListener) {
        abstract class BusinessCallback extends Callback<List<Business>> {
            @Override
            public List<Business> parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type listType = new TypeToken<LinkedList<Business>>() {
                }.getType();
                List<Business> businesses = new Gson().fromJson(string, listType);
                return businesses;
            }
        }
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/search")
                .addParams("name", name)
                .build()
                .execute(new BusinessCallback() {
                    @Override
                    public List<Business> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("失败:" + e);
                        bSearchListener.bSearchFailed();
                    }

                    @Override
                    public void onResponse(List<Business> response) {
                        if (response != null && !response.isEmpty()) {
                            bSearchListener.bSearchNo();
                        } else {
                            bSearchListener.bSearchSuccess();
                        }
                    }
                });
    }

    /**
     * 设置商家权限
     *
     * @param bId
     * @param fId
     * @param cloths
     * @param bQueryBusinessPermissionListener
     */
    @Override
    public void queryBusinessPermission(String bId, String fId, List<Cloth> cloths, final BQueryBusinessPermissionListener bQueryBusinessPermissionListener) {
        String json = new Gson().toJson(cloths);
        System.out.println(json);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/insertAuthorities")
                .addParams("bId", bId)
                .addParams("fid", fId)
                .addParams("cloths", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("失败:" + e);
                        bQueryBusinessPermissionListener.bQueryPermissionFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bQueryBusinessPermissionListener.bQueryPermissionSuccess();
                    }
                });
    }
}
