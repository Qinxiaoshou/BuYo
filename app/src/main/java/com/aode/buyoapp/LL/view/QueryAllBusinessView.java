package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Business;

import java.util.List;

/**
 * Created by qinxiaoshou on 2016/5/30.Go.
 * 厂商一览接口
 */
public interface QueryAllBusinessView {

    void QueryAllBusinessToMainActivity(List<Business> businesses);//成功跳转Activity

    void showQueryAllBusinessFailedError();//失败提示
}
