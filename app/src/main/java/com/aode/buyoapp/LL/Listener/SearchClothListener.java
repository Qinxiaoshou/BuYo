package com.aode.buyoapp.LL.Listener;


import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/6/1.Go.
 * 通知搜索状态-布匹结果
 */
public interface SearchClothListener {
    //获取资料成功
    void getSuccessCloth(List<Cloth> cloths);

    //获取资料失败
    void getFailed();


}
