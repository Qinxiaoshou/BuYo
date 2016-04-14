package com.aode.buyoapp.qinxiaoshou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessFriendShowPresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.view.IBusinessFriendView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessChooseBusinessAndPermissionActivity;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessUpdateBusinessAndPermissionActivity;

import java.io.Serializable;
import java.util.List;


/**
 * 商家设置友好商家适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessSettingBusinessFriendRecyclerViewAdapter extends RecyclerView.Adapter<BusinessSettingBusinessFriendRecyclerViewAdapter.ViewHolder> implements  IBusinessFriendView{

    private Context mContext;
    private List<Business> businesses;
    private List<Business> businessesNew;
    private int position ;

    public BusinessSettingBusinessFriendRecyclerViewAdapter(Context mContext, List<Business> businesses) {
        this.mContext = mContext;
        this.businesses = businesses;

    }


    //列表页面的布局实现
    @Override
    public BusinessSettingBusinessFriendRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_search_otherbusiness__item_bean_list_content, parent, false);
        return new ViewHolder(view);
    }
    BusinessFriendShowPresenter  businessFriendShowPresenter = new BusinessFriendShowPresenter(this);
    @Override
    public void onBindViewHolder(final BusinessSettingBusinessFriendRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.tv_name.setText(businesses.get(position).getName());
        holder.tv_phone.setText("联系方式:" + businesses.get(position).getPhoneNumber());
        holder.tv_address.setText("地址：" + businesses.get(position).getAddress());

        this.position = position;
        holder.mView.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
            @Override
            public void onClick(View v) {

                businessFriendShowPresenter.getFriend();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (businesses == null) {
            return 0;
        } else {
            return businesses.size();
        }
    }

    @Override
    public String getId() {
        return Home_business.business.getId();
    }

    @Override
    public void toMainActivity(List<Business> businesses) {
        this.businessesNew = businesses;
        Intent intent = new Intent(mContext, BusinessUpdateBusinessAndPermissionActivity.class);
        //传该商家拥有本店商品权限的集合
        Bundle bundle = new Bundle();
        System.out.println("跳转的时候要带过去的数据："+businessesNew.get(position).getCloths());
        intent.putExtra("bId", businessesNew.get(position).getId());
        bundle.putSerializable("BUSINESSES", (Serializable) businessesNew.get(position).getCloths());
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void showNo() {

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tv_name;
        TextView tv_phone;
        TextView tv_address;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //商家名
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            //商家电话号码
            tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            //商家地址
            tv_address = (TextView) view.findViewById(R.id.tv_address);
        }
    }
}
