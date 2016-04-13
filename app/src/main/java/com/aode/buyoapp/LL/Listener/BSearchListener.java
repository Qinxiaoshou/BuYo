package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Business;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知设置搜索的状态
 */
public interface BSearchListener {
    //商家搜索成功回调
    void bSearchSuccess(List<Business> Business);

    //商家搜索失败回调接口
    void bSearchFailed();

    //商家搜索没有回调接口
    void bSearchNo();
}
