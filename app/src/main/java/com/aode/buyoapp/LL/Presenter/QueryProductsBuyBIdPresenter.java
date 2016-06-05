package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BQueryProductListener;
import com.aode.buyoapp.LL.Listener.BQueryProductsBuyBIdListener;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessProductView;
import com.aode.buyoapp.LL.view.QueryProductsBuyBidView;

import java.util.List;


/**
 * Created by qinxiaoshou on 2016/6/5.Go.
 * 商家业务回调接口,通知获取我的商品情况的状态
 */
public class QueryProductsBuyBIdPresenter {
    private IBusinessBiz businessBiz;
    private QueryProductsBuyBidView queryProductsBuyBidView;
    private Handler mHandler = new Handler();

    public QueryProductsBuyBIdPresenter(QueryProductsBuyBidView queryProductsBuyBidView) {
        //设置view和业务层，在此调用业务层
        this.queryProductsBuyBidView = queryProductsBuyBidView;
        this.businessBiz = new BusinessBiz();
    }

    public void QueryProductsBuyBId() {

        businessBiz.QueryProductsBuyBId(queryProductsBuyBidView.getBId(),new BQueryProductsBuyBIdListener() {
            @Override
            public void getSuccess(final List<Cloth> cloths) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        queryProductsBuyBidView.toMainActivity(cloths);
                    }
                });

            }

            @Override
            public void getFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        queryProductsBuyBidView.showFailedError();
                    }
                });
            }
        });

    }
}
