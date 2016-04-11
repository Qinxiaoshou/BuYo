package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Business;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务View-登录接口
 */
public interface IBusinessLoginView {
    String getBusinessName();

    String getPassword();

    void toMainActivity(Business business);//成功跳转Activity

    void showFailedError();//失败提示
}
