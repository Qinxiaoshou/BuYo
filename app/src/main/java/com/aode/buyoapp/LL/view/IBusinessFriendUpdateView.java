package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/4/13.Go.
 * 商家业务View-设置友好商家修改
 */
public interface IBusinessFriendUpdateView {
    String getMeId();

    String getBusinessId();

    List<Cloth> getCLOTHS();

    void toMainActivity();//成功跳转Activity

    void showFailedError();//失败提示
}
