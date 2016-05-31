package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.SearchBusinessListener;
import com.aode.buyoapp.LL.Listener.SearchClothListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.biz.ISearchBiz;
import com.aode.buyoapp.LL.biz.SearchBiz;
import com.aode.buyoapp.LL.view.SearchView;

import java.util.List;


/**
 * Created by LiLei on 2016/6/1.Go.
 * 个人业务回调接口,通知注册的状态
 */
public class SearchPresenter {
    private ISearchBiz searchBiz;
    private SearchView searchView;
    private Handler mHandler = new Handler();

    public SearchPresenter(SearchView searchView) {
        //设置view和业务层，在此调用业务层
        this.searchView = searchView;
        this.searchBiz = new SearchBiz();
    }

    public void Search() {
        if ("布匹".equals(searchView.getKey())) {
            searchBiz.SearchCloth(searchView.getKey(), searchView.getChooseTitle(), new SearchClothListener() {
                @Override
                public void getSuccessCloth(final List<Cloth> cloths) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //真正实现view层IUserLoginView接口方法
                            searchView.toMainClothActivity(cloths);
                        }
                    });
                }

                @Override
                public void getFailed() {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            searchView.showFailedError();
                        }


                    });
                }
            });
        } else {
            searchBiz.SearchBusiness(searchView.getKey(), searchView.getChooseTitle(), new SearchBusinessListener() {

                @Override
                public void getSuccessBusiness(final List<Business> businesses) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //真正实现view层IUserLoginView接口方法
                            searchView.toMainBusinessActivity(businesses);
                        }
                    });
                }

                @Override
                public void getFailed() {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            searchView.showFailedError();
                        }

                    });
                }
            });
        }
    }
}
