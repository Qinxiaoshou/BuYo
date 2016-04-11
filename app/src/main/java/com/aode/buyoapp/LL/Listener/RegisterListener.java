package com.aode.buyoapp.LL.Listener;

/**
 * Created by LiLei on 2016/4/10.Go.
 * 个人通知注册的状态
 */
public interface RegisterListener {
    //注册成功回调接口
    void registerSuccess();
    //注册失败回调接口
    void registerFailed();
}
