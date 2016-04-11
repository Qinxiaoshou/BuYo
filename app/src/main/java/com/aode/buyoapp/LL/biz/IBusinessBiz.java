package com.aode.buyoapp.LL.biz;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务类接口
 */
public interface IBusinessBiz {
    //登录接口
    void login(String name, String password, BLoginListener bLoginListener);
    //注册接口
    void register(String name, String password, BRegisterListener bRegisterListener);
}
