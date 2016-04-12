package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知登录的状态
 */
public interface BQueryProductListener {
    //商品获得成功回调接口
    void loginSuccess(List<Cloth> cloths);
    //商品获得回调接口
    void loginFailed();
}
