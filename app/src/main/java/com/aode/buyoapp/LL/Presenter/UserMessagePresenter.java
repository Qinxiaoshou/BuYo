package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.bean.User;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.Listener.ShowListener;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserMessageView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知登录的状态
 */
public class UserMessagePresenter {
    private IUserBiz iUserBiz;
    private IUserMessageView userMessageView;
    private Handler mHandler = new Handler();

    public UserMessagePresenter(IUserMessageView userMessageView) {
        //设置view和业务层，在此调用业务层
        this.userMessageView = userMessageView;
        this.iUserBiz = new UserBiz();
    }

    public void Show() {
        iUserBiz.show(userMessageView.getId(), new ShowListener() {
            @Override
            public void showSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        userMessageView.toMainActivity(user);
                    }
                });
            }

            @Override
            public void showFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userMessageView.showFailedError();
                    }
                });
            }
        });
    }


}
