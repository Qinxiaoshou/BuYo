package com.aode.buyoapp.LL.view;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务View-注册接口
 */
public interface IBusinessRegisterView {
    String getUserName();
    String getName();
    String getPassword();

    void toMainActivity();//成功跳转Activity

    void showFailedError();//失败提示
}
