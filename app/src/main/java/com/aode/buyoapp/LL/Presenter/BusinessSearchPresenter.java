package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BSearchListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessSearchView;

import java.util.List;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务回调接口,通知查询商家的状态
 */
public class BusinessSearchPresenter {
    private IBusinessBiz iBusinessBiz;
    private IBusinessSearchView businessSearchView;
    private Handler mHandler = new Handler();

    public BusinessSearchPresenter(IBusinessSearchView businessSearchView) {
        //设置view和业务层，在此调用业务层
        this.businessSearchView = businessSearchView;
        this.iBusinessBiz = new BusinessBiz();
    }

    public void Search() {
        iBusinessBiz.SearchBusiness(businessSearchView.getName(), new BSearchListener() {

            @Override
            public void bSearchSuccess(final List<Business> Business) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层businessSearchView接口方法
                        businessSearchView.toMainActivity(Business);
                    }
                });
            }

            @Override
            public void bSearchFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessSearchView.showFailedError();
                    }
                });
            }

            @Override
            public void bSearchNo() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        businessSearchView.showNo();
                    }
                });
            }

        });
    }


}
