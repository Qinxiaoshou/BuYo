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
 * 商家设置其他商家权限信息适配器
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessSettingBusinessPerssionDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessSettingBusinessPerssionDataRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    public BusinessSettingBusinessPerssionDataRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }


    //列表页面的布局实现
    @Override
    public BusinessSettingBusinessPerssionDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_set_product_permisson_content, parent, false);
       return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final BusinessSettingBusinessPerssionDataRecyclerViewAdapter.ViewHolder holder, int position) {

     /*   final View view = holder.mView;
        view.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 5, 0); //上下移动
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        System.out.println("点击了条目，产生了跳转");
                        mContext.startActivity(new Intent(mContext, BusinessUpdateProductMessagesActivity.class));
                    }
                });
                animator.start();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
