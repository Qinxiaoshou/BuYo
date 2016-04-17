package com.aode.buyoapp.qinxiaoshou.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.ConsumerOrderDetailsRecyclerViewAdapter;


/**
 * 店家管理顾客的订单的订单详情fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
@SuppressLint("ValidFragment")
public class BusinessManageConsumerOrderDetailsFragment extends Fragment {
    private View myParentView;
    private RecyclerView myRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myParentView = inflater.inflate(R.layout.business_manage_customer_order_details_pager_before_layout, container, false);
        return myParentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myRecyclerView = (RecyclerView) myParentView.findViewById(R.id.product_data_recycler_view2);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(myRecyclerView.getContext()));
        myRecyclerView.setAdapter(new ConsumerOrderDetailsRecyclerViewAdapter(getActivity()));
    }
}
