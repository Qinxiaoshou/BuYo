package com.aode.buyoapp.qinxiaoshou.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessFriendShowPresenter;
import com.aode.buyoapp.LL.Presenter.BusinessSettedPermissionPresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessFriendView;
import com.aode.buyoapp.LL.view.IBusinessSettedPermissionView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessUpdateBusinessAndPermissionActivity;

import java.io.Serializable;
import java.util.List;


/**
 * 商家设置友好商家适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessSettingBusinessFriendRecyclerViewAdapter extends RecyclerView.Adapter<BusinessSettingBusinessFriendRecyclerViewAdapter.ViewHolder> implements IBusinessSettedPermissionView {

    private Context mContext;
    private List<Business> businesses;
    private List<Cloth> cloths;
    private int position;
    private AaimId aaimId;

    private class AaimId {
        String getId;

        public String getGetId() {
            return getId;
        }

        public void setGetId(String getId) {
            this.getId = getId;
        }
    }


    BusinessSettedPermissionPresenter businessSettedPermissionPresenter = new BusinessSettedPermissionPresenter(this);

    public BusinessSettingBusinessFriendRecyclerViewAdapter(Context mContext, List<Business> businesses) {
        this.mContext = mContext;
        this.businesses = businesses;

    }


    //列表页面的布局实现
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_search_otherbusiness__item_bean_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (businesses != null) {
            holder.rl_business_item.setVisibility(View.VISIBLE);
            holder.tv_name.setText(businesses.get(position).getName());
            holder.tv_phone.setText("联系方式:" + businesses.get(position).getPhoneNumber());
            holder.tv_address.setText("地址：" + businesses.get(position).getAddress());
            this.position = position;

            holder.mView.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
                @Override
                public void onClick(View v) {
                    aaimId = new AaimId();
                    System.out.println("Position-->" + position);
                    aaimId.setGetId(businesses.get(position).getId());
                    businessSettedPermissionPresenter.Permission();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (businesses == null) {
            return 1;
        } else {
            return businesses.size();
        }
    }


    @Override
    public String getMeId() {
        return Home_business.business.getId();
    }

    @Override
    public String getBusinessId() {
        System.out.println("bId:" + aaimId.getGetId() + "  mId;" + Home_business.business.getId());
        return aaimId.getGetId();
    }

    @Override
    public void toMainActivity(List<Cloth> cloths) {
        System.out.println("!!!!!!!!!!!!!!!-->:" + cloths);
        this.cloths = cloths;
        Intent intent = new Intent(mContext, BusinessUpdateBusinessAndPermissionActivity.class);
        //传该商家拥有本店商品权限的集合
        Bundle bundle = new Bundle();
        intent.putExtra("bId", businesses.get(position).getId());
        bundle.putSerializable("BUSINESSES", (Serializable) cloths);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public void findFailedError() {
        System.out.println("查询商家拥有本店商品失败");
    }

    @Override
    public void findNo() {
        System.out.println("没有查询到商家拥有本店商品");
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tv_name;
        TextView tv_phone;
        TextView tv_address;
        RelativeLayout rl_business_item;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //条目布局
            rl_business_item = (RelativeLayout) view.findViewById(R.id.rl_business_item);
            //商家名
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            //商家电话号码
            tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            //商家地址
            tv_address = (TextView) view.findViewById(R.id.tv_address);
        }
    }
}
