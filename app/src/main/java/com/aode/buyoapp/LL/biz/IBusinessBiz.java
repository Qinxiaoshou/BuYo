package com.aode.buyoapp.LL.biz;

import com.aode.buyoapp.LL.Listener.BAddProductListener;
import com.aode.buyoapp.LL.Listener.BDeleteProductListener;
import com.aode.buyoapp.LL.Listener.BBusinessFriendListener;
import com.aode.buyoapp.LL.Listener.BFriendBusinessChangeListener;
import com.aode.buyoapp.LL.Listener.BLoginListener;
import com.aode.buyoapp.LL.Listener.BProductChangeListener;
import com.aode.buyoapp.LL.Listener.BQueryBusinessPermissionListener;
import com.aode.buyoapp.LL.Listener.BQueryProductListener;
import com.aode.buyoapp.LL.Listener.BRegisterListener;
import com.aode.buyoapp.LL.Listener.BSearchListener;
import com.aode.buyoapp.LL.Listener.BShowChangeListener;
import com.aode.buyoapp.LL.Listener.BShowListener;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;

import java.util.List;

/**
 * Created by LiLei on 2016/4/9.Go.
 * 商家业务类接口
 */
public interface IBusinessBiz {
    //登录接口
    void login(String loginName, String password, BLoginListener bLoginListener);

    //注册接口
    void register(String loginName, String name, String password, BRegisterListener bRegisterListener);

    //展示资料接口
    void show(String id, BShowListener BShowListener);

    //修改资料接口
    void change(Business business, BShowChangeListener bShowChangeListener);

    //增加我商家的商品
    void addProduct(Cloth cloth, BAddProductListener bAddProductListener);

    //删除我商家的商品
    void deleteProduct(Cloth cloth, BDeleteProductListener bDeleteProductListener);

    //获取我商家的商品
    void getProduct(BQueryProductListener bQueryProductListener);

    //修改商品资料
    void ProductChange(Cloth cloth, BProductChangeListener bProductChangeListener);

    //搜索商家
    void SearchBusiness(String name, BSearchListener bSearchListener);

    //设置友好商家
    void queryBusinessPermission(String bId, String fId, List<Cloth> cloths, BQueryBusinessPermissionListener bQueryBusinessPermissionListener);

    //查看友好商家
    void  queryFriendBusiness(String id,BBusinessFriendListener bBusinessFriendListener);

    //修改友好商家和哪些商品
    void changeFriendBusiness(String bId, String fId, List<Cloth> cloths, BFriendBusinessChangeListener bFriendBusinessChangeListener);
}
