package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;


/**
 * 用户商品详情activity
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ConsumerProductDetailsActivity extends AppCompatActivity {

    private TextView tv_g_add_product_title;
    private Button button;
    private Toolbar toolbar;
    private RadioGroup rg_h_open_permission;
    private TextView tv_rg_name;
    private Cloth cloth;
    private TextView tv_product_data_title;
    private TextView tv_product_data_price;
    private ImageView iv_product;
    private TextView tv_company_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //获取商品信息
            Intent intent = getIntent();
            cloth = (Cloth) intent.getSerializableExtra("cloth");
            setContentView(R.layout.business_product_message_layout);
            toolbar = (Toolbar) findViewById(R.id.toolbar_business_product_details);
            tv_rg_name = (TextView) findViewById(R.id.tv_rg_name);
            tv_g_add_product_title = (TextView) findViewById(R.id.tv_g_add_product_title);
            rg_h_open_permission = (RadioGroup) findViewById(R.id.rg_h_open_permission);
            iv_product = (ImageView) findViewById(R.id.iv_product);
            //商品标题
            tv_product_data_title = (TextView) findViewById(R.id.tv_product_data_title);
            //商品价格
            tv_product_data_price = (TextView) findViewById(R.id.tv_product_data_price);
            //商店标题
            tv_company_title = (TextView) findViewById(R.id.tv_company_title);

            new ImageLoader(cloth, iv_product).resume();
            tv_product_data_title.setText(cloth.getTitle());
            tv_product_data_price.setText(cloth.getPrice() + "/米");
            tv_company_title.setText(cloth.getBusiness().getName());

            button = (Button) findViewById(R.id.btn_right_text);
            toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            tv_g_add_product_title.setText("商品详情");
            rg_h_open_permission.setVisibility(View.VISIBLE);
            tv_rg_name.setText("立即购买");
            button.setVisibility(View.GONE);

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
            final LinearLayout descriptionView = (LinearLayout) findViewById(R.id.description_layout);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
