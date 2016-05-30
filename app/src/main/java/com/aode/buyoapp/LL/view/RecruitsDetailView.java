package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Recruit;

/**
 * Created by LiLei on 2016/5/24.Go.
 * 招聘详情页面接口
 */
public interface RecruitsDetailView {

    Recruit getId();

    void toMainActivity(Recruit recruits);//成功跳转Activity

    void showFailedError();//失败提示
}
