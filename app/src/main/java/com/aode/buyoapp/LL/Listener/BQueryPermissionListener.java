package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.User;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知用户获取权限的状态
 */
public interface BQueryPermissionListener {
    //商品获得成功回调接口
    void getSuccess(List<User> users);
    //商品获得回调接口
    void getFailed();
}
