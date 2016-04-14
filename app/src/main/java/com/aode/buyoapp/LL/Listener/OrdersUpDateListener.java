package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知个人，修改订单的状态
 */
public interface OrdersUpDateListener {
    //修改订单成功回调接口
    void OrdersUpDateSuccess();

    //修改订单失败回调接口
    void OrdersUpDateFailed();
}
