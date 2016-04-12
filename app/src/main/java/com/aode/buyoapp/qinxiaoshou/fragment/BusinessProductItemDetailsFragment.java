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
import com.aode.buyoapp.qinxiaoshou.adapter.ProductDataRecyclerViewAdapter;


/**
 * 用户查看商品详情fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessProductItemDetailsFragment extends Fragment {
    private View mParentView;
    private Toolbar toolbar2;
    private RecyclerView mRecyclerView;
    private Cloth cloth;

    //判断是否点击立即购买按钮
    boolean isClickBuy = false;

    //设置背景暗淡
    private LinearLayout bg_dim = null;



    //用户查看商品详情界面立即购买按钮
    private Button btn_buy_now;

    public BusinessProductItemDetailsFragment(Cloth cloth) {
        this.cloth = cloth;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.info_details_layout, container, false);

     /*   btn_buy_now = (Button) mParentView.findViewById(R.id.btn_buy_now);
        btn_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClickBuy = true;
                setBackgroundDim(bg_dim,1);
            }
        });*/
        return  mParentView;
    }

    /**
     * 控制背景变暗，1表示暗，2表示变亮
     * @author 陈映苗
     * @param v
     * @param id
     */
    public void setBackgroundDim(View v, int id){
        switch (id){
            case 1:
                v.setVisibility(View.GONE);
                break;
            case 2:
                v.setVisibility(View.VISIBLE);
                break;
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //类listview布局
        mRecyclerView = (RecyclerView) mParentView.findViewById(R.id.product_data_recycler_view);

        mRecyclerView = (RecyclerView) mParentView.findViewById(R.id.recycler_view2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mParentView.getContext()));
        mRecyclerView.setAdapter(new ProductDataRecyclerViewAdapter(cloth,getActivity()));
    }
}
