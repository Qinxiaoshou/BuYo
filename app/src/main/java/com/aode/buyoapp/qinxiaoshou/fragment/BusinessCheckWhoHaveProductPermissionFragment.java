package com.aode.buyoapp.qinxiaoshou.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessHavePermissonProductRecyclerViewAdapter;

import java.util.List;


/**
 * 商家查看本店已设置权限的商家fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
@SuppressLint("ValidFragment")
public class BusinessCheckWhoHaveProductPermissionFragment extends Fragment {
    private RecyclerView mRecyclerView;
    List<Business> toBList;
    public BusinessCheckWhoHaveProductPermissionFragment(List<Business> toBList) {
         this.toBList = toBList;
    }
    public BusinessCheckWhoHaveProductPermissionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.info_details_layout_2, container, false);
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new BusinessHavePermissonProductRecyclerViewAdapter(getActivity(),toBList));
    }
}
