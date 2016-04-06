package com.aode.buyoapp.qinxiaoshou.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.ProductDataRecyclerViewAdapter;


/**
 * 商品详情
 */
public class ProductItemDetailsFragment extends Fragment {
    private View mParentView;
    private Toolbar toolbar2;
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.customer_product_details_pager_layout, container, false);
        return  mParentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //类listview布局
        mRecyclerView = (RecyclerView) mParentView.findViewById(R.id.product_data_recycler_view);

        LinearLayoutManager manager = new LinearLayoutManager(mRecyclerView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new ProductDataRecyclerViewAdapter(getActivity()));
    }
}
