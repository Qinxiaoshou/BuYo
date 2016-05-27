package com.aode.buyoapp.qinxiaoshou.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.qinxiaoshou.activity.BusinessProductManageSwitchActivity;


/**
 *商家商品的管理fragment
 * @// FIXME: 2016/4/7
 */
@SuppressLint("ValidFragment")
public class BusinessProductListAndCRUDFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  null;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = new Intent(getContext(), BusinessProductManageSwitchActivity.class);
        startActivity(intent);
    }
}
