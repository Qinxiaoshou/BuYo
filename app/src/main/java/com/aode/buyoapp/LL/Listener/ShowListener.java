package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.User;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知个人获取资料的状态
 */
public interface ShowListener {
    //获取资料成功
    void showSuccess(User user);
    //获取资料失败
    void showFailed();
}
