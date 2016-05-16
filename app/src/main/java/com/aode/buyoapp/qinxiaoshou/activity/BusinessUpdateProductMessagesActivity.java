package com.aode.buyoapp.qinxiaoshou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.BusinessProductChangePresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductChangeView;
import com.aode.buyoapp.R;


/**
 * 修改商品信息
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessUpdateProductMessagesActivity extends FragmentActivity implements IBusinessProductChangeView {

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private RadioGroup rg_h;
    private Cloth cloth;
    private Button button;
    BusinessProductChangePresenter businessProductChangePresenter = new BusinessProductChangePresenter(this);


    private EditText et_title;
    private EditText et_size;
    private EditText et_price;
    private EditText et_stock;
    private EditText et_color;
    private EditText et_parttern;
    private Cloth cloth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_update_product_content);
        //获取前面页面传递过来的数据
        Intent intent = this.getIntent();
        cloth = (Cloth) intent.getSerializableExtra("cloth");
        button = (Button) findViewById(R.id.btn_right_update);

        et_title = (EditText) findViewById(R.id.et_title);
        et_size = (EditText) findViewById(R.id.et_size);
        et_price = (EditText) findViewById(R.id.et_price);
        et_stock = (EditText) findViewById(R.id.et_stock);
        et_color = (EditText) findViewById(R.id.et_color);
        et_parttern = (EditText) findViewById(R.id.et_parttern);

        toolbarTitle = (TextView) findViewById(R.id.tv_g_update_product_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar_business_product_details_update);
        rg_h = (RadioGroup) findViewById(R.id.rg_h_open_permission);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rg_h.setVisibility(View.INVISIBLE);
        toolbarTitle.setText("修改商品信息");


        et_title.setText(cloth.getTitle());
        et_size.setText(cloth.getSize());
        et_price.setText(cloth.getPrice() + "");
        et_stock.setText(cloth.getStock() + "");
        et_color.setText(cloth.getColor());
        et_parttern.setText(cloth.getPattern());
        //完成按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cloth2 = new Cloth();
                    Long id = cloth.getId();
                        cloth2.setId(id);
                        cloth2.setTitle(et_title.getText().toString().trim());
                        cloth2.setSize(et_size.getText().toString().trim());
                        cloth2.setPrice(Double.valueOf(et_price.getText().toString().trim()));
                        cloth2.setStock(Long.valueOf(et_stock.getText().toString().trim()));
                        cloth2.setColor(et_color.getText().toString().trim());
                        cloth2.setPattern(et_parttern.getText().toString().trim());
                        cloth2.setbId(cloth.getbId());
                    businessProductChangePresenter.ProductChange();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"请填写完整布匹信息",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public Cloth getProduct() {
        return cloth2;
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(),"修改失败",Toast.LENGTH_SHORT).show();
        finish();
    }

}
