package com.aode.buyoapp.qinxiaoshou.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessOrderManageDataRecyclerViewAdapter;


/**
 * 商家管理用户订单fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BussinessOrderManageFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.info_details_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
         recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new BusinessOrderManageDataRecyclerViewAdapter(getActivity()));
    }
}
