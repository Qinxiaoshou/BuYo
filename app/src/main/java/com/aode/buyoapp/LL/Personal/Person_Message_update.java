package com.aode.buyoapp.LL.Personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_person;
import com.aode.buyoapp.LL.Presenter.UserMessageChangePresenter;
import com.aode.buyoapp.LL.bean.User;
import com.aode.buyoapp.LL.view.IUserMessageChangeView;
import com.aode.buyoapp.R;

/**
 * Created by LiLei on 2016/5/20.Go.
 */

public class Person_Message_update extends AppCompatActivity implements IUserMessageChangeView {
    private Button button;
    private RadioGroup radioGroup;
    private String change_LoginName, change_realName, change_Phone, change_Email;
    private String change_gender = "男";
    private User user;
    private ImageView iv_back;
    /**
     * 修改商家资料交互接口
     */
    UserMessageChangePresenter userMessageChangePresenter = new UserMessageChangePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_message_update);
        //返回键
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_personMessage);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        upDate();
    }

    public void upDate() {
        button = (Button) findViewById(R.id.btn_update_personMessage_confirm);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获得view的值存入一个对象返回过去
                        change_LoginName = ((EditText) findViewById(R.id.change_LoginName)).getText().toString();
                        change_realName = ((EditText) findViewById(R.id.change_Name)).getText().toString();
                        change_Phone = ((EditText) findViewById(R.id.change_Phone)).getText().toString();
                        change_Email = ((EditText) findViewById(R.id.change_Email)).getText().toString();

                        radioGroup = (RadioGroup) findViewById(R.id.change_gender);
                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                switch (checkedId) {
                                    case R.id.rb_person_gender_boy:
                                        change_gender = "男";
                                        break;
                                    case R.id.rb_person_gender_girl:
                                        change_gender = "女";
                                        break;
                                }
                            }
                        });

                        //将数据整合到对象
                        user = new User();
                        user.setId(Home_person.id);
                        user.setLoginName(change_LoginName);
                        user.setName(change_realName);
                        user.setPhoneNumber(change_Phone);
                        user.setEmail(change_Email);
                        user.setGender(change_gender);
                        if (!"".equals(user.getLoginName()) && user.getLoginName() != null) {
                            userMessageChangePresenter.change();
                        } else {
                            Toast.makeText(getApplication(), "名字不能为空!", Toast.LENGTH_SHORT).show();
                        }
                        //进行修改数据

                    }
                }

        );
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(getApplication(), "修改成功!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplication(), "修改失败,请检查网络和内容!", Toast.LENGTH_SHORT).show();
    }
}
