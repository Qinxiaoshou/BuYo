package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.OrdersShowListener;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserOrdersShowView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/15.Go.
 * 个人业务回调接口,获取订单的状态
 */
public class UserOrdersShowPresenter {
    private IUserBiz userBiz;
    private IUserOrdersShowView userOrdersShowView;
    private Handler mHandler = new Handler();

    public UserOrdersShowPresenter(IUserOrdersShowView userOrdersShowView) {
        //设置view和业务层，在此调用业务层
        this.userOrdersShowView = userOrdersShowView;
        this.userBiz = new UserBiz();
    }

    public void ordersShow() {
        userBiz.OrdersShow(userOrdersShowView.PutId(), new OrdersShowListener() {
            @Override
            public void OrdersShowSuccess(final List<Orders> orderses) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userOrdersShowView.toMainActivity(orderses);
                    }
                });
            }

            @Override
            public void OrdersShowFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userOrdersShowView.showFailedError();
                    }
                });
            }

            @Override
            public void OrdersShowNo() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userOrdersShowView.showNo();
                    }
                });
            }

        });
    }

}
