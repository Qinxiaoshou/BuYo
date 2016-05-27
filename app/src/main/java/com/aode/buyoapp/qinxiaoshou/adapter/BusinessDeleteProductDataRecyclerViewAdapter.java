package com.aode.buyoapp.qinxiaoshou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.BusinessProductDeletePresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductDeleteView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.util.List;


/**
 * 商家删除商品条目适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessDeleteProductDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessDeleteProductDataRecyclerViewAdapter.ViewHolder> implements IBusinessProductDeleteView {

    private List<Cloth> cloths;
    private Context mContext;
    private Cloth cloth;

    public BusinessDeleteProductDataRecyclerViewAdapter(Context mContext, List<Cloth> cloths) {
        this.mContext = mContext;
        this.cloths = cloths;
    }

    BusinessProductDeletePresenter businessProductDeletePresenter = new BusinessProductDeletePresenter(this);

    //列表页面的布局实现
    @Override
    public BusinessDeleteProductDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_delete_product_content, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final BusinessDeleteProductDataRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.tv_title.setText(cloths.get(position).getTitle());
        holder.tv_price.setText(cloths.get(position).getPrice()+"￥");
        holder.tv_stock.setText("库存:"+cloths.get(position).getStock());
        new ImageLoader(cloths.get(position),holder.iv_product_image).resume();

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloth = cloths.get(position);

                businessProductDeletePresenter.ProductDelete();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cloths.size();
    }

    @Override
    public Cloth getProduct() {
        return cloth;
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(mContext, "删除成功了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(mContext, "删除失败了", Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        Button btn_delete;
        TextView tv_title;
        TextView tv_price;
        TextView tv_stock;
        ImageView iv_product_image;

        public ViewHolder(View view) {
            super(view);
            btn_delete = (Button) view.findViewById(R.id.btn_delete);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_stock = (TextView) view.findViewById(R.id.tv_stock);
            iv_product_image = (ImageView) view.findViewById(R.id.iv_product_image);
            mView = view;
        }
    }
}
