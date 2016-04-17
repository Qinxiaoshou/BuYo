package com.aode.buyoapp.qinxiaoshou.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessSettingBusinessPerssionDataRecyclerViewAdapter;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessUpdateBusinessPerssionDataRecyclerViewAdapter;

import java.util.List;


/**
 * 商家设置其他商家拥有本店商品的权限fragment
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
@SuppressLint("ValidFragment")
public class BusinessUpdateBusinessPerssionFragment extends Fragment {
    private String bId;
    private RecyclerView recyclerView;
    private View view;
    private List<Cloth> cloths;   //本店商品集合
    private List<Cloth> clothsEd;//已经设置权限的商品集合
    private RadioGroup rg_h_open_permission;

    public BusinessUpdateBusinessPerssionFragment(List<Cloth> cloths, List<Cloth> clothsEd, RadioGroup rg_h_open_permission, String bId) {
        this.cloths = cloths;
        this.clothsEd = clothsEd;
        this.rg_h_open_permission = rg_h_open_permission;
        this.bId = bId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.info_details_layout_2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new BusinessUpdateBusinessPerssionDataRecyclerViewAdapter(getActivity(), cloths, clothsEd, rg_h_open_permission,bId));
    }
}
