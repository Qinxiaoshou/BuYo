package com.aode.buyoapp.qinxiaoshou.adapter;

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
import com.aode.buyoapp.LL.Presenter.BusinessFriendChangePresenter;
import com.aode.buyoapp.LL.Presenter.BusinessPermissionPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessFriendUpdateView;
import com.aode.buyoapp.LL.view.IBusinessPermissionView;
import com.aode.buyoapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 商家设置其他商家权限信息适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessUpdateBusinessPerssionDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessUpdateBusinessPerssionDataRecyclerViewAdapter.ViewHolder> implements IBusinessFriendUpdateView {


    private String bId;
    BusinessFriendChangePresenter businessFriendChangePresenter = new BusinessFriendChangePresenter(this);
    private Context mContext;
    private List<Cloth> cloths;   //本店商品集合
    private List<Cloth> clothsEd;//已经设置权限的商品集合
    private List<Cloth> productIds = new ArrayList<Cloth>();
    private RadioGroup rg_h_open_permission;

    public BusinessUpdateBusinessPerssionDataRecyclerViewAdapter(Context mContext, List<Cloth> cloths, List<Cloth> clothsEd, RadioGroup rg_h_open_permission, String bId) {
        this.mContext = mContext;
        this.cloths = cloths;
        this.clothsEd = clothsEd;
        this.rg_h_open_permission = rg_h_open_permission;
        this.bId = bId;
    }


    //列表页面的布局实现
    @Override
    public BusinessUpdateBusinessPerssionDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_set_product_permisson_content, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final BusinessUpdateBusinessPerssionDataRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.iv_h_perssion_product.setImageResource(R.drawable.cheese_3);
        holder.title.setText(cloths.get(position).getTitle());
        holder.tv_price.setText("￥" + cloths.get(position).getPrice());
        holder.tv_stock.setText("库存:" + cloths.get(position).getStock());
        holder.setIsRecyclable(false); //recyclerviewd的positon不能复用
        //如果该商品已设权限就回显
      /*  if(clothsEd.size()<=position){
            if(clothsEd.get(position).equals(cloths.get(position))) {
                holder.cb_permission.setChecked(true);
            }
        }*/
        for(Cloth cloth : clothsEd) {
            if ((cloths.get(position).getTitle()).equals(cloth.getTitle())) {
                holder.cb_permission.setChecked(true);
                productIds.add(cloth);
            }
        }






        //给CheckBox设置事件监听
     /*   holder.cb_permission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    Cloth cloth = new Cloth();
                    cloth.setId(cloths.get(position).getId());
                    productIds.add(cloth);
                } else {

                }
            }
        });*/

        View.OnClickListener ocl=new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!((CheckBox) v).isChecked()){
                    Toast.makeText(mContext, cloths.get(position)+"被取消", Toast.LENGTH_SHORT).show();
                   //productIds.remove(cloths.get(position).getbId());
                }
            }
        };

        CompoundButton.OnCheckedChangeListener occl=new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(mContext,cloths.get(position)+"被选中", Toast.LENGTH_SHORT).show();
                    Cloth cloth = new Cloth();
                    cloth.setId(cloths.get(position).getId());
                    productIds.add(cloth);
                }
            }
        };
        holder.cb_permission.setOnClickListener(ocl);
        holder.cb_permission.setOnCheckedChangeListener(occl);



        rg_h_open_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("本商家："+Home_business.business.getId()+"##目标商家:"+bId+"##id集合："+productIds+"选择个数："+productIds.size());
                businessFriendChangePresenter.FriendChange();

            }
        });
    }

    @Override
    public int getItemCount() {
        return cloths.size();
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
        Toast.makeText(mContext, "修改权限成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(mContext, "修改权限失败", Toast.LENGTH_SHORT).show();
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
