package com.aode.buyoapp.LL.view;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务View-搜索商家
 */
public interface IBusinessSearchView {
    String getName();

    void toMainActivity();//成功跳转Activity

    void showFailedError();//失败提示

    void showNo();//搜索不到的时候提示
}
