package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by qinxiaoshou on 2016/5/30.Go.
 * 用户界面热卖四个商品查询状态
 */
public interface QuerySaleBestFourGoodsListener {
    //商品获得成功回调接口
    void getSuccess(List<Cloth> cloths);
    //商品获得回调接口
    void getFailed();
}
