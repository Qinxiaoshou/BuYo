package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.SearchBusinessListener;
import com.aode.buyoapp.LL.Listener.SearchClothListener;

/**
 * Created by LiLei on 2016/6/1.Go.
 * 搜索业务类接口
 */
public interface ISearchBiz {
    void SearchCloth(String key, String title, SearchClothListener searchClothListener);

    void SearchBusiness(String key, String title, SearchBusinessListener searchBusinessListener);
}
