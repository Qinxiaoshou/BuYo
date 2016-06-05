package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 根据商家id查询所有该商家商品接口
 */
public interface QueryProductsBuyBidView {

      String getBId();   //获得商家id
    void toMainActivity(List<Cloth> clothlist);//成功跳转Activity

    void showFailedError();//失败提示
}
