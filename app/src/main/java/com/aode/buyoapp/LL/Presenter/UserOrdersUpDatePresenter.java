package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.OrdersUpDateListener;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserOrdersUpDateView;


/**
 * Created by LiLei on 2016/4/15.Go.
 * 个人业务回调接口,获取修改订单的状态
 */
public class UserOrdersUpDatePresenter {
    private IUserBiz userBiz;
    private IUserOrdersUpDateView userOrdersUpDateView;
    private Handler mHandler = new Handler();

    public UserOrdersUpDatePresenter(IUserOrdersUpDateView userOrdersUpDateView) {
        //设置view和业务层，在此调用业务层
        this.userOrdersUpDateView = userOrdersUpDateView;
        this.userBiz = new UserBiz();
    }

    public void OrdersUpDate() {
        userBiz.OrdersUpDate(userOrdersUpDateView.PutOrders(), new OrdersUpDateListener() {
            @Override
            public void OrdersUpDateSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userOrdersUpDateView.toMainActivity();
                    }
                });
            }

            @Override
            public void OrdersUpDateFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userOrdersUpDateView.showFailedError();
                    }
                });
            }

        });
    }

}
