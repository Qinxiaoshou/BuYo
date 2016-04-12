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

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.ProductDataRecyclerViewAdapter;


/**
 * 用户查看商品详情fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ProductItemDetailsFragment extends Fragment {
    private View mParentView;
    private Toolbar toolbar2;
    private RecyclerView mRecyclerView;
    private Button btn_buy_now;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.customer_product_details_pager_layout, container, false);

        btn_buy_now = (Button) mParentView.findViewById(R.id.btn_buy_now);

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
