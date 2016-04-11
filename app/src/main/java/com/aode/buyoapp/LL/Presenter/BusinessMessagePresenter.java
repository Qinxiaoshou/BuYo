package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.Listener.BShowListener;
import com.aode.buyoapp.LL.view.IBusinessMessageView;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务回调接口,通知登录的状态
 */
public class BusinessMessagePresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessMessageView businessMessageView;
    private Handler mHandler = new Handler();

    public BusinessMessagePresenter(IBusinessMessageView BusinessMessageView) {
        //设置view和业务层，在此调用业务层
        this.businessMessageView = BusinessMessageView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void Show() {
        iBusinessBiz.show(businessMessageView.getId(), new BShowListener() {
            @Override
            public void showSuccess(final Business business) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        businessMessageView.toMainActivity(business);
                    }
                });
            }

            @Override
            public void showFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessMessageView.showFailedError();
                    }
                });
            }
        });
    }


}
