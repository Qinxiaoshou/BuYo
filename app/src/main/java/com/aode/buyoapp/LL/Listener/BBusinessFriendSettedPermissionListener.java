package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知获取每个商家权限的状态
 */
public interface BBusinessFriendSettedPermissionListener {
    //商家友好商家拥有权限成功回调
    void bFriendSettedBusinessPermissionSuccess(List<Cloth> cloths);

    //商家友好商家商品权限查询失败回调接口
    void bSettedClothsFailed();

    //没有友好商家商品权限
    void bFriendBusinessToMeNo();
}
