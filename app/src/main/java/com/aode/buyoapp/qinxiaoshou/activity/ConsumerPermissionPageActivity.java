package com.aode.buyoapp.qinxiaoshou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.Presenter.UserQueryPermissionPresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.User;
import com.aode.buyoapp.LL.view.IBusinessFriendToMeView;
import com.aode.buyoapp.LL.view.IUserQueryPermissionProductView;
import com.aode.buyoapp.R;

import java.util.List;


/**
 * 个人权限
 *
 * @author 覃培周
 * @// FIXME: 2016/4/16
 */
public class ConsumerPermissionPageActivity extends AppCompatActivity implements IUserQueryPermissionProductView {

    private TextView tv_g_add_product_title;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<User> users;
    UserQueryPermissionPresenter userQueryPermissionPresenter = new UserQueryPermissionPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_permission_layout);
        //调用交互层的方法
        userQueryPermissionPresenter.QueryPermission();

        toolbar = (Toolbar) findViewById(R.id.toolbar_g_product_appbar);
        tv_g_add_product_title = (TextView) findViewById(R.id.tv_g_add_product_title);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        tv_g_add_product_title.setText("个人权限");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public String getUId() {
        return Home_person.id;
    }

    @Override
    public void toMainActivity(List<User> userList) {
        this.users = userList;
        System.out.println("USERPermission:-->" + users);
        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler_user_permission_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new ConsumerPermissionRecyclerViewAdapter());
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "查询个人权限失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 用户权限数据适配器
     */
    public class ConsumerPermissionRecyclerViewAdapter extends RecyclerView.Adapter<ConsumerPermissionRecyclerViewAdapter.ViewHolder> {

        private User user1;

        //列表页面的布局实现
        @Override
        public ConsumerPermissionRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumer__permisson_item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ConsumerPermissionRecyclerViewAdapter.ViewHolder holder, int position) {
            //设置显示商品的商品条目详情
            if(user1==null){

            }else {
                holder.rl_consumer_permission_content.setVisibility(View.VISIBLE);
                holder.tv_store_name.setText("店铺:" + user1.getCloths().get(position).getBusiness().getName());
                holder.iv_pictue.setImageResource(R.drawable.cheese_3);  //默认图片
                holder.tv_title.setText(user1.getCloths().get(position).getTitle());
                holder.tv_price.setText("￥" + user1.getCloths().get(position).getPrice());
                holder.tv_stock.setText("库存:" + user1.getCloths().get(position).getStock());
            }
        }

        @Override
        public int getItemCount() {
            if (users == null) {
                return 1;
            } else {
                //商品条目数
                user1 = null;
                int count = 0;
                for(User user : users){
                    if(count==0){
                        user1 = user;
                        return user1.getCloths().size();
                    }
                }
                return 1;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public TextView tv_store_name;
            public ImageView iv_pictue;
            public TextView tv_title;
            public TextView tv_price;
            public TextView tv_stock;
            public RelativeLayout rl_consumer_permission_content;


            public ViewHolder(View view) {
                super(view);
                //店铺名字
                tv_store_name = (TextView) view.findViewById(R.id.tv_store_name);
                //商品图片
                iv_pictue = (ImageView) view.findViewById(R.id.iv_pictue);
                //商品标题
                tv_title = (TextView) view.findViewById(R.id.tv_title);
                //商品单价
                tv_price = (TextView) view.findViewById(R.id.tv_price);
                //商品库存
                tv_stock = (TextView) view.findViewById(R.id.tv_stock);
                //商品条目布局
                rl_consumer_permission_content = (RelativeLayout) view.findViewById(R.id.rl_consumer_permission_content);
                mView = view;
            }
        }
    }

}
