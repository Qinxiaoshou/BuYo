package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.biz.BShowChangeListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessMessageChangeView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知登录的状态
 */
public class BusinessMessageChangePresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessMessageChangeView businessMessageChangeView;
    private Handler mHandler = new Handler();

    public BusinessMessageChangePresenter(IBusinessMessageChangeView businessMessageChangeView) {
        //设置view和业务层，在此调用业务层
        this.businessMessageChangeView = businessMessageChangeView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void change() {
        iBusinessBiz.change(businessMessageChangeView.getBusiness(), new BShowChangeListener() {
            @Override
            public void changeSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        businessMessageChangeView.toMainActivity();
                    }
                });
            }

            @Override
            public void changeFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessMessageChangeView.showFailedError();
                    }
                });
            }
        });
    }


}
