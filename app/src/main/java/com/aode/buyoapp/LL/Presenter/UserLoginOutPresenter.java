package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.LoginOutListener;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserLoginOutView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知注销的状态
 */
public class UserLoginOutPresenter {
    private IUserBiz userBiz;
    private IUserLoginOutView userLoginOutView;
    private Handler mHandler = new Handler();

    public UserLoginOutPresenter(IUserLoginOutView userLoginOutView) {
        //设置view和业务层，在此调用业务层
        this.userLoginOutView = userLoginOutView;
        this.userBiz = new UserBiz();
    }

    public void LoginOut() {
        userBiz.loginOut(new LoginOutListener() {
            //真正实现业务层中LoginOutListener接口的方法.
            @Override
            public void registerSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        userLoginOutView.toMainActivity();
                    }
                });
            }

            @Override
            public void registerFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginOutView.showFailedError();
                    }
                });
            }
        });
    }
}
