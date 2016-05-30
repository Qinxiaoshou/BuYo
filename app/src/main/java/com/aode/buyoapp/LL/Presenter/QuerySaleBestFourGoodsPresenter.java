package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.QueryAllBusinessListener;
import com.aode.buyoapp.LL.Listener.QuerySaleBestFourGoodsListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.QueryAllBusinessView;
import com.aode.buyoapp.LL.view.UQuerySaleBestFourGoodsView;

import java.util.List;

/**
 * Created by qinxiaoshou on 2016/5/30.Go.
 * 用户界面热卖四个商品显示接口
 */
public class QuerySaleBestFourGoodsPresenter {
    private IUserBiz userBiz;
    private UQuerySaleBestFourGoodsView uQuerySaleBestFourGoodsView;
    private Handler mHandler = new Handler();

    public QuerySaleBestFourGoodsPresenter(UQuerySaleBestFourGoodsView uQuerySaleBestFourGoodsView) {
        //设置view和业务层，在此调用业务层
        this.uQuerySaleBestFourGoodsView = uQuerySaleBestFourGoodsView;
        this.userBiz = new UserBiz();
    }

    public void QuerySaleBestFourGoods() {

        userBiz.QuerySaleBestFourGoods(new QuerySaleBestFourGoodsListener() {
            @Override
            public void getSuccess(final List<Cloth> cloths) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        uQuerySaleBestFourGoodsView.QuerySaleBestFourGoodsToMainActivity(cloths);
                    }
                });

            }

            @Override
            public void getFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        uQuerySaleBestFourGoodsView.showQuerySaleBestFourGoodsFailedError();
                    }
                });
            }
        });

    }
}
