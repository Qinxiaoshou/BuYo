package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.Presenter.QueryProductByIdPresenter;
import com.aode.buyoapp.LL.Presenter.UserOrdersShowPresenter;
import com.aode.buyoapp.LL.Presenter.UserOrdersUpDatePresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.view.IUserOrdersShowView;
import com.aode.buyoapp.LL.view.IUserOrdersUpDateView;
import com.aode.buyoapp.LL.view.QueryProductBuyIdView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.util.List;

/**
 * 用户订单列表activity
 * @// FIXME: 2016/4/7
 * @author 覃培周
 */
public class ConsumerOrderListActivity extends AppCompatActivity  implements IUserOrdersShowView{

    private TextView tv_g_add_product_title;
    private Button button;
    private Toolbar toolbar;
     UserOrdersShowPresenter userOrdersShowPresenter = new UserOrdersShowPresenter(this);

    private FragmentTransaction transaction;
    private RecyclerView mRecyclerView;
    Cloth clothP; //查询到的cloth
    private String CId ; //需要查询的商品的id


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_order_manager_layout);
        userOrdersShowPresenter.ordersShow();
        toolbar = (Toolbar)findViewById(R.id.toolbar_consumer_manager_order);
        tv_g_add_product_title = (TextView) findViewById(R.id.tv_toolbar_consumer_manager_order_title);
        button = (Button) findViewById(R.id.btn_right_text);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_g_add_product_title.setText("订单列表");
        button.setVisibility(View.GONE);
    }

    @Override
    public String PutId() {
        return Home_person.id;
    }

    @Override
    public void toMainActivity(List<Orders> orderses) {


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_consumer_manager_order);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(this,orderses));
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,"查询订单列表失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNo() {
        Toast.makeText(this,"购物车空空的,快去买点什么吧~",Toast.LENGTH_SHORT).show();
    }




    /**
     * 用户订单条目适配器
     *
     * @author 覃培周
     * @// FIXME: 2016/4/7
     */
    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements IUserOrdersUpDateView , QueryProductBuyIdView {
        private Context mContext;
        private List<Orders> orderses;
        private ImageView iv_pictue;
        private TextView tv_title;
        private TextView tv_price;
        private TextView tv_stock;
        private TextView tv_state;
        private Button btn_left;
        public Orders orders = new Orders(); //需要修改的订单对象
        QueryProductByIdPresenter queryProductByIdPresenter = new QueryProductByIdPresenter(this);
        UserOrdersUpDatePresenter userOrdersUpDatePresenter = new UserOrdersUpDatePresenter(this);

        public RecyclerViewAdapter(Context mContext, List<Orders> orderses) {
            this.mContext = mContext;
            this.orderses = orderses;
        }

        //根据商品id查询商品信息
        @Override
        public String getPId() {
            System.out.println("@@@@@@@@@@@@需要查询商品id"+CId);
            return CId;
        }

        @Override
        public void toFindProductMainActivity(Cloth cloth) {
            System.out.println("@#############查询到的商品对象"+cloth);
            new ImageLoader(clothP,iv_pictue).resume();
            clothP = cloth;
        }

        @Override
        public void showFindProductFailedError() {
            Toast.makeText(ConsumerOrderListActivity.this,"查询商品信息失败",Toast.LENGTH_SHORT).show();
        }
        //列表页面的布局实现
        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_order_details_pager_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, final int position) {

            holder.ll_i_product_list.removeView(holder.ll_product_content);//移除默认view
            holder.tv_store_name.setText("店铺:" + orderses.get(position).getBusiness().getName());
            holder.tv_state.setText(orderses.get(position).getState());
            //添加子view
            LinearLayout childLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.business_check_who_hava_permisson_product_item_content, null);
            //设置显示商品的商品条目详情
            iv_pictue = (ImageView) childLayout.findViewById(R.id.iv_pictue);
            tv_title = (TextView) childLayout.findViewById(R.id.tv_title);
            tv_price = (TextView) childLayout.findViewById(R.id.tv_price);
            tv_stock = (TextView) childLayout.findViewById(R.id.tv_stock);
            //查询该商品的信息
            try {
                CId = orderses.get(position).getcId()+"";
             //   queryProductByIdPresenter.QueryProductBuyId();
                //设置图片

            }catch (Exception e){
                System.out.println("商品id为空");
                e.printStackTrace();
            }
            //根据商品id查询商品信息

            tv_title.setText("订单描述:" + orderses.get(position).getDescription());
            tv_price.setText("￥" + orderses.get(position).getPrice());
            tv_stock.setText("购买长度:" + orderses.get(position).getLength() + "米");
            //在商铺条目中添加子商品条目
            holder.ll_i_product_list.addView(childLayout);

            //  holder.btn_right.setEnabled(false); //不可点击
            if ("已发货".equals(orderses.get(position).getState())) {
                holder.btn_left.setEnabled(true);
                holder.btn_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  更改订单状态
                        orders.setId(orderses.get(position).getId());
                        orders.setState("已收货");
                        userOrdersUpDatePresenter.OrdersUpDate();
                        btn_left = holder.btn_left;
                        tv_state = holder.tv_state;
                    }
                });
            } else if ("未发货".equals(orderses.get(position).getState())) {
                holder.btn_left.setText("待发货");
                holder.btn_left.setEnabled(false); //不可点击
            } else if ("已收货".equals(orderses.get(position).getState())) {
                holder.btn_left.setText("已收货");
                holder.btn_left.setEnabled(false);
            }
            final View view = holder.mView;
            view.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ConsumerOrderDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("order", orderses.get(position));
                    bundle.putSerializable("cloth",clothP);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            if (orderses == null) {
                return 0;
            } else {
                return orderses.size();
            }
        }

        @Override
        public Orders PutOrders() {
            System.out.println("提交用户修改后的订单:" + orders);
            return orders;
        }

        @Override
        public void toMainActivity() {
            tv_state.setText("已收货");
            btn_left.setText("已收货");
            btn_left.setEnabled(false);
        }

        @Override
        public void showFailedError() {
            Toast.makeText(mContext, "修改订单状态失败", Toast.LENGTH_SHORT).show();
        }


        public  class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public LinearLayout ll_i_product_list;
            public TextView tv_store_name;
            public LinearLayout ll_product_content;
            public TextView tv_state;
            public TextView tv_result;
            public Button btn_left;
            // public Button btn_right;

            public ViewHolder(View view) {
                super(view);
                //商铺动态添加条目的布局
                ll_i_product_list = (LinearLayout) view.findViewById(R.id.ll_1);
                //商铺名称
                tv_store_name = (TextView) view.findViewById(R.id.tv_store_name);
                //商铺动态添加的商品默认条目内容
                ll_product_content = (LinearLayout) view.findViewById(R.id.ll_product_content);
                //商品交易状态
                tv_state = (TextView) view.findViewById(R.id.tv_state);
                //商品订单结果
                tv_result = (TextView) view.findViewById(R.id.tv_result);
                //按钮左
                btn_left = (Button) view.findViewById(R.id.btn_left);
           /* //按钮右
            btn_right = (Button) view.findViewById(R.id.btn_right);*/
                mView = view;
            }
        }
    }

}
