package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.User;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务View-登录接口
 */
public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void toMainActivity(User user);//成功跳转Activity

    void showFailedError();//失败提示
}
