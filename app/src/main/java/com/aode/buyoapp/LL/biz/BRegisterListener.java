package com.aode.buyoapp.LL.biz;

/**
 * Created by LiLei on 2016/4/10.Go.
 * 商家通知注册的状态
 */
public interface BRegisterListener {
    //注册成功回调接口
    void bRegisterSuccess();
    //注册失败回调接口
    void bRegisterFailed();
}
