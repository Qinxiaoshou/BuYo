package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Recruit;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知招聘详情信息的状态
 */
public interface RecruitsDetailListener {
    //获取招聘信息成功
    void getSuccess(Recruit recruits);
    //获取招聘信息失败
    void getFailed();
}
