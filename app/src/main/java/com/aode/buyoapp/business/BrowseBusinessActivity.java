package com.aode.buyoapp.business;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aode.buyoapp.R;
import com.aode.buyoapp.business.activity.BusinessDetailActivity;

/**
 * 布约app厂商一览界面
 *
 * @author 陈映苗
 * @// FIXME: 2016/5/7
 */
public class BrowseBusinessActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_browse_business_back;
    private RelativeLayout rl_browse_1,rl_browse_2,rl_browse_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_business);
        iv_browse_business_back = (ImageView)findViewById(R.id.iv_back);
        iv_browse_business_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rl_browse_1 = (RelativeLayout)findViewById(R.id.rl_browse_1);
        rl_browse_2 = (RelativeLayout)findViewById(R.id.rl_browse_2);
        rl_browse_3 = (RelativeLayout)findViewById(R.id.rl_browse_3);
        rl_browse_1.setOnClickListener(this);
        rl_browse_2.setOnClickListener(this);
        rl_browse_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_browse_1:
                startActivity(new Intent(BrowseBusinessActivity.this, BusinessDetailActivity.class));
                break;
            case R.id.rl_browse_2:
                startActivity(new Intent(BrowseBusinessActivity.this, BusinessDetailActivity.class));
                break;
            case R.id.rl_browse_3:
                startActivity(new Intent(BrowseBusinessActivity.this, BusinessDetailActivity.class));
                break;
        }
    }
}
