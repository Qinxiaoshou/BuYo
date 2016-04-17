package com.aode.buyoapp.qinxiaoshou.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessOrdersShowPresenter;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.view.IBusinessOrdersShowView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessOrderManageDataRecyclerViewAdapter;

import java.util.List;


/**
 * 商家管理用户订单fragment
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
@SuppressLint("ValidFragment")
public class BussinessOrderManageFragment extends Fragment implements IBusinessOrdersShowView {
    private RecyclerView recyclerView;
    private View view;

    BusinessOrdersShowPresenter businessOrdersShowPresenter = new BusinessOrdersShowPresenter(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.info_details_layout_2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        businessOrdersShowPresenter.ordersShow();
    }

    @Override
    public String PutId() {
        return Home_business.business.getId();
    }

    @Override
    public void toMainActivity(List<Orders> orderses) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new BusinessOrderManageDataRecyclerViewAdapter(getActivity(), orderses));

    }

    @Override
    public void showFailedError() {
        Toast.makeText(getActivity(), "订单获取失败,请检查网络和账号!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNo() {
        Toast.makeText(getActivity(), "没有订单!快去多做宣传吧!~~~", Toast.LENGTH_LONG).show();
    }
}
