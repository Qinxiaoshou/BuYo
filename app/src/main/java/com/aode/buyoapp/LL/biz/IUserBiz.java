package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.BQueryPermissionListener;
import com.aode.buyoapp.LL.Listener.ChangePasswordListener;
import com.aode.buyoapp.LL.Listener.ClothListListener;
import com.aode.buyoapp.LL.Listener.LoginListener;
import com.aode.buyoapp.LL.Listener.LoginOutListener;
import com.aode.buyoapp.LL.Listener.OrdersAddListener;
import com.aode.buyoapp.LL.Listener.OrdersShowListener;
import com.aode.buyoapp.LL.Listener.OrdersUpDateListener;
import com.aode.buyoapp.LL.Listener.QueryAllBusinessListener;
import com.aode.buyoapp.LL.Listener.QueryProductListener;
import com.aode.buyoapp.LL.Listener.QuerySaleBestFourGoodsListener;
import com.aode.buyoapp.LL.Listener.RegisterListener;
import com.aode.buyoapp.LL.Listener.ShowChangeListener;
import com.aode.buyoapp.LL.Listener.ShowListener;
import com.aode.buyoapp.LL.Listener.ClothTypeListener;
import com.aode.buyoapp.LL.Presenter.QueryAllBusinessPresenter;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.bean.User;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务类接口
 */
public interface IUserBiz {
    /**
     * 个人
     */
    //登录接口
    void login(String name, String password, LoginListener loginListener);

    //注册接口
    void register(String name, String password, RegisterListener registerListener);

    //注销
    void loginOut(LoginOutListener loginOutListener);

    //展示资料接口
    void show(String id, ShowListener showListener);

    //修改资料接口
    void change(User user, ShowChangeListener showChangeListener);

    //修改密码接口
    void changePassword(String id, String oldPassword, String newPassword, ChangePasswordListener changePasswordListener);

    //获取个人的商品
    void queryAllProduct(QueryProductListener queryProductListener);

    //获取个人主页的布匹大全的布匹类型
    void getClothType(ClothTypeListener clothTypeListener);

    //获取个人主页的布匹大全的布匹列表
    void getClothList(String type, ClothListListener clothListListener);

    /**
     * 订单
     */
    //个人下单
    void OrdersAdd(Orders orders, OrdersAddListener ordersAddListener);

    //个人获取自己的订单
    void OrdersShow(String id, OrdersShowListener ordersShowListener);

    //个人修改自己的订单
    void OrdersUpDate(Orders orders, OrdersUpDateListener ordersUpDateListener);

    //个人查看个人权限
    void QuseryPermission(String id, BQueryPermissionListener bQueryPermissionListener);

   //厂商一览
    void QueryAllBusiness(QueryAllBusinessListener queryAllBusinessPresenter);

    //首页的热卖商品信息，显示四个最高点击量的布匹
    void QuerySaleBestFourGoods(QuerySaleBestFourGoodsListener querySaleBestFourGoodsListener);
}
