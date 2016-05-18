package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知添加商品的状态
 */
public interface BAddProductListener {
    //商品添加成功
    void addSuccess();

    //商品添加失败回调接口
    void addFailed();
}
