package com.aode.buyoapp.qinxiaoshou.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.io.Serializable;
import java.util.List;


/**
 * 商家设置友好商家适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessSettingBusinessFriendRecyclerViewAdapter extends RecyclerView.Adapter<BusinessSettingBusinessFriendRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Business> businesses;
    private List<Cloth> cloths;
    private int position;




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
         //   new ImageLoader(businesses.get(position),holder.item_search_iv_icon).resume();
            this.position = position;

            holder.mView.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
                @Override
                public void onClick(View v) {
                    System.out.println("Position-->" + position+"bId:"+businesses.get(position).getId());
                    Intent intent = new Intent(mContext, BusinessUpdateBusinessAndPermissionActivity.class);
                    //传该商家拥有本店商品权限的集合
                    Bundle bundle = new Bundle();
                    intent.putExtra("bId", businesses.get(position).getId());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
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




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tv_name;
        TextView tv_phone;
        TextView tv_address;
        RelativeLayout rl_business_item;
        ImageView item_search_iv_icon;

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
            //图片
            item_search_iv_icon = (ImageView) view.findViewById(R.id.item_search_iv_icon);
        }
    }
}
