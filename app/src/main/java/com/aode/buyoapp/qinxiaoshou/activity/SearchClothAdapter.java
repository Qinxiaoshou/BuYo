package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.LL.Homepage.Person_HomePage;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.util.List;

/**
 * Created by LiLei on 2016/4/10.Go.
 */
public class SearchClothAdapter extends RecyclerView.Adapter<SearchClothAdapter.MyViewHolder> {

    private Context context;
    private List<Cloth> cloths;

    public SearchClothAdapter(Context context, List<Cloth> cloths) {
        this.context = context;
        this.cloths = cloths;
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
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_search_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_search_title.setText("布匹名：" + cloths.get(position).getTitle());
        holder.tv_color.setText("颜色：" + cloths.get(position).getColor());
        holder.tv_search_size.setText("大小：" + cloths.get(position).getSize());
        holder.tv_price.setText("￥"+cloths.get(position).getPrice());
        //设置图片
        new ImageLoader(cloths.get(position),holder.iv_product);
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cloths.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_search_title, tv_color, tv_search_size,tv_price;
        ImageView iv_product ;
        public MyViewHolder(View view) {
            super(view);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            iv_product = (ImageView) view.findViewById(R.id.iv_product);
            tv_search_title = (TextView) view.findViewById(R.id.tv_product_title);
            tv_color = (TextView) view.findViewById(R.id.tv_color);
            tv_search_size = (TextView) view.findViewById(R.id.tv_size);
        }
    }
}

