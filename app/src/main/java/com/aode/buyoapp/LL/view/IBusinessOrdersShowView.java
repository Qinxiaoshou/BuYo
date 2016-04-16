package com.aode.buyoapp.LL.view;

import com.aode.buyoapp.LL.bean.Orders;

import java.util.List;

/**
 * Created by LiLei on 2016/4/11.Go.
 * 商家查看订单的view接口
 */
public interface IBusinessOrdersShowView {
    String PutId();

    void toMainActivity(List<Orders> orderses);//成功提示

    void showFailedError();//失败提示

    void showNo();//没有订单
}
