package com.aode.buyoapp.LL.Listener;


/**
 * Created by LiLei on 2016/4/9.Go.
 * 通知删除商品的状态
 */
public interface BDeleteProductListener {
    //商品删除成功
    void deleteSuccess();
    //商品删除失败回调接口
    void deleteFailed();
}
