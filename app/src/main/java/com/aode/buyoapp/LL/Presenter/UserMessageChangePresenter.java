package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.Listener.ShowChangeListener;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserMessageChangeView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知个人信息修改的状态
 */
public class UserMessageChangePresenter {
    private IUserBiz iUserBiz;
    private IUserMessageChangeView userMessageChangeView;
    private Handler mHandler = new Handler();

    public UserMessageChangePresenter(IUserMessageChangeView userMessageChangeView) {
        //设置view和业务层，在此调用业务层
        this.userMessageChangeView = userMessageChangeView;
        this.iUserBiz = new UserBiz();
    }

    public void change() {
        iUserBiz.change(userMessageChangeView.getUser(), new ShowChangeListener() {
            @Override
            public void changeSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        userMessageChangeView.toMainActivity();
                    }
                });
            }

            @Override
            public void changeFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userMessageChangeView.showFailedError();
                    }
                });
            }
        });
    }


}
