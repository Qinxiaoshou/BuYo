package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知修改密码的状态
 */
public interface ChangePasswordListener {
    //修改密码成功
    void changeSuccess();
    //获取密码失败
    void changeFailed();
}
