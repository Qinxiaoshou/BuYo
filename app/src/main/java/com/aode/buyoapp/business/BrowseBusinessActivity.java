package com.aode.buyoapp.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.QueryAllBusinessPresenter;
import com.aode.buyoapp.LL.Presenter.QueryProductsBuyBIdPresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.QueryAllBusinessView;
import com.aode.buyoapp.LL.view.QueryProductsBuyBidView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.SearchBusinessActivity;

import java.io.Serializable;
import java.util.List;

/**
 * 布约app厂商一览界面
 *
 * @author 陈映苗
 * @// FIXME: 2016/5/7
 */
public class BrowseBusinessActivity extends AppCompatActivity implements QueryAllBusinessView  {

    private ImageView iv_browse_business_back;
    private List<Business> businesses;
    QueryAllBusinessPresenter queryAllBusinessPresenter = new QueryAllBusinessPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_business);
        iv_browse_business_back = (ImageView) findViewById(R.id.iv_back);
        iv_browse_business_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        queryAllBusinessPresenter.QueryAllBusiness();

    }

    @Override
    public void QueryAllBusinessToMainActivity(List<Business> businesses) {
        System.out.println("查询商家一览成功");
        this.businesses = businesses;

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_browseBusiness);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new QueryAllBusinessRecyclerViewAdapter(this));

    }

    @Override
    public void showQueryAllBusinessFailedError() {
        System.out.println("查询商家一览失败");
        Toast.makeText(getApplicationContext(), "查询所有商家失败", Toast.LENGTH_SHORT).show();
    }




    /**
     * 商家一览适配器
     *
     * @author 覃培周
     * @// FIXME: 2016/5/30
     */
    public class QueryAllBusinessRecyclerViewAdapter extends RecyclerView.Adapter<QueryAllBusinessRecyclerViewAdapter.ViewHolder>implements QueryProductsBuyBidView {

        private Context mContext;
        private View view;


        public QueryAllBusinessRecyclerViewAdapter(Context mContext) {
            this.mContext = mContext;
        }
        private Business aimBusiness;
        QueryProductsBuyBIdPresenter queryProductsBuyBIdPresenter = new QueryProductsBuyBIdPresenter(this);

        //订单详情页面的布局实现
        @Override
        public QueryAllBusinessRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_browse_business_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final QueryAllBusinessRecyclerViewAdapter.ViewHolder holder, final int position) {

            try {
                holder.businessName.setText(businesses.get(position).getName());
                if(null==businesses.get(position).getDescription()){
                    holder.description.setText("[商家描述]  该商家还没有填写描述");
                }else{
                    holder.description.setText("[商家描述]" + businesses.get(position).getDescription());
                }

                if(null==businesses.get(position).getAddress()){
                    holder.tv_address_business.setText("厂商地址 ： 该商家还没有填写地址");
                }else{
                    holder.tv_address_business.setText("厂商地址 ： "+businesses.get(position).getAddress());
                }

                view.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
                    @Override
                    public void onClick(View v) {
                        aimBusiness = businesses.get(position);
                        queryProductsBuyBIdPresenter.QueryProductsBuyBId();
                       // finish();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override

        public int getItemCount() {
            return businesses.size();
        }

        /**
         * 根据商家id查询商品信息
         * @return
         */
        @Override
        public String getBId() {
            return aimBusiness.getId();
        }

        @Override
        public void toMainActivity(List<Cloth> clothlist) {
            System.out.println("商家的商品:"+clothlist);
            Intent intent = new Intent(mContext, SearchBusinessActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("business", aimBusiness);
            bundle.putSerializable("clothlist", (Serializable) clothlist);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }

        @Override
        public void showFailedError() {

        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            private final TextView businessName;
            private final TextView description;
            private final TextView tv_address_business;

            public ViewHolder(View view) {
                super(view);
                //商家名称
                businessName = (TextView) view.findViewById(R.id.textView4);
                //商店描述
                description = (TextView) view.findViewById(R.id.textView5);
                //店铺地址
                tv_address_business = (TextView) view.findViewById(R.id.tv_address_business);
                mView = view;
            }
        }
    }

}
