package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Cloth;

import java.io.File;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 增加商品
 */
public interface IBusinessProductAddView {
    Cloth getProduct();//获取商品

   // File getPicture(); //获取图片

    void toMainActivity();//成功跳转Activity

    void showFailedError();//失败提示
}
