package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.RecruitsListener;
import com.aode.buyoapp.LL.bean.Recruit;
import com.aode.buyoapp.LL.biz.IRecruitsBiz;
import com.aode.buyoapp.LL.biz.RecruitsBiz;
import com.aode.buyoapp.LL.view.RecruitsView;

import java.util.List;


/**
 * Created by LiLei on 2016/5/24.Go.
 * 通知获取招聘信息的状态
 */
public class RecruitsPresenter {
    private IRecruitsBiz recruitsBiz;
    private RecruitsView recruitsView;
    private Handler mHandler = new Handler();

    public RecruitsPresenter(RecruitsView recruitsView) {
        //设置view和业务层，在此调用业务层
        this.recruitsView = recruitsView;
        this.recruitsBiz = new RecruitsBiz();
    }

    public void recruitsList() {
        recruitsBiz.recruitsList(new RecruitsListener() {
            @Override
            public void getSuccess(final List<Recruit> recruits) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        recruitsView.toMainActivity(recruits);
                    }
                });
            }

            @Override
            public void getFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        recruitsView.showFailedError();
                    }
                });
            }
        });
    }
}
