package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.RecruitsAddListener;
import com.aode.buyoapp.LL.biz.IRecruitsBiz;
import com.aode.buyoapp.LL.biz.RecruitsBiz;
import com.aode.buyoapp.LL.view.RecruitsAddView;


/**
 * Created by LiLei on 2016/5/27.Go.
 * 通知添加招聘信息的状态
 */
public class RecruitsAddPresenter {
    private IRecruitsBiz recruitsBiz;
    private RecruitsAddView recruitsAddView;
    private Handler mHandler = new Handler();

    public RecruitsAddPresenter(RecruitsAddView recruitsAddView) {
        //设置view和业务层，在此调用业务层
        this.recruitsAddView = recruitsAddView;
        this.recruitsBiz = new RecruitsBiz();
    }

    public void recruitsAdd() {
        recruitsBiz.recruitsAdd(recruitsAddView.getRecruit(), new RecruitsAddListener() {
            @Override
            public void getSuccess() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        recruitsAddView.toMainActivity();
                    }
                });
            }

            @Override
            public void getFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        recruitsAddView.showFailedError();
                    }
                });
            }

        });
    }
}
