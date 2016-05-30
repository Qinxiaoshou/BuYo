package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Recruit;

import java.util.List;

/**
 * Created by LiLei on 2016/5/27.Go.
 * 通知招聘信息的状态
 */
public interface RecruitsListener {
    //获取招聘信息成功
    void getSuccess(List<Recruit> recruits);
    //获取招聘信息失败
    void getFailed();
}
