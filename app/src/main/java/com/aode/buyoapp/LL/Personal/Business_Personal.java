package com.aode.buyoapp.LL.Personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Login_business;
import com.aode.buyoapp.LL.Presenter.BusinessLoginOutPresenter;
import com.aode.buyoapp.LL.view.IBusinessLoginOutView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessFriendPagerActivity;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessManageConsumerOrderPagerActivity;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessProductManageSwitchActivity;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessSearchOtherBusinessPagerActivity;


public class Business_Personal extends Fragment implements View.OnClickListener, IBusinessLoginOutView {
    private View view;
    private ImageView iv_message, iv_publish, iv_order, iv_amity, iv_permission;
    private TextView tv_business_name;
    private ImageButton btn_login_out;

    BusinessLoginOutPresenter businessLoginOutPresenter = new BusinessLoginOutPresenter(this);

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
        iv_permission = (ImageView) view.findViewById(R.id.iv_permission);
        iv_publish.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        iv_order.setOnClickListener(this);
        iv_amity.setOnClickListener(this);
        iv_permission.setOnClickListener(this);
        tv_business_name = (TextView) view.findViewById(R.id.tv_business_name);
        tv_business_name.setOnClickListener(this);
        btn_login_out = (ImageButton) view.findViewById(R.id.btn_login_out);
        btn_login_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_publish:
                //商品操作
                startActivity(new Intent(getActivity(), BusinessProductManageSwitchActivity.class));
                break;
            case R.id.iv_message:
                //商家信息
                startActivity(new Intent(getActivity(), Business_Message.class));
                break;
            case R.id.iv_order:
                //订单
                startActivity(new Intent(getActivity(), BusinessManageConsumerOrderPagerActivity.class));
                break;
            case R.id.tv_business_name:
                //商家信息
                startActivity(new Intent(getActivity(), Business_Message.class));
                break;
            case R.id.iv_amity:
                //合作商家展示
                startActivity(new Intent(getActivity(), BusinessFriendPagerActivity.class));
                break;
            case R.id.iv_permission:
                //设置合作商家
                startActivity(new Intent(getActivity(), BusinessSearchOtherBusinessPagerActivity.class));
                break;
            case R.id.iv_inventory:
                Toast.makeText(getActivity(), "此处还在施工", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login_out:
                businessLoginOutPresenter.LoginOut();
                break;
        }
    }

    @Override
    public void toMainActivity() {
        Home_business.business = null;
        Home_business.instance.finish();
        startActivity(new Intent(getActivity(), Login_business.class));

        Toast.makeText(getActivity(), "注销成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getActivity(), "注销失败", Toast.LENGTH_SHORT).show();
    }
}