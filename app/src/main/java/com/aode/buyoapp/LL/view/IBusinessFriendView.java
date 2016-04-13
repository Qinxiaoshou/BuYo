package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Business;

import java.util.List;

/**
 * Created by LiLei on 2016/4/13.Go.
 * 商家业务View-设置友好商家显示
 */
public interface IBusinessFriendView {
    String getId();

    void toMainActivity(List<Business> businesses);//成功跳转Activity

    void showFailedError();//失败提示

    void showNo();//未查找到
}
