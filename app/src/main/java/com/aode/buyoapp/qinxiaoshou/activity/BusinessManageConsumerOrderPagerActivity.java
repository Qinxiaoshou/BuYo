package com.aode.buyoapp.qinxiaoshou.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessOrdersShowPresenter;
import com.aode.buyoapp.LL.Presenter.BusinessOrdersUpDatePresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Orders;
import com.aode.buyoapp.LL.view.IBusinessOrdersShowView;
import com.aode.buyoapp.LL.view.IBusinessOrdersUpDateView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 商家管理订单页面activity
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessManageConsumerOrderPagerActivity extends FragmentActivity implements IBusinessOrdersShowView {
    private Toolbar toolbar;
    private TextView tv_g_add_product_title;
    private Button button;
    BusinessOrdersShowPresenter businessOrdersShowPresenter = new BusinessOrdersShowPresenter(this);
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_manage_consumer_order_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar_business_manager_consumer_order_details);
        tv_g_add_product_title = (TextView) findViewById(R.id.tv_g_add_product_title);
        button = (Button) findViewById(R.id.btn_right_text);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_g_add_product_title.setText("商家订单管理");
        button.setVisibility(View.GONE);
        businessOrdersShowPresenter.ordersShow();

    }

    @Override
    public String PutId() {
        return Home_business.business.getId();
    }

    @Override
    public void toMainActivity(List<Orders> orderses) {
        recyclerView = (RecyclerView) findViewById(R.id.rv_manager_consumer_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BusinessOrderManageDataRecyclerViewAdapter(this, orderses));

    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "订单获取失败,请检查网络和账号!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNo() {
        Toast.makeText(this, "没有订单!快去多做宣传吧!~~~", Toast.LENGTH_LONG).show();
    }


    /**
     * 商家管理用户订单适配器
     *
     * @author 覃培周
     * @// FIXME: 2016/4/7
     */
    public class BusinessOrderManageDataRecyclerViewAdapter extends RecyclerView.Adapter<BusinessOrderManageDataRecyclerViewAdapter.ViewHolder> implements IBusinessOrdersUpDateView {

        private Context mContext;
        private List<Orders> orderses;
        private Orders orders = new Orders();    //需要修改的订单对象
        private Button btn_check;
        private TextView tv_state;

        BusinessOrdersUpDatePresenter businessOrdersUpDatePresenter = new BusinessOrdersUpDatePresenter(this);


        public BusinessOrderManageDataRecyclerViewAdapter(Context mContext, List<Orders> orderses) {
            this.mContext = mContext;
            this.orderses = orderses;
        }

        //列表页面的布局实现
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_manage_customer_order_ltem_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final View view = holder.mView;
            String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(orderses.get(position).getDate());
            holder.tv_buyer.setText("买家:" + orderses.get(position).getBuyer());
            holder.tv_state.setText(orderses.get(position).getState());
            holder.tv_title.setText("订单描述:" + orderses.get(position).getDescription());
            holder.tv_price.setText("初始价钱:" + String.valueOf(orderses.get(position).getPrice()));
            holder.tv_realprice.setText("实际价钱:" + String.valueOf(orderses.get(position).getPrice()));
            holder.tv_length.setText("布长:" + orderses.get(position).getLength() + "米");
            holder.tv_date.setText("下单日期:" + date);
            new ImageLoader(orderses.get(position).getCloth(),holder.iv_product_picture).resume();

            holder.itemView.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
                @Override
                public void onClick(View v) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 5, 0); //上下移动
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            //跳转到商家查看用户订单详情的界面
                            Intent intent = new Intent(mContext, BusinessManageConsumerOrderDetailActivity.class);
                            int pos = holder.getLayoutPosition();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("orders", orderses.get(pos));
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);

                        }
                    });
                    animator.start();
                }
            });
            //3个状态,未发货,已发货,已收货
            if ("未发货".equals(orderses.get(position).getState())) {
                holder.btn_check.setEnabled(true);
                holder.btn_check.setText("发货");
                //监听发货按钮状态改变
                holder.btn_check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //提交改变状态信息给服务器
                        orders.setId(orderses.get(position).getId());
                        orders.setState("已发货");
                        tv_state = holder.tv_state;
                        btn_check = holder.btn_check;
                        businessOrdersUpDatePresenter.OrdersUpDate();

                    }
                });

            } else if ("已收货".equals(orderses.get(position).getState())) {
                holder.btn_check.setEnabled(false);
                //  holder.btn_right.setEnabled(false);
                holder.btn_check.setText("已收货");
            } else if ("已发货".equals(orderses.get(position).getState())) {
                holder.btn_check.setEnabled(false);
                holder.btn_check.setText("已发货");
            }
        }

        @Override
        public int getItemCount() {
            return orderses.size();
        }

        @Override
        public Orders PutOrders() {
            System.out.println("提交商家修改后的订单:" + orders);
            return orders;//传进服务器的Orders的时间类型可能不对应
        }

        @Override
        public void toMainActivity() {
            tv_state.setText("已发货");
            btn_check.setText("已发货");
            btn_check.setEnabled(false); //不可点击

        }

        @Override
        public void showFailedError() {
            Toast.makeText(mContext, "管理订单失败", Toast.LENGTH_SHORT).show();
        }


        public  class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            TextView tv_buyer;
            TextView tv_state;
            TextView tv_title;
            TextView tv_price;
            TextView tv_realprice;
            TextView tv_length;
            TextView tv_date;
            Button btn_check;
            ImageView iv_product_picture;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                tv_buyer = (TextView) view.findViewById(R.id.tv_buyer);
                tv_state = (TextView) view.findViewById(R.id.tv_state);
                tv_title = (TextView) view.findViewById(R.id.tv_title);
                tv_price = (TextView) view.findViewById(R.id.tv_price);
                tv_realprice = (TextView) view.findViewById(R.id.tv_realprice);
                tv_length = (TextView) view.findViewById(R.id.tv_length);
                tv_date = (TextView) view.findViewById(R.id.tv_buy_date);
                btn_check = (Button) view.findViewById(R.id.bt_2);
                //商品图片
                iv_product_picture = (ImageView) view.findViewById(R.id.iv_product_picture);
            }
        }
    }

}
