package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知商家给他人设置权限的状态
 */
public interface BQueryBusinessPermissionListener {
    //商家设置权限成功回调
    void bQueryPermissionSuccess();

    //商家设置权限失败回调接口
    void bQueryPermissionFailed();
}
