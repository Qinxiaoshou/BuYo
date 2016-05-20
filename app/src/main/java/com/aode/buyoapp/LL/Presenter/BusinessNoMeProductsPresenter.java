package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BQueryNoMeProductListener;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessNoMeProductView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务回调接口,通知获取除了我的商品的其余商品情况的状态
 */
public class BusinessNoMeProductsPresenter {
    private IBusinessBiz businessBiz;
    private IBusinessNoMeProductView iBusinessNoMeProductView;
    private Handler mHandler = new Handler();

    public BusinessNoMeProductsPresenter(IBusinessNoMeProductView iBusinessNoMeProductView) {
        //设置view和业务层，在此调用业务层
        this.iBusinessNoMeProductView = iBusinessNoMeProductView;
        this.businessBiz = new BusinessBiz();
    }

    public void QueryAllProduct() {

        businessBiz.getNoMeProduct(new BQueryNoMeProductListener() {
            @Override
            public void getSuccess(final List<Cloth> cloths) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        iBusinessNoMeProductView.toMainActivity(cloths);
                    }
                });

            }

            @Override
            public void getFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iBusinessNoMeProductView.showFailedError();
                    }
                });
            }
        });

    }
}
