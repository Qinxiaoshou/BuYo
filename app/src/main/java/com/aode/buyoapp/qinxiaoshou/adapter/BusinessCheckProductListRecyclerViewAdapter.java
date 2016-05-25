package com.aode.buyoapp.qinxiaoshou.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.url;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessUpdateProductMessagesActivity;
import com.aode.buyoapp.qinxiaoshou.view.ImageLoader;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.LogRecord;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 商家查看商品列表适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessCheckProductListRecyclerViewAdapter extends RecyclerView.Adapter<BusinessCheckProductListRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    static List<Cloth> cloths;
    private ImageView picture;
    private Bitmap bitmap;

    public BusinessCheckProductListRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BusinessCheckProductListRecyclerViewAdapter(Context mContext, List<Cloth> cloths) {
        this.mContext = mContext;
        this.cloths = cloths;
    }

    //列表页面的布局实现
    @Override
    public BusinessCheckProductListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_product_list_content, parent, false);
        return new ViewHolder(view);
    }

    private com.aode.buyoapp.LL.url url = new url();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final BusinessCheckProductListRecyclerViewAdapter.ViewHolder holder, final int position) {
        //加载图片
        holder.setIsRecyclable(false); //recyclerviewd的positon不能复用
         new ImageLoader(cloths,position,holder.iv_pictue).resume();


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
                        Intent intent = new Intent(mContext, BusinessUpdateProductMessagesActivity.class);
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
        return cloths.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
