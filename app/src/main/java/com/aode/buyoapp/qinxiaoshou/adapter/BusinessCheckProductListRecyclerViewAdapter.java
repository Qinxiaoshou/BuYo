package com.aode.buyoapp.qinxiaoshou.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessUpdateProductMessagesActivity;

import java.util.List;


/**
 * 商家查看商品列表适配器
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessCheckProductListRecyclerViewAdapter extends RecyclerView.Adapter<BusinessCheckProductListRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Cloth> cloths;
    public BusinessCheckProductListRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BusinessCheckProductListRecyclerViewAdapter(Context mContext,List<Cloth> cloths) {
        this.mContext = mContext;
        this.cloths = cloths;
    }
    //列表页面的布局实现
    @Override
    public BusinessCheckProductListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_product_list_content, parent, false);
       return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final BusinessCheckProductListRecyclerViewAdapter.ViewHolder holder, int position) {
    final View view = holder.mView;
        view.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 5, 0); //上下移动
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        System.out.println("点击了商家产品条目，产生了跳转");
                        mContext.startActivity(new Intent(mContext, BusinessUpdateProductMessagesActivity.class));
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
