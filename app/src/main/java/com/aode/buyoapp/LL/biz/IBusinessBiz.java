package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.BLoginListener;
import com.aode.buyoapp.LL.Listener.BRegisterListener;
import com.aode.buyoapp.LL.Listener.BShowChangeListener;
import com.aode.buyoapp.LL.Listener.BShowListener;
import com.aode.buyoapp.LL.bean.Business;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务类接口
 */
public interface IBusinessBiz {
    //登录接口
    void login(String loginName, String password, BLoginListener bLoginListener);
    //注册接口
    void register(String loginName,String  name,String password, BRegisterListener bRegisterListener);
    //展示资料接口
    void show(String id,BShowListener BShowListener);
    //修改资料接口
    void change(Business business,BShowChangeListener bShowChangeListener);
}
