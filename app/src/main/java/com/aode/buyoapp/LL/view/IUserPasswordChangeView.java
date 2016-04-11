package com.aode.buyoapp.LL.view;

/**
 * Created by LiLei on 2016/4/11.Go.
 */
public interface IUserPasswordChangeView {
    String getId();

    String getOldPassword();

    String getNewPassword();

    void toMainActivity();//成功提示

    void showFailedError();//失败提示
}
