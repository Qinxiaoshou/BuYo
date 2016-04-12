package com.aode.buyoapp.qinxiaoshou.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessUpdateProductDataRecyclerViewAdapter;


/**
 * 商家修改商品详情fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessUpdateProductDataFragment extends Fragment {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private View view;
    private Cloth cloth;
    private Button button;


    public BusinessUpdateProductDataFragment(Cloth cloth, Button button) {
        this.cloth = cloth;
        this.button = button;
    }

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
        recyclerView.setAdapter(new BusinessUpdateProductDataRecyclerViewAdapter(getActivity(),cloth,button));
    }
}
