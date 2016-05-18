package com.aode.buyoapp.LL.bean;

import java.util.List;

/**
 * Created by 黎垒 on 2016/5,17.Go。
 */
public class For_ClothType {
    public static List<String> clothType_for(List<String> biglist,List<String> list){
        for(String str : list)
            biglist.add(str);
        return biglist;
    }
}
