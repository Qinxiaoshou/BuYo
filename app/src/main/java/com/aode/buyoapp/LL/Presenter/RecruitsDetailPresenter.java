package com.aode.buyoapp.LL.Presenter;

import android.os.Handler;

import com.aode.buyoapp.LL.Listener.RecruitsDetailListener;
import com.aode.buyoapp.LL.bean.Recruit;
import com.aode.buyoapp.LL.biz.IRecruitsBiz;
import com.aode.buyoapp.LL.biz.RecruitsBiz;
import com.aode.buyoapp.LL.view.RecruitsDetailView;


/**
 * Created by LiLei on 2016/5/24.Go.
 * 通知获取招聘信息的状态
 */
public class RecruitsDetailPresenter {
    private IRecruitsBiz recruitsBiz;
    private RecruitsDetailView recruitsDetailView;
    private Handler mHandler = new Handler();

    public RecruitsDetailPresenter(RecruitsDetailView recruitsDetailView) {
        //设置view和业务层，在此调用业务层
        this.recruitsDetailView = recruitsDetailView;
        this.recruitsBiz = new RecruitsBiz();
    }

    public void recruitsDetail() {
        recruitsBiz.recruitsDetail(recruitsDetailView.getId(),new RecruitsDetailListener() {
            @Override
            public void getSuccess(final Recruit recruit) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //真正实现view层IUserLoginView接口方法
                        recruitsDetailView.toMainActivity(recruit);
                    }
                });
            }

            @Override
            public void getFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        recruitsDetailView.showFailedError();
                    }
                });
            }
        });
    }
}
