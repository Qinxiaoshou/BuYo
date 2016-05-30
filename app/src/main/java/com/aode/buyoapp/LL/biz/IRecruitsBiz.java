package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.RecruitsAddListener;
import com.aode.buyoapp.LL.Listener.RecruitsDetailListener;
import com.aode.buyoapp.LL.Listener.RecruitsListener;
import com.aode.buyoapp.LL.bean.Recruit;

/**
 * Created by LiLei on 2016/5/24.Go.
 */
public interface IRecruitsBiz {
    //获取所有的招聘信息接口
    void recruitsList(RecruitsListener recruitsListener);

    //获取指定招聘信息的详情信息接口
    void recruitsDetail(Recruit id, RecruitsDetailListener recruitsDetailListener);

    //添加招聘信息的接口
    void recruitsAdd(Recruit recruit, RecruitsAddListener recruitsAddListener);
}
