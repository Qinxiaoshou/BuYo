package com.aode.buyoapp.LL.view;

import com.aode.buyoapp.LL.bean.Business;

/**
 * Created by LiLei on 2016/4/11.Go.
 */
public interface IBusinessMessageChangeView {
    Business getBusiness();

    void toMainActivity();//成功显示

    void showFailedError();//失败提示
}
