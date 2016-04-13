package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BQueryBusinessPermissionListener;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessPermissionView;


/**
 * Created by LiLei on 2016/4/13.Go.
 * 商家业务回调接口,通知设置权限的状态
 */
public class BusinessPermissionPresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessPermissionView businessPermissionView;
    private Handler mHandler = new Handler();

    public BusinessPermissionPresenter(IBusinessPermissionView businessPermissionView) {
        //设置view和业务层，在此调用业务层
        this.businessPermissionView = businessPermissionView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void Permission() {
        iBusinessBiz.queryBusinessPermission(businessPermissionView.getMeId(), businessPermissionView.getBusinessId(), businessPermissionView.getCLOTHS(), new BQueryBusinessPermissionListener() {

            @Override
            public void bQueryPermissionSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessPermissionView.toMainActivity();
                    }
                });
            }

            @Override
            public void bQueryPermissionFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessPermissionView.showFailedError();
                    }
                });
            }
        });
    }


}
