package com.aode.buyoapp.LL.Homepage.AllCloth;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Item;
import com.aode.buyoapp.R;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ListViewHolder> {
    private Context context;
    private List<Item> items;

    public TypeAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
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
        ListViewHolder viewHolder = new ListViewHolder(LayoutInflater.from(context).inflate(R.layout.homepage_clothtype_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {

        if (!"花型".equals(items.get(position).getContent()) && !"底部".equals(items.get(position).getContent())) {
            holder.tv_list_item.setBackgroundColor(Color.argb(253, 233, 232, 232));
            holder.tv_list_item.setTextColor(Color.argb(255, 0, 0, 0));
            holder.tv_list_item.setTextSize(20);

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
        holder.tv_list_item.setText(items.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_list_item;

        public ListViewHolder(View view) {
            super(view);
            tv_list_item = (TextView) view.findViewById(R.id.tv_item_allCloth);
        }
    }
}