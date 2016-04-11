package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BRegisterListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessRegisterView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务回调接口,通知注册的状态
 */
public class BusinessRegisterPresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessRegisterView BusinessRegisterView;
    private Handler mHandler = new Handler();

    public BusinessRegisterPresenter(IBusinessRegisterView BusinessRegisterView) {
        //设置view和业务层，在此调用业务层
        this.BusinessRegisterView = BusinessRegisterView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void Register() {
        iBusinessBiz.register(BusinessRegisterView.getUserName(),BusinessRegisterView.getName(), BusinessRegisterView.getPassword(), new BRegisterListener() {
            @Override
            public void bRegisterSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        BusinessRegisterView.toMainActivity();
                    }
                });
            }

            @Override
            public void bRegisterFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        BusinessRegisterView.showFailedError();
                    }
                });
            }
        });

    }
}
