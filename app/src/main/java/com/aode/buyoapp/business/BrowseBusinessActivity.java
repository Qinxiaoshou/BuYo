package com.aode.buyoapp.business;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aode.buyoapp.R;

/**
 * 布约app厂商一览界面
 *
 * @author 陈映苗
 * @// FIXME: 2016/5/7
 */
public class BrowseBusinessActivity extends AppCompatActivity {

    private ImageView iv_browse_business_back;
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
    }
}
