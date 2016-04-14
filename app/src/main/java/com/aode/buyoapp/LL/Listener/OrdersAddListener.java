package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知个人，个人下单的状态
 */
public interface OrdersAddListener {
    //个人下单成功回调接口
    void OrdersAddSuccess();

    //个人下单失败回调接口
    void OrdersAddFailed();
}
