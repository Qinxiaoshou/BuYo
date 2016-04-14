package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Business;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知获取别人为我们开放的友好商家权限的状态
 */
public interface BBusinessFriendToMeListener {
    //商家友好商家成功回调
    void bFriendBusinessToMeSuccess(List<Business> businesses);

    //商家友好商家失败回调接口
    void bFriendBusinessToMeFailed();

    //没有友好商家
    void bFriendBusinessToMeNo();
}
