package com.aode.buyoapp.qinxiaoshou.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.aode.buyoapp.qinxiaoshou.activity.BusinessChooseBusinessAndPermissionActivity;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessSettingBusinessPerssionDataRecyclerViewAdapter;

import java.util.List;


/**
 * 商家设置其他商家拥有本店商品的权限fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */

@SuppressLint("ValidFragment")
public class BusinessSettingBusinessPerssionFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView view;
    private List<Cloth> cloths;
    private String bId;
    private RadioGroup rg_h_open_permission;
    Activity activity = getActivity();

    public BusinessSettingBusinessPerssionFragment(List<Cloth> cloths, String bId, RadioGroup rg_h_open_permission) {
        this.cloths = cloths;
        this.bId  = bId;
        this.rg_h_open_permission = rg_h_open_permission ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.info_details_layout_2, container, false);
        return recyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = (RecyclerView) recyclerView.findViewById(R.id.recycler_view2);
        view.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        view.setAdapter(new BusinessSettingBusinessPerssionDataRecyclerViewAdapter(activity,getActivity(),cloths,bId,rg_h_open_permission));
    }
}
