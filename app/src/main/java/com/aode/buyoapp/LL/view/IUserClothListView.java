package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/5/18.Go.
 * 个人主页-布匹大全的布匹列表
 */
public interface IUserClothListView {

    String getType();        //布匹类型

    void toMainActivity(List<Cloth> cloths);//成功跳转Activity

    void showFailedError();//失败提示

    void showNone();//获取值为空
}
