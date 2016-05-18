package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/5/18.Go.
 * 通知获取布匹列表的状态
 */
public interface ClothListListener {
    //布匹列表获取成功回调接口
    void getClothListSuccess(List<Cloth> cloths);

    //布匹列表获取失败回调接口
    void getClothListFailed();

    //布匹列表获取成功但空回调接口
    void getClothListNone();
}
