package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
 * 友好商家界面
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessAddNewProductActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    //完成按钮
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_layout);
        //步骤一：添加一个FragmentTransaction的实例
        button = (Button) findViewById(R.id.btn_right_text);
        //步骤二：用add()方法加上Fragment的对象rightFragment
        toolbar = (Toolbar) findViewById(R.id.toolbar_business_product_details);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标

        recyclerView = (RecyclerView)findViewById(R.id.rv_product_details_view);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BusinessAddProductDataRecyclerViewAdapter(this,button));

    }

}
    /**
     * 商家添加商品信息的适配器
     *
     * @author 覃培周
     * @// FIXME: 2016/4/7
     */
    class BusinessAddProductDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessAddProductDataRecyclerViewAdapter.ViewHolder> implements IBusinessProductAddView {

        private Cloth cloth;
        private Context mContext;
        private Button button;

        public BusinessAddProductDataRecyclerViewAdapter(BusinessAddNewProductActivity businessAddNewProductActivity, Button button) {
            this.mContext = businessAddNewProductActivity;
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
            Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void showFailedError() {
            Toast.makeText(mContext, "添加失败", Toast.LENGTH_SHORT).show();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
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