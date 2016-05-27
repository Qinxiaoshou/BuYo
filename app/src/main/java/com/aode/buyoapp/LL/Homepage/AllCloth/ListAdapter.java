package com.aode.buyoapp.LL.Homepage.AllCloth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private Context context;
    private List<Cloth> cloths;

    public ListAdapter(Context context, List<Cloth> cloths) {
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
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListViewHolder viewHolder = new ListViewHolder(LayoutInflater.from(context).inflate(R.layout.homepage_clothlist_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {
       // holder.iv_itemIv.setImageResource(R.drawable.buliao2);
        new ImageLoader(cloths.get(position),holder.iv_itemIv).resume();
        holder.id_item_abstruct.setText(cloths.get(position).getTitle());
        holder.id_item_price.setText("$" + cloths.get(position).getPrice());

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
        ;
    }

    @Override
    public int getItemCount() {
        return cloths.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_itemIv;
        TextView id_item_abstruct;
        TextView id_item_price;

        public ListViewHolder(View view) {
            super(view);
            iv_itemIv = (ImageView) view.findViewById(R.id.iv_itemIv);
            id_item_abstruct = (TextView) view.findViewById(R.id.id_item_abstruct);
            id_item_price = (TextView) view.findViewById(R.id.id_item_price);
        }
    }
}