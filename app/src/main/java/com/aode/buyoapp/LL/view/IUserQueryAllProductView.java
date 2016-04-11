package com.aode.buyoapp.LL.view;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人查询所有商品接口
 */
public interface IUserQueryAllProductView {
    String getUserName();

    String getPassword();

    void toMainActivity();//成功跳转Activity

    void showFailedError();//失败提示
}
