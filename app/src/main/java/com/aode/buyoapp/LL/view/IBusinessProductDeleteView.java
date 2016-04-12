package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Cloth;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 删除商品
 */
public interface IBusinessProductDeleteView {
    Cloth getProduct();//获取商品

    void toMainActivity();//成功跳转Activity

    void showFailedError();//失败提示
}
