package com.aode.buyoapp.qinxiaoshou.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.url;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.util.List;

/**
 * 布约app搜索界面
 *
 * @author 覃培周
 * @// FIXME: 2016/5/7
 */
public class SearchBusinessActivity extends AppCompatActivity {

    public Business business;
    private ImageView iv_back;
    private TextView tv_search_business_name, tv_search_business_description, tv_search_business_phone, tv_search_business_license, tv_search_business_address, tv_search_business_sales;
    private List<Cloth> cloths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_business);

        //获取商品信息
        Intent intent = getIntent();
        business = (Business) intent.getSerializableExtra("business");
        cloths = (List<Cloth>) intent.getSerializableExtra("clothlist");

        init();
        set();
        back();
    }

    public void init() {
        tv_search_business_name = (TextView) findViewById(R.id.tv_search_business_name);
        tv_search_business_description = (TextView) findViewById(R.id.tv_search_business_description);
        tv_search_business_phone = (TextView) findViewById(R.id.tv_search_business_phone);
        tv_search_business_license = (TextView) findViewById(R.id.tv_search_business_license);
        tv_search_business_address = (TextView) findViewById(R.id.tv_search_business_address);
        tv_search_business_sales = (TextView) findViewById(R.id.tv_search_business_sales);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_store_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new QueryProductsBuyBIdRecyclerViewAdapter(this, cloths));
    }

    public void set() {
        if (business != null) {
            tv_search_business_name.setText("店铺名:" + business.getName());
            tv_search_business_description.setText("店铺描述:" + business.getDescription());
            tv_search_business_phone.setText("电话:" + business.getPhoneNumber());
            tv_search_business_license.setText("营业执照:" + business.getLicense());
            tv_search_business_address.setText("地址:" + business.getAddress());
            tv_search_business_sales.setText("销量:" + business.getSales());
        } else {
            Toast.makeText(this, "获取商家信息失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void back() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        //返回键事件
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 商家查看商品列表适配器
     *
     * @author 覃培周
     * @// FIXME: 2016/4/7
     */
    private class QueryProductsBuyBIdRecyclerViewAdapter extends RecyclerView.Adapter<QueryProductsBuyBIdRecyclerViewAdapter.ViewHolder> {

        private Context mContext;
        List<Cloth> cloths;
        private ImageView picture;
        private Bitmap bitmap;

        public QueryProductsBuyBIdRecyclerViewAdapter(Context mContext) {
            this.mContext = mContext;
        }

        public QueryProductsBuyBIdRecyclerViewAdapter(Context mContext, List<Cloth> cloths) {
            this.mContext = mContext;
            this.cloths = cloths;
        }

        //列表页面的布局实现
        @Override
        public QueryProductsBuyBIdRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_content, parent, false);
            return new ViewHolder(view);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(final QueryProductsBuyBIdRecyclerViewAdapter.ViewHolder holder, final int position) {
            //加载图片
            new ImageLoader(cloths, position, holder.iv_pictue).resume();
            holder.tv_title.setText(cloths.get(position).getTitle());
            holder.tv_price.setText("￥" + cloths.get(position).getPrice());
            holder.tv_stock.setText("库存:" + cloths.get(position).getStock());


            final View view = holder.mView;
            view.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
                @Override
                public void onClick(View v) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 5, 0); //上下移动
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            System.out.println("点击了商家产品条目，产生了跳转");
                            Intent intent = new Intent(mContext, ConsumerProductDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("cloth", cloths.get(position));
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);
                        }
                    });
                    animator.start();
                }
            });
        }

        @Override
        public int getItemCount() {
            if(cloths==null) {
                return  0;
            }else{
                return cloths.size();
            }

        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            ImageView iv_pictue;
            TextView tv_title;
            TextView tv_price;
            TextView tv_stock;

            public ViewHolder(View view) {
                super(view);
                iv_pictue = (ImageView) view.findViewById(R.id.iv_pictue);
                tv_title = (TextView) view.findViewById(R.id.tv_title);
                tv_price = (TextView) view.findViewById(R.id.tv_price);
                tv_stock = (TextView) view.findViewById(R.id.tv_stock);
                mView = view;
            }
        }
    }

}






