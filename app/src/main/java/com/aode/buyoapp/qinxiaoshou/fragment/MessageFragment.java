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
    private Button btn_add;
    private Button btn_call_moreproduct_manager;


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
        btn_add = (Button) view.findViewById(R.id.btn_add); //添加商品
        btn_call_moreproduct_manager = (Button) view.findViewById(R.id.btn_call_moreproduct_manager); //管理商品
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "添加商品界面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BusinessAddNewProductActivity.class);
                startActivity(intent);

            }
        });
        btn_call_moreproduct_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "管理商品界面", Toast.LENGTH_SHORT).show();
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
