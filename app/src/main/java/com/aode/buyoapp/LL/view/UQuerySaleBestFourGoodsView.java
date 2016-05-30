package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by qinxiaoshou on 2016/5/30.Go.
 * 用户界面热卖四个商品显示接口
 */
public interface UQuerySaleBestFourGoodsView {

    void QuerySaleBestFourGoodsToMainActivity(List<Cloth> cloths);//成功跳转Activity

    void showQuerySaleBestFourGoodsFailedError();//失败提示
}
