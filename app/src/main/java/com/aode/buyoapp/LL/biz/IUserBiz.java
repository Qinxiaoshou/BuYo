package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.ChangePasswordListener;
import com.aode.buyoapp.LL.Listener.LoginListener;
import com.aode.buyoapp.LL.Listener.QueryProductListener;
import com.aode.buyoapp.LL.Listener.RegisterListener;
import com.aode.buyoapp.LL.Listener.ShowChangeListener;
import com.aode.buyoapp.LL.Listener.ShowListener;
import com.aode.buyoapp.LL.bean.User;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 个人业务类接口
 */
public interface IUserBiz {
    //登录接口
    void login(String name, String password, LoginListener loginListener);

    //注册接口
    void register(String name, String password, RegisterListener registerListener);

    //展示资料接口
    void show(String id, ShowListener showListener);

    //修改资料接口
    void change(User user, ShowChangeListener showChangeListener);

    //修改密码接口
    void changePassword(String id, String oldPassword, String newPassword, ChangePasswordListener changePasswordListener);

    //获取个人的商品
    void queryAllProduct(QueryProductListener queryProductListener);
}
