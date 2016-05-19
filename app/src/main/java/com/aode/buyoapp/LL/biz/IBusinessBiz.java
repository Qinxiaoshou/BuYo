package com.aode.buyoapp.LL.biz;

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
import com.aode.buyoapp.LL.Listener.BQueryProductListener;
import com.aode.buyoapp.LL.Listener.BRegisterListener;
import com.aode.buyoapp.LL.Listener.BSearchListener;
import com.aode.buyoapp.LL.Listener.BShowChangeListener;
import com.aode.buyoapp.LL.Listener.BShowListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Orders;

import java.io.File;
import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务类接口
 */
public interface IBusinessBiz {
    /**
     * 基础操作
     */
    //登录接口
    void login(String loginName, String password, BLoginListener bLoginListener);

    //注册接口
    void register(String loginName, String name, String password, BRegisterListener bRegisterListener);

    //展示资料接口
    void show(String id, BShowListener BShowListener);

    //修改资料接口
    void change(Business business, BShowChangeListener bShowChangeListener);

    /**
     * 商品
     */
    //增加我商家的商品
    void addProduct(Cloth product, BAddProductListener bAddProductListener);
    //void addProduct(Cloth cloth, BAddProductListener bAddProductListener, File picture);

    //删除我商家的商品
    void deleteProduct(Cloth cloth, BDeleteProductListener bDeleteProductListener);

    //获取我商家的商品
    void getProduct(BQueryProductListener bQueryProductListener);

    //修改商品资料
    void ProductChange(Cloth cloth, BProductChangeListener bProductChangeListener);

    //获取我商家布匹大全的布匹列表
    void getClothList(String type, BClothListListener bClothListListener);
    /**
     * 商家关系
     */
    //搜索商家
    void SearchBusiness(String name, BSearchListener bSearchListener);

    //设置友好商家
    void queryBusinessPermission(String bId, String fId, List<Cloth> cloths, BQueryBusinessPermissionListener bQueryBusinessPermissionListener);

    //查看友好商家
    void getFriendBusiness(String id, BBusinessFriendListener bBusinessFriendListener);

    //修改友好商家和哪些商品
    void changeFriendBusiness(String bId, String fId, List<Cloth> cloths, BFriendBusinessChangeListener bFriendBusinessChangeListener);

    //查看他人商家为本商家设置的友好商家以及商品
    void getFriendBusinessToMe(String id, BBusinessFriendToMeListener bBusinessFriendToMeListener);

    //查询单个友好商家被设置权限的商品
    void queryBusinessSettedPermissionCloth(String bId, String fId, BBusinessFriendSettedPermissionListener bBusinessFriendSettedPermissionListener);

    /**
     * 订单
     */
    //个人下单
    void OrdersAdd(Orders orders, BOrdersAddListener bOrdersAddListener);

    //个人获取自己的订单
    void OrdersShow(String id, BOrdersShowListener bOrdersShowListener);

    //个人修改自己的订单
    void OrdersUpDate(Orders orders, BOrdersUpDateListener bOrdersUpDateListener);



}
