package com.aode.buyoapp.LL.view;

import com.aode.buyoapp.LL.bean.Business;

/**
 * Created by LiLei on 2016/4/11.Go.
 * 获取商家自己个人信息的view接口
 */
public interface IBusinessMessageView {
    String getId();

    void toMainActivity(Business business);//成功显示数据

    void showFailedError();//失败提示
}
