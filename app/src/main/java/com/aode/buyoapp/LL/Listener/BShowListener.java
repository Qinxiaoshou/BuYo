package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Business;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知登录的状态
 */
public interface BShowListener {
    //获取资料成功
    void showSuccess(Business business);
    //获取资料失败
    void showFailed();
}
