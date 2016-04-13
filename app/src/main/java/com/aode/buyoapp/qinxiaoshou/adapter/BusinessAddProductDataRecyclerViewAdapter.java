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

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessProductAddPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductAddView;
import com.aode.buyoapp.R;


/**
 * 商家添加商品信息的适配器
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessAddProductDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessAddProductDataRecyclerViewAdapter.ViewHolder> implements IBusinessProductAddView{

    private  Button button;
    private Context mContext;
    private Cloth cloth;

    public BusinessAddProductDataRecyclerViewAdapter(Context mContext, Button button) {
        this.mContext = mContext;
        this.button = button;
    }
    BusinessProductAddPresenter businessProductAddPresenter = new BusinessProductAddPresenter(this);

    //列表页面的布局实现
    @Override
    public BusinessAddProductDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_add_product_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BusinessAddProductDataRecyclerViewAdapter.ViewHolder holder, int position) {
//完成按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloth = new Cloth();
                cloth.setTitle(holder.et_title.getText().toString().trim());
                cloth.setSize(holder.et_size.getText().toString().trim());
                cloth.setPrice(Double.valueOf(holder.et_price.getText().toString().trim()));
                cloth.setStock(Long.valueOf(holder.et_stock.getText().toString().trim()));
                cloth.setColor(holder.et_color.getText().toString().trim());
                cloth.setPattern(holder.et_parttern.getText().toString().trim());
                cloth.setbId(Home_business.business.getId());
                Toast.makeText(mContext, "添加完成" + cloth, Toast.LENGTH_SHORT).show();

                businessProductAddPresenter.ProductAdd();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public Cloth getProduct() {
        return cloth;
    }

    /**
     * 出现不能添加商品问题
     */
    @Override
    public void toMainActivity() {
        Toast.makeText(mContext,"添加成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(mContext,"添加失败",Toast.LENGTH_SHORT).show();
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
