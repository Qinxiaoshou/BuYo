package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Homepage.AllCloth.BusinessClothListFragment;
import com.aode.buyoapp.LL.Listener.BAddProductListener;
import com.aode.buyoapp.LL.Listener.BBusinessFriendListener;
import com.aode.buyoapp.LL.Listener.BBusinessFriendSettedPermissionListener;
import com.aode.buyoapp.LL.Listener.BBusinessFriendToMeListener;
import com.aode.buyoapp.LL.Listener.BClothListListener;
import com.aode.buyoapp.LL.Listener.BDeleteProductListener;
import com.aode.buyoapp.LL.Listener.BFriendBusinessChangeListener;
import com.aode.buyoapp.LL.Listener.BLoginListener;
import com.aode.buyoapp.LL.Listener.BOrdersAddListener;
import com.aode.buyoapp.LL.Listener.BOrdersShowListener;
import com.aode.buyoapp.LL.Listener.BOrdersUpDateListener;
import com.aode.buyoapp.LL.Listener.BProductChangeListener;
import com.aode.buyoapp.LL.Listener.BQueryBusinessPermissionListener;
import com.aode.buyoapp.LL.Listener.BQueryNoMeProductListener;
import com.aode.buyoapp.LL.Listener.BQueryProductListener;
import com.aode.buyoapp.LL.Listener.BRegisterListener;
import com.aode.buyoapp.LL.Listener.BSearchListener;
import com.aode.buyoapp.LL.Listener.BShowChangeListener;
import com.aode.buyoapp.LL.Listener.BShowListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Orders;
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
 * 商家业务接口实现类
 */
public class BusinessBiz implements IBusinessBiz {
    private url url = new url();

    /**
     * 登录功能
     *
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

    /**
     * 注册功能
     *
     * @param loginName
     * @param name
     * @param password
     * @param bRegisterListener
     */
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
                .addParams("name", business.getName())
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
                        System.out.println("后台错误" + e);
                        bShowChangeListener.changeFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bShowChangeListener.changeSuccess();
                    }
                });
    }

   /* *//**
     * 增加商品
     *
     * @param cloth
     * @param bAddProductListener
     *//*
    @Override
    public void addProduct(Cloth cloth, final BAddProductListener bAddProductListener, File picture) {
        String json = new Gson().toJson(cloth);
        System.out.println(json);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/cloth/save2")
                .addParams("clothStr", json)
                .addFile("picture", UUID.randomUUID().toString(), picture)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println(e);
                        bAddProductListener.addFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bAddProductListener.addSuccess();
                    }
                });
    }*/

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
                .url(url.getUrl() + "/tb/admin/cloth/save2")
                .addParams("clothStr", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println(e);
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
     * 获取商家的商品
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

    @Override
    public void getNoMeProduct(final BQueryNoMeProductListener bQueryNoMeProductListener) {
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
                .url(url.getUrl() + "/tb/busClothComplete")
                .addParams("id", Home_business.business.getId())
                .build()
                .execute(new ClothCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        bQueryNoMeProductListener.getFailed();
                    }

                    @Override
                    public void onResponse(List<Cloth> response) {
                        bQueryNoMeProductListener.getSuccess(response);
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
     * 根据类型获取商家的商品列表
     *
     * @param type
     * @param bClothListListener
     */
    @Override
    public void getClothList(String type, final BClothListListener bClothListListener) {

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
                .url(url.getUrl() + "/tb/admin/cloth/conditionSearch")
                .addParams(BusinessClothListFragment.label, type)
                .build()
                .execute(new ClothCallback() {

                    @Override
                    public List<Cloth> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        bClothListListener.getClothListFailed();
                    }

                    @Override
                    public void onResponse(List<Cloth> response) {
                        if (response != null && !response.isEmpty()) {
                            bClothListListener.getClothListSuccess(response);
                        } else {
                            bClothListListener.getClothListNone();
                        }
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
                            bSearchListener.bSearchSuccess(response);
                        } else {
                            bSearchListener.bSearchNo();

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
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/insertAuthorities")
                .addParams("bId", bId)
                .addParams("fId", fId)
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
                        bQueryBusinessPermissionListener.bQueryPermissionFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bQueryBusinessPermissionListener.bQueryPermissionSuccess();
                    }
                });
    }

    /**
     * 获取友好商家
     *
     * @param id
     * @param bBusinessFriendListener
     */
    @Override
    public void getFriendBusiness(String id, final BBusinessFriendListener bBusinessFriendListener) {

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
                .url(url.getUrl() + "/tb/admin/business/showAuthority")
                .addParams("id", id)
                .build()
                .execute(new BusinessCallback() {
                    @Override
                    public List<Business> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("失败:" + e);
                        bBusinessFriendListener.bFriendBusinessFailed();
                    }

                    @Override
                    public void onResponse(List<Business> response) {
                        if (response != null && !response.isEmpty()) {
                            System.out.println("后台：" + response);
                            bBusinessFriendListener.bFriendBusinessSuccess(response);
                        } else {
                            bBusinessFriendListener.bFriendBusinessNo();

                        }
                    }
                });
    }

    /**
     * 修改友好商家
     *
     * @param bId
     * @param fId
     * @param cloths
     * @param bFriendBusinessChangeListener
     */
    @Override
    public void changeFriendBusiness(String bId, String fId, List<Cloth> cloths, final BFriendBusinessChangeListener bFriendBusinessChangeListener) {
        String json = new Gson().toJson(cloths);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/updateAuthorities")
                .addParams("bId", bId)
                .addParams("fId", fId)
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
                        bFriendBusinessChangeListener.bFriendBusinessChangeFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bFriendBusinessChangeListener.bFriendBusinessChangeSuccess();
                    }
                });
    }

    /**
     * 他人为我设置的商家权限
     *
     * @param id
     * @param bBusinessFriendToMeListener
     */
    @Override
    public void getFriendBusinessToMe(String id, final BBusinessFriendToMeListener bBusinessFriendToMeListener) {
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
                .url(url.getUrl() + "/tb/admin/business/friendBusiness")
                .addParams("id", id)
                .build()
                .execute(new BusinessCallback() {
                    @Override
                    public List<Business> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("失败:" + e);
                        bBusinessFriendToMeListener.bFriendBusinessToMeFailed();
                    }

                    @Override
                    public void onResponse(List<Business> response) {
                        if (response != null && !response.isEmpty()) {
                            System.out.println(response);
                            bBusinessFriendToMeListener.bFriendBusinessToMeSuccess(response);
                        } else {
                            bBusinessFriendToMeListener.bFriendBusinessToMeNo();

                        }
                    }
                });
    }

    /**
     * 商家添加订单
     *
     * @param orders
     * @param bOrdersAddListener
     */
    @Override
    public void OrdersAdd(Orders orders, final BOrdersAddListener bOrdersAddListener) {
        String json = new Gson().toJson(orders);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/orders/add")
                .addParams("orders", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        bOrdersAddListener.BOrdersAddFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bOrdersAddListener.BOrdersAddSuccess();
                    }
                });
    }

    /**
     * 商家查看订单
     *
     * @param id
     * @param bOrdersShowListener
     */
    @Override
    public void OrdersShow(String id, final BOrdersShowListener bOrdersShowListener) {
        abstract class OrdersCallback extends Callback<List<Orders>> {
            @Override
            public List<Orders> parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type listType = new TypeToken<List<Orders>>() {
                }.getType();
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create();
                List<Orders> orderses = gson.fromJson(string, listType);
                System.out.println("商家查看订单列表:" + orderses);
                return orderses;
            }
        }

        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/orders/list")
                .addParams("id", id)
                .build()
                .execute(new OrdersCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("错误:" + e);
                        bOrdersShowListener.BOrdersShowFailed();
                    }

                    @Override
                    public void onResponse(List<Orders> response) {
                        System.out.println(response);
                        if (response != null && !response.isEmpty()) {
                            bOrdersShowListener.BOrdersShowSuccess(response);
                        } else {
                            bOrdersShowListener.BOrdersShowNo();

                        }
                    }

                    @Override
                    public List<Orders> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }

                });
    }

    /**
     * 友好商家查询单个商家被设置商品权限的商品集合
     *
     * @param bId
     * @param fId
     * @param bBusinessFriendSettedPermissionListener
     */
    @Override
    public void queryBusinessSettedPermissionCloth(String bId, String fId, final BBusinessFriendSettedPermissionListener bBusinessFriendSettedPermissionListener) {
        abstract class BusinessSettedPProductCallback extends Callback<List<Cloth>> {
            @Override
            public List<Cloth> parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                Type listType = new TypeToken<LinkedList<Cloth>>() {
                }.getType();
                List<Cloth> cloths = new Gson().fromJson(string, listType);
                System.out.println("单个商家被设置商品权限的商品集合:" + cloths);
                return cloths;
            }
        }
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/cloth/authorityMsg")
                .addParams("b_id", bId)
                .addParams("f_id", fId)
                .build()
                .execute(new BusinessSettedPProductCallback() {
                    @Override
                    public List<Cloth> parseNetworkResponse(Response response) throws IOException {
                        return super.parseNetworkResponse(response);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("失败:" + e);
                        bBusinessFriendSettedPermissionListener.bSettedClothsFailed();
                    }

                    @Override
                    public void onResponse(List<Cloth> response) {
                        if (response != null && !response.isEmpty()) {
                            System.out.println("后台：" + response);
                            bBusinessFriendSettedPermissionListener.bFriendSettedBusinessPermissionSuccess(response);
                        } else {
                            bBusinessFriendSettedPermissionListener.bFriendBusinessToMeNo();

                        }
                    }
                });

    }

    /**
     * 商家修改订单(发货、取消发货)
     *
     * @param orders
     * @param bOrdersUpDateListener
     */
    @Override
    public void OrdersUpDate(Orders orders, final BOrdersUpDateListener bOrdersUpDateListener) {
        String json = new Gson().toJson(orders);
        System.out.println("后台" + json);
        OkHttpUtils
                .post()
                .url(url.getUrl() + "/tb/admin/business/orders/updateState")
                .addParams("ordersStr", json)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        bOrdersUpDateListener.BOrdersUpDateFailed();
                    }

                    @Override
                    public void onResponse(Object response) {
                        bOrdersUpDateListener.BOrdersUpDateSuccess();
                    }
                });
    }


}
