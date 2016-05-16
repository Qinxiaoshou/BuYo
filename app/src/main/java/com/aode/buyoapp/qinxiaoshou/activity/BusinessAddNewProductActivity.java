package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessProductAddPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductAddView;
import com.aode.buyoapp.R;

import java.util.ArrayList;


/**
 * 添加商品界面
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessAddNewProductActivity extends AppCompatActivity implements IBusinessProductAddView {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ArrayList<String> mSelectPath;
    public static final int REQUEST_IMAGE = 2;
    public String imgUrl;
    Bitmap bitmap = null;

    EditText et_title;
    EditText et_size;
    EditText et_price;
    EditText et_stock;
    EditText et_color;
    EditText et_parttern;
    ImageView iv_prodct_image;
    private Cloth cloth;
    BusinessProductAddPresenter businessProductAddPresenter = new BusinessProductAddPresenter(this);


    //完成按钮
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_add_product_content);

        et_title = (EditText) findViewById(R.id.et_title);
        et_size = (EditText) findViewById(R.id.et_size);
        et_price = (EditText) findViewById(R.id.et_price);
        et_stock = (EditText) findViewById(R.id.et_stock);
        et_color = (EditText) findViewById(R.id.et_color);
        et_parttern = (EditText) findViewById(R.id.et_parttern);
        //上传图片
        iv_prodct_image = (ImageView) findViewById(R.id.iv_prodct_image_add);


        //步骤一：添加一个FragmentTransaction的实例
        button = (Button) findViewById(R.id.btn_right_add);
        //步骤二：用add()方法加上Fragment的对象rightFragment
        toolbar = (Toolbar) findViewById(R.id.toolbar_business_product_details_add);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标

        //完成按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cloth = new Cloth();
                    cloth.setTitle(et_title.getText().toString().trim());
                    cloth.setSize(et_size.getText().toString().trim());
                    cloth.setPrice(Double.valueOf(et_price.getText().toString().trim()));
                    cloth.setStock(Long.valueOf(et_stock.getText().toString().trim()));
                    cloth.setColor(et_color.getText().toString().trim());
                    cloth.setPattern(et_parttern.getText().toString().trim());
                    cloth.setbId(Home_business.business.getId());
                    businessProductAddPresenter.ProductAdd();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "请填完整布匹信息", Toast.LENGTH_LONG).show();
                }
            }
        });


        iv_prodct_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(), "添加失败", Toast.LENGTH_SHORT).show();
        finish();
    }
}


