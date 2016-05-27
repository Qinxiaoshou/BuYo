package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.User;

import java.util.List;

/**
 * Created by qinxiaoshou on 2016/5/27.Go.
 * 根据商品id查询商品信息
 */
public interface QueryProductBuyIdView {
    String getPId();  //传递id


    void toFindProductMainActivity(Cloth cloth);//成功跳转Activity

    void showFindProductFailedError();//失败提示
}
