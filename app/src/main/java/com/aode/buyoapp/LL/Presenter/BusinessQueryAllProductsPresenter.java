package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BQueryProductListener;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessProductView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务回调接口,通知获取我的商品情况的状态
 */
public class BusinessQueryAllProductsPresenter {
    private IBusinessBiz businessBiz;
    private IBusinessProductView businessProductView;
    private Handler mHandler = new Handler();

    public BusinessQueryAllProductsPresenter(IBusinessProductView businessProductView) {
        //设置view和业务层，在此调用业务层
        this.businessProductView = businessProductView;
        this.businessBiz = new BusinessBiz();
    }

    public void QueryAllProduct() {

        businessBiz.getProduct(new BQueryProductListener() {
            @Override
            public void getSuccess(final List<Cloth> cloths) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        businessProductView.toMainActivity(cloths);
                    }
                });

            }

            @Override
            public void getFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessProductView.showFailedError();
                    }
                });
            }
        });

    }
}
