package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.ClothTypeListener;
import com.aode.buyoapp.LL.bean.ClothCategory;
import com.aode.buyoapp.LL.biz.IUserBiz;
import com.aode.buyoapp.LL.biz.UserBiz;
import com.aode.buyoapp.LL.view.IUserClothTypeView;


/**
 * Created by LiLei on 2016/5/17.Go.
 * 个人业务回调接口,通知获取布匹大全中布匹类型的状态
 */
public class UserClothTypePresenter {
    private IUserBiz userBiz;
    private IUserClothTypeView userClothTypeView;
    private Handler mHandler = new Handler();

    public UserClothTypePresenter(IUserClothTypeView userClothTypeView) {
        //设置view和业务层，在此调用业务层
        this.userClothTypeView = userClothTypeView;
        this.userBiz = new UserBiz();
    }

    public void getClothType() {

        userBiz.getClothType(new ClothTypeListener() {
            @Override
            public void getClothTypeSuccess(final ClothCategory clothCategory) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userClothTypeView.toMainActivity(clothCategory);
                    }
                });
            }

            @Override
            public void getClothTypeFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userClothTypeView.showFailedError();
                    }
                });

            }

            @Override
            public void getClothTypeNone() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userClothTypeView.showNone();
                    }
                });
            }
        });
    }
}
