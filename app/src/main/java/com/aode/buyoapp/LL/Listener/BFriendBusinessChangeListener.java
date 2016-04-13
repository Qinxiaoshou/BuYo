package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知修改友好商家的状态
 */
public interface BFriendBusinessChangeListener {
    //商家修改友好商家成功回调
    void bFriendBusinessChangeSuccess();

    //商家修改友好商家失败回调接口
    void bFriendBusinessChangeFailed();
}
