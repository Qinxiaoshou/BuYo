package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BQueryPermissionListener;
import com.aode.buyoapp.LL.Listener.QueryProductListener;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.User;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserQueryAllProductView;
import com.aode.buyoapp.LL.view.IUserQueryPermissionProductView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知获取商品情况的状态
 */
public class UserQueryPermissionPresenter {
    private IUserBiz userBiz;
    private IUserQueryPermissionProductView userQueryPermissionProductView;
    private Handler mHandler = new Handler();

    public UserQueryPermissionPresenter(IUserQueryPermissionProductView userQueryPermissionProductView) {
        //设置view和业务层，在此调用业务层
        this.userQueryPermissionProductView = userQueryPermissionProductView;
        this.userBiz = new UserBiz();
    }

    public void QueryPermission() {

        userBiz.QuseryPermission(userQueryPermissionProductView.getUId(), new BQueryPermissionListener() {
            @Override
            public void getSuccess(final List<User> users) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层userQueryPermissionProductView接口方法
                        userQueryPermissionProductView.toMainActivity(users);
                    }
                });
            }

            @Override
            public void getFailed() {

                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层userQueryPermissionProductView接口方法
                        userQueryPermissionProductView.showFailedError();

                    }
                });
            }
        });

    }
}
