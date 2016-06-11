package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.R;

import java.util.List;

/**
 * Created by LiLei on 2016/4/10.Go.
 */
public class SearchBusinessAdapter extends RecyclerView.Adapter<SearchBusinessAdapter.MyViewHolder> {

    private Context context;
    private List<Business> businesses;

    public SearchBusinessAdapter(Context context, List<Business> businesses) {
        this.context = context;
        this.businesses = businesses;
    }


    //接口
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_search_business_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_search_title.setText("商家名：" + businesses.get(position).getName());

        holder.tv_search_address.setText("地址：" + businesses.get(position).getAddress());
        holder.tv_search_size.setText("销售量：" + businesses.get(position).getSales());
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("商家搜索的条目点击"+position);
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_search_title, tv_search_address, tv_search_size;

        public MyViewHolder(View view) {
            super(view);
            tv_search_title = (TextView) view.findViewById(R.id.tv_search_title);
            tv_search_address = (TextView) view.findViewById(R.id.tv_search_address);
            tv_search_size = (TextView) view.findViewById(R.id.tv_search_size);
        }
    }
}

