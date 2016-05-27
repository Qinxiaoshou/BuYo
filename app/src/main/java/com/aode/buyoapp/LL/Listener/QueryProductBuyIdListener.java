package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by qinxiaoshou on 2016/5/25.Go.
 * 通知根据商品id查询商品对象信息的状态
 */
public interface QueryProductBuyIdListener {
    //商品获得成功回调接口
    void getSuccess(Cloth cloth);
    //商品获得回调接口
    void getFailed();
}
