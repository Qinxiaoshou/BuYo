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
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessSettingBusinessFriendRecyclerViewAdapter;

import java.util.List;


/**
 * 商家设置友好商家fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessSettingBusinessFriendFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private List<Business> businesses;

    @SuppressLint("ValidFragment")
    public BusinessSettingBusinessFriendFragment(List<Business> businesses) {
         this.businesses = businesses;
    }
    public BusinessSettingBusinessFriendFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.business_manage_my_businessfriends_product_list_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new BusinessSettingBusinessFriendRecyclerViewAdapter(getActivity(),businesses));
    }

}
