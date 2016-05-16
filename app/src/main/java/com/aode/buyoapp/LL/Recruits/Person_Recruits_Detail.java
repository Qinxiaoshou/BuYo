package com.aode.buyoapp.LL.Recruits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.aode.buyoapp.R;

/**
 * Created by LiLei on 2016/5/15.Go.
 * 招聘页面-招聘详情页
 */
public class Person_Recruits_Detail extends AppCompatActivity {

    private ImageView iv_Recruits_msg_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_recruits_detail);

        iv_Recruits_msg_back = (ImageView) findViewById(R.id.iv_Recruits_msg_back);
        iv_Recruits_msg_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}