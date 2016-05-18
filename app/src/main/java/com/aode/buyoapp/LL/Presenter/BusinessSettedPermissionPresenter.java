package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BBusinessFriendSettedPermissionListener;
import com.aode.buyoapp.LL.Listener.BQueryBusinessPermissionListener;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessPermissionView;
import com.aode.buyoapp.LL.view.IBusinessSettedPermissionView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/13.Go.
 * 商家业务回调接口,通知查看商家权限的状态
 */
public class BusinessSettedPermissionPresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessSettedPermissionView IBusinessSettedPermissionView;
    private Handler mHandler = new Handler();

    public BusinessSettedPermissionPresenter(IBusinessSettedPermissionView IBusinessSettedPermissionView) {
        //设置view和业务层，在此调用业务层
        this.IBusinessSettedPermissionView = IBusinessSettedPermissionView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void Permission() {
        iBusinessBiz.queryBusinessSettedPermissionCloth(IBusinessSettedPermissionView.getMeId(), IBusinessSettedPermissionView.getBusinessId(), new BBusinessFriendSettedPermissionListener() {
            @Override
            public void bFriendSettedBusinessPermissionSuccess(final List<Cloth> cloths) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        IBusinessSettedPermissionView.toMainActivityCheck(cloths);
                    }
                });
            }

            @Override
            public void bSettedClothsFailed() {
                IBusinessSettedPermissionView.findFailedError();
            }


            @Override
            public void bFriendBusinessToMeNo() {
                IBusinessSettedPermissionView.findNo();
            }
        });
    }


}
