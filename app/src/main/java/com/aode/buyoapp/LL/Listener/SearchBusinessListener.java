package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Business;

import java.util.List;

/**
 * Created by LiLei on 2016/6/1.Go.
 * 通知搜索状态-商家结果
 */
public interface SearchBusinessListener {
    //获取资料成功
    void getSuccessBusiness(List<Business> businesses);

    //获取资料失败
    void getFailed();


}
