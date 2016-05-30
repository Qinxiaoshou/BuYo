package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Recruit;

/**
 * Created by LiLei on 2016/5/27.Go.
 * 添加招聘接口
 */
public interface RecruitsAddView {

    Recruit getRecruit();

    void toMainActivity();//成功跳转Activity

    void showFailedError();//失败提示
}
