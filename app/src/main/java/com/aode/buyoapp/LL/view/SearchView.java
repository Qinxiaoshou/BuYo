package com.aode.buyoapp.LL.view;


import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/6/1.Go.
 * 首页-搜索框接口
 */
public interface SearchView {
    String getKey();

    String getChooseTitle();

    void toMainClothActivity(List<Cloth> list);//成功跳转Activity

    void toMainBusinessActivity(List<Business> list);//成功跳转Activity

    void showFailedError();//失败提示
}
