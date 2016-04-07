package com.aode.buyoapp.qinxiaoshou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.R;


/**
 * 列表条目信息的适配器
 */
public class ProductDataRecyclerViewAdapter extends RecyclerView.Adapter<ProductDataRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    public ProductDataRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
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
        final View view = holder.mView;
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
