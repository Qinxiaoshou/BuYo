package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BBusinessFriendToMeListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessFriendToMeView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/13.Go.
 * 商家业务回调接口,通知设获取友好商家的状态
 */
public class BusinessFriendToMeShowPresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessFriendToMeView businessFriendToMeView;
    private Handler mHandler = new Handler();

    public BusinessFriendToMeShowPresenter(IBusinessFriendToMeView businessFriendToMeView) {
        //设置view和业务层，在此调用业务层
        this.businessFriendToMeView = businessFriendToMeView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void getFriendToMe() {

        iBusinessBiz.getFriendBusinessToMe(businessFriendToMeView.getId(), new BBusinessFriendToMeListener() {


            @Override
            public void bFriendBusinessToMeSuccess(final List<Business> businesses) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessFriendToMeView.toMainActivity(businesses);
                    }
                });
            }

            @Override
            public void bFriendBusinessToMeFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessFriendToMeView.showFailedError();
                    }
                });
            }

            @Override
            public void bFriendBusinessToMeNo() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessFriendToMeView.showNo();
                    }
                });
            }
        });
    }
}
