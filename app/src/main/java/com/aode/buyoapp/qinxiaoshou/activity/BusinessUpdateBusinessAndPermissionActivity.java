package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessFriendChangePresenter;
import com.aode.buyoapp.LL.Presenter.BusinessQueryAllProductsPresenter;
import com.aode.buyoapp.LL.Presenter.BusinessSettedPermissionPresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessFriendUpdateView;
import com.aode.buyoapp.LL.view.IBusinessProductView;
import com.aode.buyoapp.LL.view.IBusinessSettedPermissionView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessUpdateBusinessPerssionDataRecyclerViewAdapter;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessUpdateBusinessPerssionFragment;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * 商家选择商品对于某个商家的权限开放
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */

public class BusinessUpdateBusinessAndPermissionActivity extends FragmentActivity implements IBusinessProductView,IBusinessSettedPermissionView {

    BusinessQueryAllProductsPresenter businessQueryAllProductsPresenter = new BusinessQueryAllProductsPresenter(this);
    BusinessSettedPermissionPresenter businessSettedPermissionPresenter = new BusinessSettedPermissionPresenter(this);

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private TextView tv_rg_name;
    private Button toolbarRigntButton;
    private RadioGroup radioGroup;
    private RadioGroup rg_h_open_permission;
    private  List<Cloth> cloths;
    private String bId; //被修改权限的商家id
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_update_other_business_permission_layout);
        //或得需要设置权限商家的id
        Intent intent  = getIntent();
        bId = intent.getStringExtra("bId");

        rg_h_open_permission = (RadioGroup) findViewById(R.id.rg_h_open_permission);

        toolbarTitle = (TextView) findViewById(R.id.tv_g_search_business_title);
        toolbarRigntButton = (Button) findViewById(R.id.btn_right_text);
        radioGroup = (RadioGroup) findViewById(R.id.rg_h_open_permission);
        tv_rg_name = (TextView) findViewById(R.id.tv_rg_name);

        toolbar = (Toolbar)findViewById(R.id.toolbar_business_permission_update);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });
        toolbarTitle.setText("选择修权限的商品");
        tv_rg_name.setText("修改权限");
        toolbarTitle.setTextSize(20);
        toolbarRigntButton.setText("全选");
        toolbarRigntButton.setVisibility(View.GONE);
        radioGroup.setVisibility(View.VISIBLE);
        //开始查询本店铺该店家拥有权限的商品
        businessSettedPermissionPresenter.Permission();
    }


    //查询本店铺该店家拥有权限的商品
    @Override
    public String getMeId() {
        return Home_business.business.getId();
    }

    @Override
    public String getBusinessId() {
        return bId;
    }

    @Override
    public void toMainActivityCheck(List<Cloth> cloths) {   //查询本店铺该店家拥有权限的商品成功
         this.cloths = cloths;
        businessQueryAllProductsPresenter.QueryAllProduct();
    }

    @Override
    public void toMainActivity(List<Cloth> clothlist) {  //查询本店商品成功
        recyclerView = (RecyclerView)findViewById(R.id.rv_permission_update);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BusinessUpdateBusinessPerssionDataRecyclerViewAdapter(getApplicationContext(), clothlist, cloths, rg_h_open_permission,bId));

    }

    @Override
    public void findFailedError() {
        Toast.makeText(getApplicationContext(),"查询该商家拥有权限的商品失败，请检查网络设置",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void findNo() {
        Toast.makeText(getApplicationContext(),"该商家没有本店权限，请前往设置",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(),"查询商品失败，请检查网络设置",Toast.LENGTH_SHORT).show();
    }


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
        private List<Cloth> productIds = new ArrayList<Cloth>();  //修改后的商品权限集合
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
            //如果该商品已设权限就回显
            for (Cloth cloth : clothsEd) {
                if ((cloths.get(position).getTitle()).equals(cloth.getTitle())) {
                    holder.cb_permission.setChecked(true);
                Cloth cloth1 = new Cloth();
                cloth1.setId(cloth.getId());
                productIds.add(cloth1);
                }
            }

            new ImageLoader(cloths.get(position),holder.iv_h_perssion_product).resume();
            holder.title.setText(cloths.get(position).getTitle());
            holder.tv_price.setText("￥" + cloths.get(position).getPrice());
            holder.tv_stock.setText("库存:" + cloths.get(position).getStock());
            holder.setIsRecyclable(false); //recyclerviewd的positon不能复用

            holder.cb_permission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!holder.cb_permission.isChecked()) {
                        holder.cb_permission.setChecked(false);
                        for (int i=0;i<productIds.size();i++) {
                            if (productIds.get(i).getId() == cloths.get(position).getId()) {
                                productIds.remove(productIds.get(i));
                                System.out.println("点击了取消" + productIds);
                            }
                        }
                    } else {
                        holder.cb_permission.setChecked(true);
                        Cloth cloth = new Cloth();
                        cloth.setId(cloths.get(position).getId());
                        productIds.add(cloth);
                        System.out.println("点击了选中" + productIds);
                    }
                }
            });


            rg_h_open_permission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("本商家：" + Home_business.business.getId() + "##目标商家:" + bId + "##id集合：" + productIds + "选择个数：" + productIds.size());
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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void showFailedError() {
            Toast.makeText(mContext, "修改权限失败", Toast.LENGTH_SHORT).show();
        }


        public  class ViewHolder extends RecyclerView.ViewHolder {
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

}
