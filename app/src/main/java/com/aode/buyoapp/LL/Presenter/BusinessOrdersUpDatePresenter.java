package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BOrdersUpDateListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessOrdersUpDateView;


/**
 * Created by LiLei on 2016/4/15.Go.
 * 商家业务回调接口,获取修改订单的状态
 */
public class BusinessOrdersUpDatePresenter {
    private IBusinessBiz businessBiz;
    private IBusinessOrdersUpDateView businessOrdersUpDateView;
    private Handler mHandler = new Handler();

    public BusinessOrdersUpDatePresenter(IBusinessOrdersUpDateView businessOrdersUpDateView) {
        //设置view和业务层，在此调用业务层
        this.businessOrdersUpDateView = businessOrdersUpDateView;
        this.businessBiz = new BusinessBiz();
    }

    public void OrdersUpDate() {
        businessBiz.OrdersUpDate(businessOrdersUpDateView.PutOrders(), new BOrdersUpDateListener() {
            @Override
            public void BOrdersUpDateSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessOrdersUpDateView.toMainActivity();
                    }
                });
            }

            @Override
            public void BOrdersUpDateFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessOrdersUpDateView.showFailedError();
                    }
                });
            }

        });
    }

}
