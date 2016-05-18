package com.aode.buyoapp.qinxiaoshou.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessPermissionPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessPermissionView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessChooseBusinessAndPermissionActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 商家设置其他商家权限信息适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessSettingBusinessPerssionDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessSettingBusinessPerssionDataRecyclerViewAdapter.ViewHolder> implements IBusinessPermissionView {


    BusinessPermissionPresenter businessPermissionPresenter = new BusinessPermissionPresenter(this);
    private Context mContext;
    private List<Cloth> oldCloths;
    private List<Cloth> productIds = new ArrayList<Cloth>();
    private String bId;
    private RadioGroup rg_h_open_permission;
    Activity mMctivity;

    public BusinessSettingBusinessPerssionDataRecyclerViewAdapter(Activity activity, Context mContext, List<Cloth> cloths, String bId, RadioGroup rg_h_open_permission) {
         this.mMctivity = activity;
        this.mContext = mContext;
        this.oldCloths = cloths;
        this.bId = bId;
        this.rg_h_open_permission = rg_h_open_permission;
    }


    //列表页面的布局实现
    @Override
    public BusinessSettingBusinessPerssionDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_set_product_permisson_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BusinessSettingBusinessPerssionDataRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.iv_h_perssion_product.setImageResource(R.drawable.cheese_3);
        holder.title.setText(oldCloths.get(position).getTitle());
        holder.tv_price.setText("￥" + oldCloths.get(position).getPrice());
        holder.tv_stock.setText("库存:" + oldCloths.get(position).getStock());
        //给CheckBox设置事件监听
        holder.cb_permission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    Cloth cloth = new Cloth();
                    cloth.setId(oldCloths.get(position).getId());
                    productIds.add(cloth);
                } else {

                }
            }
        });


        rg_h_open_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("本商家：" + Home_business.business.getId() + "##目标商家:" + bId + "##id集合：" + productIds + "选择个数：" + productIds.size());
                businessPermissionPresenter.Permission();

            }
        });
    }

    @Override
    public int getItemCount() {
        return oldCloths.size();
    }

    @Override
    public String getMeId() {  //自己商家的名字
        return Home_business.business.getId();
    }

    @Override
    public String getBusinessId() { //对方商家的名字
        return bId;
    }

    @Override
    public List<Cloth> getCLOTHS() {
        return productIds;
    }

    @Override
    public void toMainActivity() {
        try {
            mMctivity.onBackPressed();
        }catch (Exception e){
            Toast.makeText(mContext, "设置权限成功", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void showFailedError() {
        Toast.makeText(mContext, "设置权限失败", Toast.LENGTH_SHORT).show();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        ImageView iv_h_perssion_product;
        TextView title;
        TextView tv_price;
        TextView tv_stock;
        CheckBox cb_permission;

        public ViewHolder(View view) {
            super(view);
            iv_h_perssion_product = (ImageView) view.findViewById(R.id.iv_h_perssion_product);
            title = (TextView) view.findViewById(R.id.iv_h_product_permission_title);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_stock = (TextView) view.findViewById(R.id.tv_stock);
            cb_permission = (CheckBox) view.findViewById(R.id.cb_permission);
            mView = view;
        }
    }
}
