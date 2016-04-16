package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.User;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人查询所有商品接口
 */
public interface IUserQueryPermissionProductView {

    String getUId();  //传递id


    void toMainActivity(List<User> userList);//成功跳转Activity

    void showFailedError();//失败提示
}
