package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务View-获取商家拥有权限接口，需要属性，我的id，对方id，我的商品的id
 */
public interface IBusinessSettedPermissionView {
    String getMeId();

    String getBusinessId();

    void toMainActivity(List<Cloth> cloths);//成功跳转Activity

    void findFailedError();//失败提示

    void findNo(); //没有
}
