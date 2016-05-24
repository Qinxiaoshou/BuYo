package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BLoginOutListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessLoginOutView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务回调接口,通知注销的状态
 */
public class BusinessLoginOutPresenter {
    private IBusinessBiz businessBiz;
    private IBusinessLoginOutView businessLoginOutView;
    private Handler mHandler = new Handler();

    public BusinessLoginOutPresenter(IBusinessLoginOutView businessLoginOutView) {
        //设置view和业务层，在此调用业务层
        this.businessLoginOutView = businessLoginOutView;
        this.businessBiz = new BusinessBiz();
    }

    public void LoginOut() {
        businessBiz.loginOut(new BLoginOutListener() {
            //真正实现业务层中LoginOutListener接口的方法.
            @Override
            public void registerSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IBusinessLoginOutView接口方法
                        businessLoginOutView.toMainActivity();
                    }
                });
            }

            @Override
            public void registerFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessLoginOutView.showFailedError();
                    }
                });
            }
        });
    }
}
