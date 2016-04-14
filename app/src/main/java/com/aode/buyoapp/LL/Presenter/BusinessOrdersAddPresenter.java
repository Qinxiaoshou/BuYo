package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BOrdersAddListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessOrdersAddView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务回调接口,通知下单的状态
 */
public class BusinessOrdersAddPresenter {
    private IBusinessBiz businessBiz;
    private IBusinessOrdersAddView businessOrdersAddView;
    private Handler mHandler = new Handler();

    public BusinessOrdersAddPresenter(IBusinessOrdersAddView businessOrdersAddView) {
        //设置view和业务层，在此调用业务层
        this.businessOrdersAddView = businessOrdersAddView;
        this.businessBiz = new BusinessBiz();
    }

    public void ordersAdd() {
        businessBiz.OrdersAdd(businessOrdersAddView.PutOrders(), new BOrdersAddListener() {
            @Override
            public void BOrdersAddSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessOrdersAddView.toMainActivity();
                    }
                });

            }

            @Override
            public void BOrdersAddFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessOrdersAddView.showFailedError();
                    }
                });
            }
        });
    }


}
