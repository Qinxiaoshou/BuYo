package com.aode.buyoapp.LL.view;

import com.aode.buyoapp.LL.bean.User;

/**
 * Created by LiLei on 2016/4/11.Go.
 */
public interface IUserMessageView {
    String getId();

    void toMainActivity(User user);//成功显示数据

    void showFailedError();//失败提示
}
