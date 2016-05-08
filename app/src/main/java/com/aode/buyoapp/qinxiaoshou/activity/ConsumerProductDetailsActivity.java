package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.ProductItemDetailsFragment;


/**
 * 用户商品详情activity
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ConsumerProductDetailsActivity extends AppCompatActivity {

    private ProductItemDetailsFragment productItemDetailsFragment;
    private TextView tv_g_add_product_title;
    private Button button;
    private Toolbar toolbar;
    private RadioGroup rg_h_open_permission;
    private TextView tv_rg_name;
    private Cloth cloth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取商品信息
        Intent intent = getIntent();
        cloth = (Cloth) intent.getSerializableExtra("cloth");

        setContentView(R.layout.business_product_message_layout);
        //步骤一：添加一个FragmentTransaction的实例
      /*  FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();*/
        //步骤二：用add()方法加上Fragment的对象rightFragment
     /*   productItemDetailsFragment = new ProductItemDetailsFragment(cloth);*/
        toolbar = (Toolbar) findViewById(R.id.toolbar_business_product_details);
        tv_rg_name = (TextView) findViewById(R.id.tv_rg_name);
        tv_g_add_product_title = (TextView) findViewById(R.id.tv_g_add_product_title);
        rg_h_open_permission = (RadioGroup) findViewById(R.id.rg_h_open_permission);
        button = (Button) findViewById(R.id.btn_right_text);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_g_add_product_title.setText("商品详情");
        rg_h_open_permission.setVisibility(View.VISIBLE);
        tv_rg_name.setText("立即购买");
        button.setVisibility(View.GONE);
       /* transaction.add(R.id.fl_g_framelayout, productItemDetailsFragment).commit();*/

       //跳转到添加订单页面

            rg_h_open_permission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("".equals(Home_person.id) || Home_person.id == null) {
                        Toast.makeText(getApplicationContext(), "请登录，再购买", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(ConsumerProductDetailsActivity.this, ConsumerAddOrderDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cloth", cloth);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        final TextView expandView = (TextView) findViewById(R.id.expand_view);
        final LinearLayout descriptionView = (LinearLayout)findViewById(R.id.description_layout);
        findViewById(R.id.expand_view).setOnClickListener(new View.OnClickListener() {
            boolean isExpand;

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                descriptionView.clearAnimation();
                int durationMillis = 350;
                if (isExpand) {
                    Animation animation = new Animation() {
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            //设置下拉图片方向
                            Drawable nav_up = getResources().getDrawable(R.drawable.ach_turn);
                            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                            expandView.setCompoundDrawables(null, null, nav_up, null);
                            //展示商品详情内容
                            descriptionView.setVisibility(View.VISIBLE);
                        }

                    };
                    animation.setDuration(durationMillis);
                    descriptionView.startAnimation(animation);

                } else {
                    Animation animation = new Animation() {
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            Drawable nav_up = getResources().getDrawable(R.drawable.ach);
                            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                            expandView.setCompoundDrawables(null, null, nav_up, null);
                            descriptionView.setVisibility(View.GONE);
                        }

                    };
                    animation.setDuration(durationMillis);
                    descriptionView.startAnimation(animation);

                }


            }
        });
    }
}
