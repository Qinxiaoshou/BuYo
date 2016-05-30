package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Recruit;

import java.util.List;

/**
 * Created by LiLei on 2016/5/24.Go.
 * 招聘页面接口
 */
public interface RecruitsView {

    void toMainActivity(List<Recruit> recruits);//成功跳转Activity

    void showFailedError();//失败提示
}
