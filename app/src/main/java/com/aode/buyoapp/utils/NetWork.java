package com.aode.buyoapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络连接
 * Created by 陈映苗 on 2016/4/7 10:05.
 */
public class NetWork {

    /**
     * 设置网络连接常量
     */
    public final static int NONE = 0;
    public final static int WIFI = 1;
    public final static int MOBILE = 2;

    /**
     * 获得网络连接的服务
     * @param context
     * @return  返回NONE
     */
    public static int getNetWorkState(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return MOBILE;
        }
        state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return WIFI;
        }
        return NONE;
    }
}
