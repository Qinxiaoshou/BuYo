package com.aode.buyoapp.LL;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aode.buyoapp.LL.Homepage.Person_HomePage;
import com.aode.buyoapp.LL.Personal.Person_Personal;
import com.aode.buyoapp.R;

public class Home_person extends AppCompatActivity implements Login_person.onNameListener {

    private Person_HomePage homePage;
    private Person_Personal personal;
    private Login_person login_person;
    private RadioGroup tabs;
    public static Fragment fragment;
    public static boolean result = false;
    public static String loginName = "个人用户名";
    public static String id = "";
    private TextView tv_person_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_person);
        //按钮
        initView();

    }

    public void initView() {
        homePage = new Person_HomePage();
        getSupportFragmentManager().beginTransaction().add(R.id.person_homepage, homePage).commit();
        fragment = homePage;
        tabs = (RadioGroup) findViewById(R.id.person_menu);
        tabs.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        switch (checkedId) {
                            case R.id.rb_Page:
                                if (homePage != fragment) {
                                    if (!homePage.isAdded()) {
                                        getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.person_homepage, homePage).commit();
                                    } else {
                                        getSupportFragmentManager().beginTransaction().hide(fragment).show(homePage).commit();
                                    }
                                    fragment = homePage;
                                }
                                break;
                            case R.id.rb_Logistics:
                                break;
                            case R.id.rb_Recruits:
                                break;
                            case R.id.rb_Personal:
                                if (personal == null)
                                    personal = new Person_Personal();
                                if (personal != fragment) {
                                    if (!personal.isAdded()) {
                                        getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.person_homepage, personal).commit();
                                    } else {
                                        getSupportFragmentManager().beginTransaction().hide(fragment).show(personal).commit();
                                    }
                                    fragment = personal;
                                    if (!result) {
                                        if (login_person == null)
                                            login_person = new Login_person();
                                        if (!login_person.isAdded()) {
                                            getSupportFragmentManager().beginTransaction().hide(personal).add(R.id.person_homepage, login_person).commit();
                                        } else {
                                            getSupportFragmentManager().beginTransaction().hide(personal).show(login_person).commit();
                                        }
                                        fragment = login_person;
                                    }
                                }
                                break;
                            default:
                                break;
                        }

                    }
                }

        );
    }

    @Override
    public void onTest(String name) {
        loginName = name;
        //回显用户名
        tv_person_name = (TextView) findViewById(R.id.tv_person_name);
        tv_person_name.setText(loginName);
        getSupportFragmentManager().beginTransaction().hide(fragment).show(personal).commit();
        fragment = personal;
    }

    @Override
    protected void onDestroy() {
        result = false;
        super.onDestroy();
    }
}
