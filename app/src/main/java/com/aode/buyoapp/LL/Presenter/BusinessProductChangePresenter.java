package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BProductChangeListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessProductChangeView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务回调接口,通知获取商品情况的状态
 */
public class BusinessProductChangePresenter {
    private IBusinessBiz businessBiz;
    private IBusinessProductChangeView businessProductChangeView;
    private Handler mHandler = new Handler();

    public BusinessProductChangePresenter(IBusinessProductChangeView businessProductChangeView) {
        //设置view和业务层，在此调用业务层
        this.businessProductChangeView = businessProductChangeView;
        this.businessBiz = new BusinessBiz();
    }

    public void ProductChange() {
        businessBiz.ProductChange(businessProductChangeView.getProduct(),new BProductChangeListener() {
            @Override
            public void changeSuccess() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        businessProductChangeView.toMainActivity();
                    }
                });
            }

            @Override
            public void changeFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessProductChangeView.showFailedError();
                    }
                });
            }
        },businessProductChangeView.getPicture());
    }
}
