package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BBusinessFriendListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessFriendView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/13.Go.
 * 商家业务回调接口,通知设置权限的状态
 */
public class BusinessFriendQueryPresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessFriendView businessFriendView;
    private Handler mHandler = new Handler();

    public BusinessFriendQueryPresenter(IBusinessFriendView businessFriendView) {
        //设置view和业务层，在此调用业务层
        this.businessFriendView = businessFriendView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void Permission() {
        iBusinessBiz.queryFriendBusiness(businessFriendView.getId(), new BBusinessFriendListener() {
            @Override
            public void bFriendBusinessSuccess(final List<Business> businesses) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessFriendView.toMainActivity(businesses);
                    }
                });
            }

            @Override
            public void bFriendBusinessFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessFriendView.showFailedError();
                    }
                });
            }

            @Override
            public void bFriendBusinessNo() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessFriendView.showNo();
                    }
                });
            }
        });

    }
}
