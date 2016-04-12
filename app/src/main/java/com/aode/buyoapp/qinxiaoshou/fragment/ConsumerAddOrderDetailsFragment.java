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
import android.widget.Button;
import android.widget.LinearLayout;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.ConsumerAddOrderDataRecyclerViewAdapter;
import com.aode.buyoapp.qinxiaoshou.adapter.ProductDataRecyclerViewAdapter;


/**
 * 用户添加订单详情fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ConsumerAddOrderDetailsFragment extends Fragment {
    private View mParentView;
    private RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.info_details_layout, container, false);
        return  mParentView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //类listview布局
        mRecyclerView = (RecyclerView) mParentView.findViewById(R.id.recycler_view2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mParentView.getContext()));
        mRecyclerView.setAdapter(new ConsumerAddOrderDataRecyclerViewAdapter(getActivity()));
    }
}
