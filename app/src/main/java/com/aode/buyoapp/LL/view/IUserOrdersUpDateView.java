package com.aode.buyoapp.LL.view;

import com.aode.buyoapp.LL.bean.Orders;

/**
 * Created by LiLei on 2016/4/11.Go.
 * 个人修改订单的view接口
 */
public interface IUserOrdersUpDateView {
    Orders PutOrders();

    void toMainActivity();//成功提示

    void showFailedError();//失败提示
}
