package com.aode.buyoapp.qinxiaoshou.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;


/**
 * 用户商品详情activity
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessProductDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_product_details);
        //获取商品信息
        Intent intent = getIntent();
        Cloth cloth = (Cloth) intent.getSerializableExtra("cloth");

        // productItemDetailsFragment = new ProductItemDetailsFragment(cloth);
        toolbar = (Toolbar) findViewById(R.id.business_product_details);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_product_details_view_2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ProductDataRecyclerViewAdapter(cloth, this));

    }
}

class ProductDataRecyclerViewAdapter extends RecyclerView.Adapter<ProductDataRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private Cloth cloth;

    public ProductDataRecyclerViewAdapter(Cloth cloth, Context mContext) {
        this.mContext = mContext;
        this.cloth = cloth;
    }


    //列表页面的布局实现
    @Override
    public ProductDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_product_details_pager_content, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ProductDataRecyclerViewAdapter.ViewHolder holder, int position) {
        new ImageLoader(cloth,holder.iv_product).resume();
        holder.tv_product_data_title.setText(cloth.getTitle());
        holder.tv_product_data_price.setText(cloth.getPrice()+"/￥");
        if(cloth.getBusiness()==null){
            holder.tv_company_title.setText("");
            holder.tv_store_descrite.setText("");
        }else {
            holder.tv_company_title.setText(cloth.getBusiness().getName());
            if(cloth.getBusiness().getDescription()==null){
                holder.tv_store_descrite.setText("");
            }else{
                holder.tv_store_descrite.setText(cloth.getBusiness().getDescription());
            }
        }

        final TextView expandView = (TextView) holder.mView.findViewById(R.id.expand_view);
        final LinearLayout descriptionView = (LinearLayout) holder.mView.findViewById(R.id.description_layout);
        descriptionView.post(new Runnable() {

            @Override
            public void run() {
                expandView.setVisibility(View.VISIBLE);

            }
        });
        holder.mView.findViewById(R.id.expand_view).setOnClickListener(new View.OnClickListener() {
            boolean isExpand;

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                descriptionView.clearAnimation();
                int durationMillis = 350;
                if (isExpand) {
                    Animation animation = new Animation() {
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            //设置下拉图片方向
                            Drawable nav_up = mContext.getResources().getDrawable(R.drawable.ach_turn);
                            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                            expandView.setCompoundDrawables(null, null, nav_up, null);
                            //展示商品详情内容
                            descriptionView.setVisibility(View.VISIBLE);
                        }

                    };
                    animation.setDuration(durationMillis);
                    descriptionView.startAnimation(animation);

                } else {
                    Animation animation = new Animation() {
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            Drawable nav_up = mContext.getResources().getDrawable(R.drawable.ach);
                            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                            expandView.setCompoundDrawables(null, null, nav_up, null);
                            descriptionView.setVisibility(View.GONE);
                        }

                    };
                    animation.setDuration(durationMillis);
                    descriptionView.startAnimation(animation);

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private final ImageView iv_product;
        private final TextView tv_product_data_title;
        private final TextView tv_product_data_price;
        private final TextView tv_company_title;
        private final TextView tv_store_descrite;

        public ViewHolder(View view) {
            super(view);
            //商品图片
            iv_product = (ImageView) view.findViewById(R.id.iv_product);
            //商品标题
            tv_product_data_title = (TextView) view.findViewById(R.id.tv_product_data_title);
            //商品价格
            tv_product_data_price = (TextView) view.findViewById(R.id.tv_product_data_price);
            //店铺名称
            tv_company_title = (TextView) view.findViewById(R.id.tv_company_title);
            //店铺描述
            tv_store_descrite = (TextView) view.findViewById(R.id.tv_store_descrite);
            mView = view;
        }
    }
}
