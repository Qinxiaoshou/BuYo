package com.aode.buyoapp.qinxiaoshou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        toolbar = (Toolbar)findViewById(R.id.toolbar_g_product_appbar);
        tv_g_add_product_title = (TextView) findViewById(R.id.tv_g_add_product_title);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        tv_g_add_product_title.setText("个人权限");
        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new ConsumerPermissionRecyclerViewAdapter());
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
        System.out.println("USERPermission:-->"+userList);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,"查询个人权限失败",Toast.LENGTH_SHORT).show();
    }
    /**
     * 用户权限数据适配器
     */
    public class ConsumerPermissionRecyclerViewAdapter extends RecyclerView.Adapter<ConsumerPermissionRecyclerViewAdapter.ViewHolder> {
        //列表页面的布局实现
        @Override
        public ConsumerPermissionRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumer__permisson_item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ConsumerPermissionRecyclerViewAdapter.ViewHolder holder, int position) {
         /*   //取出所有商品
            holder.ll_i_product_list.removeView(holder.ll_product_content);//移除默认view
            System.out.println(toBList);
            holder.tv_store_name.setText("店铺:" + toBList.get(position).getName());

            for (Cloth cloth : toBList.get(position).getCloths()) {
                //添加子view
                LinearLayout childLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.business_check_who_hava_permisson_product_item_content, null);
                //设置显示商品的商品条目详情
                iv_pictue = (ImageView) childLayout.findViewById(R.id.iv_pictue);
                tv_title = (TextView) childLayout.findViewById(R.id.tv_title);
                tv_price = (TextView) childLayout.findViewById(R.id.tv_price);
                tv_stock = (TextView) childLayout.findViewById(R.id.tv_stock);
                iv_pictue.setImageResource(R.drawable.cheese_3);  //默认图片
                tv_title.setText(cloth.getTitle());
                tv_price.setText("￥" + cloth.getPrice());
                tv_stock.setText("库存:" + cloth.getStock());
                //在商铺条目中添加子商品条目
                holder.ll_i_product_list.addView(childLayout);
            }*/
        }

        @Override
        public int getItemCount() {
            if(users==null){
                return 0;
            }else{
                return users.size();
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;


            public ViewHolder(View view) {
                super(view);
                mView = view;
            }
        }
    }

}
