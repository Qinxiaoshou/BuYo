package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.bean.User;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.Listener.LoginListener;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserLoginView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知登录的状态
 */
public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        //设置view和业务层，在此调用业务层
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void Login() {
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new LoginListener() {
            //真正实现业务层中LoginListener接口的方法.
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        userLoginView.toMainActivity(user);
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                    }
                });

            }
        });
    }


}
