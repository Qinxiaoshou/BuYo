package com.aode.buyoapp.LL.Personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.BusinessFriendPagerActivity;
import com.aode.buyoapp.qinxiaoshou.BusinessManageConsumerOrderPagerActivity;
import com.aode.buyoapp.qinxiaoshou.BusinessProductManageSwitchActivity;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessAddNewProductActivity;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessManageConsumerOrderDetailActivity;


public class Business_Personal extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView iv_message, iv_publish, iv_order, iv_amity;
    private TextView tv_business_name;

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_business_personal, container, false);
        init();
        return view;
    }

    public void init() {
        iv_message = (ImageView) view.findViewById(R.id.iv_message);
        iv_publish = (ImageView) view.findViewById(R.id.iv_publish);
        iv_order = (ImageView) view.findViewById(R.id.iv_order);
        iv_amity = (ImageView) view.findViewById(R.id.iv_amity);
        iv_publish.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        iv_order.setOnClickListener(this);
        iv_amity.setOnClickListener(this);
        tv_business_name = (TextView) view.findViewById(R.id.tv_business_name);
        tv_business_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_publish:
                startActivity(new Intent(getActivity(), BusinessProductManageSwitchActivity.class));
                break;
            case R.id.iv_message:
                startActivity(new Intent(getActivity(), Business_Message.class));
                break;
            case R.id.iv_order:
                startActivity(new Intent(getActivity(), BusinessManageConsumerOrderPagerActivity.class));
                break;
            case R.id.tv_business_name:
                startActivity(new Intent(getActivity(), Business_Message.class));
                break;
            case R.id.iv_amity:
                startActivity(new Intent(getActivity(), BusinessFriendPagerActivity.class));
                break;
        }
    }
}