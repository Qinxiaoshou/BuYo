package com.aode.buyoapp.LL.view;

import com.aode.buyoapp.LL.bean.Business;

/**
 * Created by LiLei on 2016/4/11.Go.
 */
public interface IBusinessMessageView {
    String getId();

    void toMainActivity(Business business);//成功显示数据

    void showFailedError();//失败提示
}
