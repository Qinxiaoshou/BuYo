package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BFriendBusinessChangeListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessFriendUpdateView;


/**
 * Created by LiLei on 2016/4/13.Go.
 * 商家业务回调接口,通知友好商家修改的状态
 */
public class BusinessFriendChangePresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessFriendUpdateView businessFriendUpdateView;
    private Handler mHandler = new Handler();

    public BusinessFriendChangePresenter(IBusinessFriendUpdateView businessFriendUpdateView) {
        //设置view和业务层，在此调用业务层
        this.businessFriendUpdateView = businessFriendUpdateView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void FriendChange() {
        iBusinessBiz.changeFriendBusiness(businessFriendUpdateView.getMeId(), businessFriendUpdateView.getBusinessId(), businessFriendUpdateView.getCLOTHS(), new BFriendBusinessChangeListener() {

            @Override
            public void bFriendBusinessChangeSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessFriendUpdateView.toMainActivity();
                    }
                });
            }

            @Override
            public void bFriendBusinessChangeFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessFriendUpdateView.showFailedError();
                    }
                });
            }
        });
    }


}
