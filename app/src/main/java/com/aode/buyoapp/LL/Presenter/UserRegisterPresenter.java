package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.RegisterListener;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserRegisterView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知注册的状态
 */
public class UserRegisterPresenter {
    private IUserBiz userBiz;
    private IUserRegisterView registerView;
    private Handler mHandler = new Handler();

    public UserRegisterPresenter(IUserRegisterView registerView) {
        //设置view和业务层，在此调用业务层
        this.registerView = registerView;
        this.userBiz = new UserBiz();
    }

    public void Register() {
        userBiz.register(registerView.getUserName(), registerView.getPassword(), new RegisterListener() {
            //真正实现业务层中RegisterListener接口的方法.
            @Override
            public void registerSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        registerView.toMainActivity();
                    }
                });
            }
            @Override
            public void registerFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.showFailedError();
                    }
                });
            }
        });
    }
}
