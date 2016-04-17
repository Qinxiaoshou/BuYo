package com.aode.buyoapp.qinxiaoshou.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.BusinessProductChangePresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductChangeView;
import com.aode.buyoapp.R;


/**
 * 商家添加商品信息的适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessUpdateProductDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessUpdateProductDataRecyclerViewAdapter.ViewHolder> implements IBusinessProductChangeView {

    private Context mContext;
    private Cloth cloth;
    private Button button;
    private Cloth cloth2;

    public BusinessUpdateProductDataRecyclerViewAdapter(Context mContext, Cloth cloth, Button button) {
        this.mContext = mContext;
        this.cloth = cloth;
        this.button = button;
    }
    BusinessProductChangePresenter businessProductChangePresenter = new BusinessProductChangePresenter(this);

    //列表页面的布局实现
    @Override
    public BusinessUpdateProductDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_add_product_content, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final BusinessUpdateProductDataRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.et_title.setText(cloth.getTitle());
        holder.et_size.setText(cloth.getSize());
        holder.et_price.setText(cloth.getPrice() + "");
        holder.et_stock.setText(cloth.getStock() + "");
        holder.et_color.setText(cloth.getColor());
        holder.et_parttern.setText(cloth.getPattern());
        //完成按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloth2 = new Cloth();
                cloth2.setId(cloth.getId());
                cloth2.setTitle(holder.et_title.getText().toString().trim());
                cloth2.setSize(holder.et_size.getText().toString().trim());
                cloth2.setPrice(Double.valueOf(holder.et_price.getText().toString().trim()));
                cloth2.setStock(Long.valueOf(holder.et_stock.getText().toString().trim()));
                cloth2.setColor(holder.et_color.getText().toString().trim());
                cloth2.setPattern(holder.et_parttern.getText().toString().trim());
                cloth2.setbId(cloth.getbId());
                //Toast.makeText(mContext, "完成" + cloth, Toast.LENGTH_SHORT).show();

                businessProductChangePresenter.ProductChange();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public Cloth getProduct() {
        return cloth2;
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(mContext,"修改成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(mContext,"修改失败",Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        final EditText et_title;
        final EditText et_size;
        final EditText et_price;
        final EditText et_stock;
        final EditText et_color;
        final EditText et_parttern;

        public ViewHolder(View view) {
            super(view);
            et_title = (EditText) view.findViewById(R.id.et_title);
            et_size = (EditText) view.findViewById(R.id.et_size);
            et_price = (EditText) view.findViewById(R.id.et_price);
            et_stock = (EditText) view.findViewById(R.id.et_stock);
            et_color = (EditText) view.findViewById(R.id.et_color);
            et_parttern = (EditText) view.findViewById(R.id.et_parttern);
            mView = view;
        }
    }
}
