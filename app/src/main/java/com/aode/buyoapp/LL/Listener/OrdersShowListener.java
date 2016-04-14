package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Orders;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知个人，查看订单的状态
 */
public interface OrdersShowListener {
    //查看订单成功回调接口
    void OrdersShowSuccess(List<Orders> orderses);

    //查看订单失败回调接口
    void OrdersShowFailed();
}
