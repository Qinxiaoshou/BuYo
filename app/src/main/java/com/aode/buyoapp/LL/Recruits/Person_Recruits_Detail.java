package com.aode.buyoapp.LL.Recruits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.RecruitsDetailPresenter;
import com.aode.buyoapp.LL.bean.Recruit;
import com.aode.buyoapp.LL.view.RecruitsDetailView;
import com.aode.buyoapp.R;

import java.text.SimpleDateFormat;

/**
 * Created by LiLei on 2016/5/15.Go.
 * 招聘页面-招聘详情页
 */
public class Person_Recruits_Detail extends AppCompatActivity implements RecruitsDetailView {

    private ImageView iv_Recruits_msg_back;
    private Recruit recruit;
    private TextView tv_recruits_work_workName, tv_recruits_work_area, tv_recruits_work_content, tv_recruits_work_time;

    RecruitsDetailPresenter recruitsDetailPresenter = new RecruitsDetailPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_recruits_detail);
        recruitsDetailPresenter.recruitsDetail();
        iv_Recruits_msg_back = (ImageView) findViewById(R.id.iv_Recruits_msg_back);
        iv_Recruits_msg_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public Recruit getId() {
        recruit = new Recruit();
        recruit.setId(getIntent().getLongExtra("id", 0));
        return recruit;
    }

    @Override
    public void toMainActivity(Recruit recruits) {
        System.out.println("招聘详情:" + recruits.toString());

        tv_recruits_work_workName = (TextView) findViewById(R.id.tv_recruits_work_workName);
        tv_recruits_work_area = (TextView) findViewById(R.id.tv_recruits_work_area);
        tv_recruits_work_content = (TextView) findViewById(R.id.tv_recruits_work_content);
        tv_recruits_work_time = (TextView) findViewById(R.id.tv_recruits_work_time);
        tv_recruits_work_workName.setText(recruits.getTitle());
        tv_recruits_work_area.setText(recruits.getIssuer());
        tv_recruits_work_content.setText("要求:" + recruits.getContent() + "\n\n电话:" + recruits.getPhoneNumber() + "\n\n地址:" + recruits.getAddress());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (recruits.getDate() != null && "".equals(recruits.getDate())) {
            String dateString = formatter.format(recruits.getDate());
            tv_recruits_work_time.setText(dateString);
        }

    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "招聘详情获取失败", Toast.LENGTH_SHORT).show();
    }
}