package com.aode.buyoapp.LL.biz;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务类接口
 */
public interface IUserBiz {
    //登录接口
    void login(String name, String password, LoginListener loginListener);
    //注册接口
    void register(String name,String password,RegisterListener registerListener);
}
