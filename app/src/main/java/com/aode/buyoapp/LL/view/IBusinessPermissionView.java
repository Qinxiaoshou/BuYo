package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务View-设置权限接口，需要属性，我的id，对方id，我的商品的id
 */
public interface IBusinessPermissionView {
    String getMeName();

    String getBusinessName();

    List<Cloth> getCLOTHS();

    void toMainActivity();//成功跳转Activity

    void showFailedError();//失败提示
}
