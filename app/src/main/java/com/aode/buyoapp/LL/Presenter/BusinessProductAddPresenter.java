package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BAddProductListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessProductAddView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知增加商品情况的状态
 */
public class BusinessProductAddPresenter {
    private IBusinessBiz businessBiz;
    private IBusinessProductAddView businessProductAddView;
    private Handler mHandler = new Handler();

    public BusinessProductAddPresenter(IBusinessProductAddView businessProductAddView) {
        //设置view和业务层，在此调用业务层
        this.businessProductAddView = businessProductAddView;
        this.businessBiz = new BusinessBiz();
    }

    public void ProductAdd() {
        businessBiz.addProduct(businessProductAddView.getProduct(), new BAddProductListener() {
            @Override
            public void addSuccess() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        businessProductAddView.toMainActivity();
                    }
                });
            }

            @Override
            public void addFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessProductAddView.showFailedError();
                    }
                });
            }
        });
    }
}
