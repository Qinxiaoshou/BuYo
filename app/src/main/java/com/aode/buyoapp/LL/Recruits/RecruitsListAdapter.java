package com.aode.buyoapp.LL.Recruits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.LL.bean.Recruit;
import com.aode.buyoapp.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecruitsListAdapter extends RecyclerView.Adapter<RecruitsListAdapter.RecruitsListViewHolder> {
    private Context context;
    private List<Recruit> recruits;


    public RecruitsListAdapter(Context context, List<Recruit> recruits) {
        this.context = context;
        this.recruits = recruits;
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
    public RecruitsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecruitsListViewHolder viewHolder = new RecruitsListViewHolder(LayoutInflater.from(context).inflate(R.layout.recruits_message_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecruitsListViewHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (recruits.get(position).getDate() != null && "".equals(recruits.get(position).getDate())) {
            String dateString = formatter.format(recruits.get(position).getDate());
            holder.iv_recruits_time.setText(dateString);
        }
        holder.tv_recruits_workName.setText(recruits.get(position).getTitle());
        holder.iv_recruits_firm.setText(recruits.get(position).getIssuer());
        holder.iv_recruits_area.setText(recruits.get(position).getAddress());
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
        return recruits.size();
    }

    class RecruitsListViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_recruits_collect;
        TextView tv_recruits_workName;
        TextView iv_recruits_firm;
        TextView iv_recruits_area;
        TextView iv_recruits_money;
        ImageView iv_recruits_enter;
        TextView iv_recruits_time;

        public RecruitsListViewHolder(View view) {
            super(view);
            iv_recruits_collect = (ImageView) view.findViewById(R.id.iv_recruits_collect);
            tv_recruits_workName = (TextView) view.findViewById(R.id.tv_recruits_workName);
            iv_recruits_firm = (TextView) view.findViewById(R.id.iv_recruits_firm);
            iv_recruits_area = (TextView) view.findViewById(R.id.iv_recruits_area);
            iv_recruits_money = (TextView) view.findViewById(R.id.iv_recruits_money);
            iv_recruits_enter = (ImageView) view.findViewById(R.id.iv_recruits_enter);
            iv_recruits_time = (TextView) view.findViewById(R.id.iv_recruits_time);
        }
    }
}