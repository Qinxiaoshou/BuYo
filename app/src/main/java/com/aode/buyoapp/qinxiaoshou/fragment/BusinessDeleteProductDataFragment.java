package com.aode.buyoapp.qinxiaoshou.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.LL.Presenter.BusinessQueryAllProductsPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessDeleteProductDataRecyclerViewAdapter;

import java.util.List;


/**
 * 商家删除商品条目fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
@SuppressLint("ValidFragment")
public class BusinessDeleteProductDataFragment extends Fragment implements IBusinessProductView{
    private RecyclerView recyclerView;
    private View view;


    BusinessQueryAllProductsPresenter businessQueryAllProductsPresenter = new BusinessQueryAllProductsPresenter(this);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载数据
        view =inflater.inflate(R.layout.info_details_layout_2, container, false);
        businessQueryAllProductsPresenter.QueryAllProduct();
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void toMainActivity(List<Cloth> clothlist) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new BusinessDeleteProductDataRecyclerViewAdapter(getActivity(),clothlist));
    }

    @Override
    public void showFailedError() {

    }
}
