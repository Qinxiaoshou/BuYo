package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;
/**
 * Created by qinxiaoshou on 2016/5/30.Go.
 * 厂商一览的获取商家状态
 */
public interface QueryAllBusinessListener {
    //商品获得成功回调接口
    void getSuccess(List<Business> businesses);
    //商品获得回调接口
    void getFailed();
}
