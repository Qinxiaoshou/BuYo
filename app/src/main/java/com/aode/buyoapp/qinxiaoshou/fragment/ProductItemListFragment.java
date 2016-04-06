package com.aode.buyoapp.qinxiaoshou.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.ProductItemListRecyclerViewAdapter;


/**
 * 顾客商品列表
 */
public class ProductItemListFragment extends Fragment {
    private View myParentView;
    private RecyclerView myRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myParentView = inflater.inflate(R.layout.customer_product_list_layout, container, false);
        return myParentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置recyclerView中的内容
        myRecyclerView = (RecyclerView) myParentView.findViewById(R.id.user_product_data_recycler_view2);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(myRecyclerView.getContext()));
        myRecyclerView.setAdapter(new ProductItemListRecyclerViewAdapter(getActivity()));
    }
}
