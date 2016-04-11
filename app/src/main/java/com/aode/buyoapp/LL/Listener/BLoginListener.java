package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Business;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知登录的状态
 */
public interface BLoginListener {
    //商家登录成功回调接口
    void bLoginSuccess(Business business);

    //登录失败回调接口
    void bLoginFailed();
}
