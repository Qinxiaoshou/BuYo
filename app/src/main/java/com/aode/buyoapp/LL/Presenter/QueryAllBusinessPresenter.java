package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.QueryAllBusinessListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.QueryAllBusinessView;

import java.util.List;


/**
 * Created by qinxiaoshou on 2016/5/30.Go.
 * 厂商一览回掉接口
 */
public class QueryAllBusinessPresenter {
    private IUserBiz userBiz;
    private QueryAllBusinessView queryAllBusinessView;
    private Handler mHandler = new Handler();

    public QueryAllBusinessPresenter(QueryAllBusinessView queryAllBusinessView) {
        //设置view和业务层，在此调用业务层
        this.queryAllBusinessView = queryAllBusinessView;
        this.userBiz = new UserBiz();
    }

    public void QueryAllBusiness() {

        userBiz.QueryAllBusiness(new QueryAllBusinessListener() {
            @Override
            public void getSuccess(final List<Business> businesses) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        queryAllBusinessView.QueryAllBusinessToMainActivity(businesses);
                    }
                });

            }

            @Override
            public void getFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        queryAllBusinessView.showQueryAllBusinessFailedError();
                    }
                });
            }
        });

    }
}
