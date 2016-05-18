package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.ClothCategory;

/**
 * Created by LiLei on 2016/5/17.Go.
 * 个人主页-布匹大全的布匹类型
 */
public interface IUserClothTypeView {

    void toMainActivity(ClothCategory clothCategories);//成功跳转Activity

    void showFailedError();//失败提示

    void showNone();//获取值为空
}
