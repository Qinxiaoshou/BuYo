package com.aode.buyoapp.qinxiaoshou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;

/**
 * 用户商品详情适配器
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ProductDataRecyclerViewAdapter extends RecyclerView.Adapter<ProductDataRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private Cloth cloth;
    private ImageView imageView;
    private TextView tv_product_data_title;
    private TextView tv_product_data_price;
    private TextView tv_company_title;
    private View view;

    public ProductDataRecyclerViewAdapter(Cloth cloth,Context mContext) {
        this.mContext = mContext;
        this.cloth = cloth;
    }




    //列表页面的布局实现
    @Override
    public ProductDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_product_details_pager_content, parent, false);
       return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ProductDataRecyclerViewAdapter.ViewHolder holder, int position) {
        System.out.println("-->"+position);
        view = holder.mView;
            //商品图片
            imageView = (ImageView) view.findViewById(R.id.iv_product);

            //商品标题
            tv_product_data_title = (TextView) view.findViewById(R.id.tv_product_data_title);
            //商品价格
            tv_product_data_price = (TextView) view.findViewById(R.id.tv_product_data_price);
            //商家名
            tv_company_title = (TextView) view.findViewById(R.id.tv_company_title);

            imageView.setImageResource(R.drawable.cheese_3);
            tv_product_data_title .setText(cloth.getTitle());
            tv_product_data_price.setText(cloth.getPrice()+"￥/米");
            tv_company_title.setText(cloth.getBusiness()+"");


    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
