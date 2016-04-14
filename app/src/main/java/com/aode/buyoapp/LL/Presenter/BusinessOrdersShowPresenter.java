package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BOrdersShowListener;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessOrdersShowView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/15.Go.
 * 商家业务回调接口,获取展示订单的状态
 */
public class BusinessOrdersShowPresenter {
    private IBusinessBiz businessBiz;
    private IBusinessOrdersShowView businessOrdersShowView;
    private Handler mHandler = new Handler();

    public BusinessOrdersShowPresenter(IBusinessOrdersShowView businessOrdersShowView) {
        //设置view和业务层，在此调用业务层
        this.businessOrdersShowView = businessOrdersShowView;
        this.businessBiz = new BusinessBiz();
    }

    public void ordersShow() {
        businessBiz.OrdersShow(businessOrdersShowView.PutId(), new BOrdersShowListener() {
            @Override
            public void BOrdersShowSuccess(final List<Orders> orderses) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessOrdersShowView.toMainActivity(orderses);
                    }
                });
            }

            @Override
            public void BOrdersShowFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessOrdersShowView.showFailedError();
                    }
                });
            }

        });
    }

}
