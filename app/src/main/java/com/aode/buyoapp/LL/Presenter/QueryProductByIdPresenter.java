package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.BQueryProductListener;
import com.aode.buyoapp.LL.Listener.QueryProductBuyIdListener;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.biz.BusinessBiz;
import com.aode.buyoapp.LL.biz.IBusinessBiz;
import com.aode.buyoapp.LL.view.IBusinessProductView;
import com.aode.buyoapp.LL.view.QueryProductBuyIdView;

import java.util.List;


/**
 * Created by qinxiaoshou on 2016/5/25.Go.
 * 商家业务回调接口,通知获取我根据商品id查询商品对象信息情况的状态
 */
public class QueryProductByIdPresenter {
    private IBusinessBiz businessBiz;
    private QueryProductBuyIdView queryProductBuyIdView;
    private Handler mHandler = new Handler();

    public QueryProductByIdPresenter(QueryProductBuyIdView queryProductBuyIdView) {
        //设置view和业务层，在此调用业务层
        this.queryProductBuyIdView = queryProductBuyIdView;
        this.businessBiz = new BusinessBiz();
    }

    public void QueryProductBuyId() {

        businessBiz.getProductBuyId(queryProductBuyIdView.getPId(),new QueryProductBuyIdListener() {
            @Override
            public void getSuccess(final Cloth cloth) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        queryProductBuyIdView.toFindProductMainActivity(cloth);
                    }
                });

            }

            @Override
            public void getFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        queryProductBuyIdView.showFindProductFailedError();
                    }
                });
            }
        });

    }
}
