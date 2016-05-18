package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.ClothListListener;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserClothListView;

import java.util.List;


/**
 * Created by LiLei on 2016/5/17.Go.
 * 个人业务回调接口,通知获取布匹大全中布匹类型的状态
 */
public class UserClothListPresenter {
    private IUserBiz userBiz;
    private IUserClothListView userClothListView;
    private Handler mHandler = new Handler();

    public UserClothListPresenter(IUserClothListView userClothListView) {
        //设置view和业务层，在此调用业务层
        this.userClothListView = userClothListView;
        this.userBiz = new UserBiz();
    }

    public void getClothList() {

        userBiz.getClothList(userClothListView.getType(), new ClothListListener() {
            @Override
            public void getClothListSuccess(final List<Cloth> cloths) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userClothListView.toMainActivity(cloths);
                    }
                });
            }

            @Override
            public void getClothListFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userClothListView.showFailedError();
                    }
                });
            }

            @Override
            public void getClothListNone() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userClothListView.showNone();
                    }
                });
            }

        });
    }
}
