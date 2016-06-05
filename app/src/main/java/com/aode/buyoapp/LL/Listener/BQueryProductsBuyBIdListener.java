package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知商家获取商品的状态
 */
public interface BQueryProductsBuyBIdListener {
    //商品获得成功回调接口
    void getSuccess(List<Cloth> cloths);
    //商品获得回调接口
    void getFailed();
}
