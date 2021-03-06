package com.aode.buyoapp.qinxiaoshou.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.BusinessQueryAllProductsPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessAddNewProductActivity;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessMamageProductActivity;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessCheckProductListRecyclerViewAdapter;

import java.util.List;

/**
 * 出售中的商品界面fragment
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
@SuppressLint("ValidFragment")
public class MessageFragment extends Fragment implements IBusinessProductView {
    private View view;
    private TextView tv_add;
    private TextView tv_call_moreproduct_manager;


    BusinessQueryAllProductsPresenter businessQueryAllProductsPresenter = new BusinessQueryAllProductsPresenter(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.business_product_list_layout_and_recycleview, container, false);
        businessQueryAllProductsPresenter.QueryAllProduct();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_add = (TextView) view.findViewById(R.id.tv_add); //添加商品
        tv_call_moreproduct_manager = (TextView) view.findViewById(R.id.tv_call_moreproduct_manager); //管理商品
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BusinessAddNewProductActivity.class);
                startActivity(intent);

            }
        });
        tv_call_moreproduct_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BusinessMamageProductActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void toMainActivity(List<Cloth> clothlist) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_f_message);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new BusinessCheckProductListRecyclerViewAdapter(getActivity(), clothlist));
    }

    @Override
    public void showFailedError() {

    }
}
