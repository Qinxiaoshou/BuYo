package com.aode.buyoapp.LL.Listener;

/**
 * Created by LiLei on 2016/5/24.Go.
 * 商家通知注销的状态
 */
public interface BLoginOutListener {
    //注销成功回调接口
    void registerSuccess();
    //注销失败回调接口
    void registerFailed();
}
