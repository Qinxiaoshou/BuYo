package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Orders;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知商家，查看订单的状态
 */
public interface BOrdersShowListener {
    //查看订单成功回调接口
    void BOrdersShowSuccess(List<Orders> orderses);

    //查看订单失败回调接口
    void BOrdersShowFailed();

    //查看订单为空
    void BOrdersShowNo();
}
