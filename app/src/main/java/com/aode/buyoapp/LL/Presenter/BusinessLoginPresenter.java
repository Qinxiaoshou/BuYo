package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.Listener.BLoginListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessLoginView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务回调接口,通知登录的状态
 */
public class BusinessLoginPresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessLoginView businessLoginView;
    private Handler mHandler = new Handler();

    public BusinessLoginPresenter(IBusinessLoginView businessLoginView) {
        //设置view和业务层，在此调用业务层
        this.businessLoginView = businessLoginView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void Login() {
        iBusinessBiz.login(businessLoginView.getBusinessName(), businessLoginView.getPassword(), new BLoginListener() {

            @Override
            public void bLoginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessLoginView.showFailedError();
                    }
                });
            }

            @Override
            public void bLoginSuccess(final Business business) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        businessLoginView.toMainActivity(business);
                    }
                });
            }
        });
    }


}
