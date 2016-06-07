package com.aode.buyoapp.qinxiaoshou.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.ConsumerProductDetailsActivity;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.util.List;


/**
 * 店铺详情fragment
 * @author 覃培周
 * @// FIXME: 2016/6/7
 */
@SuppressLint("ValidFragment")
public class BusinessStoreMessageFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private List<Cloth> cloths;
    private TextView tv_search_business_name, tv_search_business_description, tv_search_business_phone, tv_search_business_license, tv_search_business_address, tv_search_business_sales;
    private Business business;
    @SuppressLint("ValidFragment")
    public BusinessStoreMessageFragment(List<Cloth> cloths,Business business) {
        this.cloths = cloths;
        this.business = business;
    }
    public BusinessStoreMessageFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_search_business_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_search_business_name = (TextView) view.findViewById(R.id.tv_search_business_name);
        tv_search_business_description = (TextView) view.findViewById(R.id.tv_search_business_description);
        tv_search_business_phone = (TextView) view.findViewById(R.id.tv_search_business_phone);
        tv_search_business_license = (TextView) view.findViewById(R.id.tv_search_business_license);
        tv_search_business_address = (TextView) view.findViewById(R.id.tv_search_business_address);
        tv_search_business_sales = (TextView) view.findViewById(R.id.tv_search_business_sales);
        tv_search_business_name.setText("店铺名:" + business.getName());
        tv_search_business_description.setText("店铺描述:" + business.getDescription());
        tv_search_business_phone.setText("电话:" + business.getPhoneNumber());
        tv_search_business_license.setText("营业执照:" + business.getLicense());
        tv_search_business_address.setText("地址:" + business.getAddress());
        tv_search_business_sales.setText("销量:" + business.getSales());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_store_products);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new QueryProductsBuyBIdRecyclerViewAdapter(getActivity(),cloths));
    }

}
/**
 * 商家查看商品列表适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
 class QueryProductsBuyBIdRecyclerViewAdapter extends RecyclerView.Adapter<QueryProductsBuyBIdRecyclerViewAdapter.ViewHolder> {

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