package com.aode.buyoapp.qinxiaoshou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;


/**
 * 商家添加商品信息的适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessAddProductDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessAddProductDataRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    public BusinessAddProductDataRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }


    //列表页面的布局实现
    @Override
    public BusinessAddProductDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_add_product_content, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final BusinessAddProductDataRecyclerViewAdapter.ViewHolder holder, int position) {
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
