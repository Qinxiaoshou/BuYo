package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知商家商品修改的状态
 */
public interface BProductChangeListener {
    //商品修改成功
    void changeSuccess();
    //商品获得回调接口
    void changeFailed();
}
