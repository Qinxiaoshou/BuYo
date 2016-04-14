package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.OrdersAddListener;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserOrdersAddView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知下单的状态
 */
public class UserOrdersAddPresenter {
    private IUserBiz userBiz;
    private IUserOrdersAddView userOrdersAddView;
    private Handler mHandler = new Handler();

    public UserOrdersAddPresenter(IUserOrdersAddView userOrdersAddView) {
        //设置view和业务层，在此调用业务层
        this.userOrdersAddView = userOrdersAddView;
        this.userBiz = new UserBiz();
    }

    public void ordersAdd() {
        userBiz.OrdersAdd(userOrdersAddView.PutOrders(), new OrdersAddListener() {
            @Override
            public void OrdersAddSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userOrdersAddView.toMainActivity();
                    }
                });

            }

            @Override
            public void OrdersAddFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userOrdersAddView.showFailedError();
                    }
                });
            }
        });
    }


}
