package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.ClothCategory;

/**
 * Created by LiLei on 2016/5/17.Go.
 * 通知获取布匹类型的状态
 */
public interface ClothTypeListener {
    //布匹类型获取成功回调接口
    void getClothTypeSuccess(ClothCategory clothCategory);

    //布匹类型获取失败回调接口
    void getClothTypeFailed();

    //布匹类型获取成功但空回调接口
    void getClothTypeNone();
}
