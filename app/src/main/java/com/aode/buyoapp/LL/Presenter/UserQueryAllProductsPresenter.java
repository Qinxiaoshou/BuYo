package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.QueryProductListener;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserQueryAllProductView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知获取商品情况的状态
 */
public class UserQueryAllProductsPresenter {
    private IUserBiz userBiz;
    private IUserQueryAllProductView userQueryAllProductView;
    private Handler mHandler = new Handler();

    public UserQueryAllProductsPresenter(IUserQueryAllProductView userQueryAllProductView) {
        //设置view和业务层，在此调用业务层
        this.userQueryAllProductView = userQueryAllProductView;
        this.userBiz = new UserBiz();
    }

    public void QueryAllProduct() {

        userBiz.queryAllProduct(new QueryProductListener() {
            @Override
            public void loginSuccess(final List<Cloth> cloths) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        userQueryAllProductView.toMainActivity(cloths);
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userQueryAllProductView.showFailedError();
                    }
                });
            }
        });

    }
}
