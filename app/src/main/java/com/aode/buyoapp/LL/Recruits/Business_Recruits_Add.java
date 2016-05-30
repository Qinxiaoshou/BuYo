package com.aode.buyoapp.LL.Recruits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.RecruitsAddPresenter;
import com.aode.buyoapp.LL.bean.Recruit;
import com.aode.buyoapp.LL.view.RecruitsAddView;
import com.aode.buyoapp.R;

/**
 * Created by LiLei on 2016/5/14.Go.
 * 招聘页面
 */
public class Business_Recruits_Add extends AppCompatActivity implements RecruitsAddView {

    private ImageView iv_person_back;
    private Button btn_recruits_add;
    private Recruit recruit;

    RecruitsAddPresenter recruitsAddPresenter = new RecruitsAddPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_business_recruits_add);

        add();

        iv_person_back = (ImageView) findViewById(R.id.iv_person_back);
        iv_person_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void add() {
        btn_recruits_add = (Button) findViewById(R.id.btn_recruits_add);
        btn_recruits_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recruit = new Recruit();
                recruit.setTitle(((EditText) findViewById(R.id.tv_business_title)).getText().toString().trim());
                recruit.setIssuer(((EditText) findViewById(R.id.tv_business_issuer)).getText().toString().trim());
                recruit.setContent(((EditText) findViewById(R.id.tv_business_content)).getText().toString().trim());
                recruit.setPhoneNumber(((EditText) findViewById(R.id.tv_business_phoneNumber)).getText().toString().trim());
                recruit.setAddress(((EditText) findViewById(R.id.tv_business_address)).getText().toString().trim());

                recruitsAddPresenter.recruitsAdd();
            }
        });
    }

    @Override
    public Recruit getRecruit() {
        return recruit;
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(this, "添加招聘信息成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "添加招聘信息失败", Toast.LENGTH_SHORT).show();
    }
}