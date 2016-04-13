package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Business;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知获取我的友好商家的状态
 */
public interface BBusinessFriendListener {
    //商家友好商家成功回调
    void bFriendBusinessSuccess(List<Business> businesses);

    //商家友好商家失败回调接口
    void bFriendBusinessFailed();

    //没有友好商家
    void bFriendBusinessNo();
}
