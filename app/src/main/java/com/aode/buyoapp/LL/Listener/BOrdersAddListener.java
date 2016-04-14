package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/15.Go.
 * 通知商家，商家下单的状态
 */
public interface BOrdersAddListener {
    //个人下单成功回调接口
    void BOrdersAddSuccess();

    //个人下单失败回调接口
    void BOrdersAddFailed();
}
