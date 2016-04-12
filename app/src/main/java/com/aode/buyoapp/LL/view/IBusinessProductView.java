package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家查询所有商品接口
 */
public interface IBusinessProductView {

    void toMainActivity(List<Cloth> clothlist);//成功跳转Activity

    void showFailedError();//失败提示
}
