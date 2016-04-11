package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.ChangePasswordListener;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserPasswordChangeView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知密码改变的状态
 */
public class UserPasswordChangePresenter {
    private IUserBiz iUserBiz;
    private IUserPasswordChangeView userPasswordChangeView;
    private Handler mHandler = new Handler();

    public UserPasswordChangePresenter(IUserPasswordChangeView userPasswordChangeView) {
        //设置view和业务层，在此调用业务层
        this.userPasswordChangeView = userPasswordChangeView;
        this.iUserBiz = new UserBiz();
    }

    public void change() {
        iUserBiz.changePassword(userPasswordChangeView.getId(), userPasswordChangeView.getOldPassword(), userPasswordChangeView.getNewPassword(), new ChangePasswordListener() {
            @Override
            public void changeSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        userPasswordChangeView.toMainActivity();
                    }
                });
            }

            @Override
            public void changeFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userPasswordChangeView.showFailedError();
                    }
                });
            }
        });
    }


}
