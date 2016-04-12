package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BDeleteProductListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessProductDeleteView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知获取商品情况的状态
 */
public class BusinessProductDeletePresenter {
    private IBusinessBiz businessBiz;
    private IBusinessProductDeleteView businessProductDeleteView;
    private Handler mHandler = new Handler();

    public BusinessProductDeletePresenter(IBusinessProductDeleteView businessProductDeleteView) {
        //设置view和业务层，在此调用业务层
        this.businessProductDeleteView = businessProductDeleteView;
        this.businessBiz = new BusinessBiz();
    }

    public void ProductDelete() {
        businessBiz.deleteProduct(businessProductDeleteView.getProduct(), new BDeleteProductListener() {
            @Override
            public void deleteSuccess() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        businessProductDeleteView.toMainActivity();
                    }
                });
            }

            @Override
            public void deleteFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessProductDeleteView.showFailedError();
                    }
                });
            }
        });
    }
}
