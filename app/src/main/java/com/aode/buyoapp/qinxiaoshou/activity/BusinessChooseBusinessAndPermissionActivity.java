package com.aode.buyoapp.qinxiaoshou.activity;

import android.app.Activity;
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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessPermissionPresenter;
import com.aode.buyoapp.LL.Presenter.BusinessQueryAllProductsPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessPermissionView;
import com.aode.buyoapp.LL.view.IBusinessProductView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.adapter.BusinessSettingBusinessPerssionDataRecyclerViewAdapter;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessSettingBusinessPerssionFragment;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * 商家选择商品对于某个商家的权限开放
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */

public class BusinessChooseBusinessAndPermissionActivity extends FragmentActivity implements IBusinessProductView {

    BusinessQueryAllProductsPresenter businessQueryAllProductsPresenter = new BusinessQueryAllProductsPresenter(this);

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private Button toolbarRigntButton;
    private RadioGroup radioGroup;
    private RadioGroup rg_h_open_permission;
    String bId;
    List<Cloth> oldCloths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_search_other_business_layout);
        //或得需要设置权限商家的id
        Intent intent = getIntent();
        bId = intent.getStringExtra("bId");

        rg_h_open_permission = (RadioGroup) findViewById(R.id.rg_h_open_permission);

        toolbarTitle = (TextView) findViewById(R.id.tv_g_search_business_title);
        toolbarRigntButton = (Button) findViewById(R.id.btn_right_text);
        radioGroup = (RadioGroup) findViewById(R.id.rg_h_open_permission);

        toolbar = (Toolbar) findViewById(R.id.toolbar_business_search);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbarTitle.setText("选择开放权限的商品");
        toolbarTitle.setTextSize(20);
        toolbarRigntButton.setText("全选");
        toolbarRigntButton.setVisibility(View.GONE);
        radioGroup.setVisibility(View.VISIBLE);
        businessQueryAllProductsPresenter.QueryAllProduct();
    }

    @Override
    public void toMainActivity(List<Cloth> clothlist) {
        this.oldCloths = clothlist;


        RecyclerView view = (RecyclerView) findViewById(R.id.rv_search_business);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new BusinessSettingBusinessPerssionDataRecyclerViewAdapter(rg_h_open_permission));
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(), "查询商品失败，请检查网络设置", Toast.LENGTH_SHORT).show();
    }

    /**
     * 商家设置其他商家权限信息适配器
     *
     * @author 覃培周
     * @// FIXME: 2016/4/7
     */
    public class BusinessSettingBusinessPerssionDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessSettingBusinessPerssionDataRecyclerViewAdapter.ViewHolder> implements IBusinessPermissionView {


        BusinessPermissionPresenter businessPermissionPresenter = new BusinessPermissionPresenter(this);
        private List<Cloth> productIds = new ArrayList<Cloth>();
        private RadioGroup rg_h_open_permission;

        public BusinessSettingBusinessPerssionDataRecyclerViewAdapter(RadioGroup rg_h_open_permission) {
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
           new ImageLoader(oldCloths.get(position),holder.iv_h_perssion_product).resume();
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

            Toast.makeText(getApplicationContext(), "设置权限成功", Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onBackPressed();
        }

        @Override
        public void showFailedError() {
            Toast.makeText(getApplicationContext(), "设置权限失败", Toast.LENGTH_SHORT).show();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
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
