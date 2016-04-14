package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知商家，修改订单的状态
 */
public interface BOrdersUpDateListener {
    //修改订单成功回调接口
    void BOrdersUpDateSuccess();

    //修改订单失败回调接口
    void BOrdersUpDateFailed();
}
