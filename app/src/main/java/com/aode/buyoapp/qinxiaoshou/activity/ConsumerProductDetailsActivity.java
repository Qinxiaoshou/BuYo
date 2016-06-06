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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.Presenter.QueryProductsBuyBIdPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.QueryProductsBuyBidView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;


/**
 * 用户商品详情activity
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class ConsumerProductDetailsActivity extends AppCompatActivity implements QueryProductsBuyBidView {

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
    private TextView tv_store_descrite;
    private RelativeLayout rl_enter_store_ways;
    private TextView tv_type;
    private TextView tv_good_descripe;
    QueryProductsBuyBIdPresenter queryProductsBuyBIdPresenter = new QueryProductsBuyBIdPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取商品信息
        Intent intent = getIntent();
        cloth = (Cloth) intent.getSerializableExtra("cloth");
        try {

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
            //店铺描述
            tv_store_descrite = (TextView) findViewById(R.id.tv_store_descrite);
            //进入店铺
            rl_enter_store_ways  = (RelativeLayout) findViewById(R.id.rl_enter_store_ways);
            //花纹
            tv_type = (TextView) findViewById(R.id.tv_type);
            //颜色
            tv_good_descripe = (TextView) findViewById(R.id.tv_good_descripe);

            new ImageLoader(cloth, iv_product).resume();
            tv_product_data_title.setText(cloth.getTitle());
            tv_product_data_price.setText(cloth.getPrice() + "/米");
            tv_company_title.setText(cloth.getBusiness().getName());
            if(cloth.getBusiness().getDescription()==null){
                tv_store_descrite.setText("");
            }else{
                tv_store_descrite.setText(cloth.getBusiness().getDescription());
            }
            if(cloth.getPattern()==null){
                tv_type.setText("");
            }else{
                tv_type.setText("  "+cloth.getPattern());
            }
            if(cloth.getColor()==null){
                tv_good_descripe.setText("");
            }else{
                tv_good_descripe.setText(cloth.getColor());
            }

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
            rl_enter_store_ways.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入店铺
                    queryProductsBuyBIdPresenter.QueryProductsBuyBId();
                }
            });

            //跳转到添加订单页面

            rg_h_open_permission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("".equals(Home_person.id) || Home_person.id == null) {
                        Toast.makeText(getApplicationContext(), "请登录个人用户账号，再购买", Toast.LENGTH_SHORT).show();
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


    //查询该商店的商品集合
    @Override
    public String getBId() {
        return cloth.getBusiness().getId();
    }

    @Override
    public void toMainActivity(List<Cloth> clothlist) {
        Intent intent = new Intent(getApplication(), SearchBusinessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("business", cloth.getBusiness());
        bundle.putSerializable("clothlist", (Serializable) clothlist);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void showFailedError() {

    }
}
