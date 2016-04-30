package com.aode.buyoapp.qinxiaoshou.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.view.MoreTextView;


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
       /* System.out.println("-->" + position);
        holder.imageView.setImageResource(R.drawable.buliao3);
        holder.tv_product_data_title.setText(cloth.getTitle());
        holder.tv_product_data_price.setText(cloth.getPrice() + "￥/米");
        holder.tv_company_title.setText(cloth.getBusiness().getName());*/
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
      /*  public final ImageView imageView;
        public final TextView tv_product_data_title;
        public final TextView tv_product_data_price;
        public final TextView tv_company_title;*/

        public ViewHolder(View view) {
            super(view);
         /*   //商家名
            tv_company_title = (TextView) view.findViewById(R.id.tv_company_title);
            //商品图片
            imageView = (ImageView) view.findViewById(R.id.iv_product);
            //商品标题
            tv_product_data_title = (TextView) view.findViewById(R.id.tv_product_data_title);
            //商品价格
            tv_product_data_price = (TextView) view.findViewById(R.id.tv_product_data_price);*/
            mView = view;
        }
    }
}
