package com.aode.buyoapp.LL;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.aode.buyoapp.LL.Homepage.Person_HomePage2;
import com.aode.buyoapp.LL.Logistics.Person_Logistics;
import com.aode.buyoapp.LL.Personal.Person_Personal;
import com.aode.buyoapp.LL.Recruits.Person_Recruits;
import com.aode.buyoapp.R;

/**
 * Created by LiLei on 2016/5/24.Go.
 */
public class Home_person extends AppCompatActivity implements Login_person.onNameListener,Person_Personal.onLoginOutListener {

    private Person_HomePage2 homePage;
    private Person_Logistics logistics;
    private Person_Recruits recruits;
    private Person_Personal personal;
    private Login_person login_person;

    private RadioGroup tabs;
    public static Fragment fragment;
    public static boolean result = false;
    public static String loginName = "个人用户名";
    public static String id = "";
    public static String phone = "";
    private TextView tv_person_name;
    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_person);
        //按钮
        initView();

    }

    public void initView() {
        homePage = new Person_HomePage2();
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
                                if (logistics == null)
                                    logistics = new Person_Logistics();
                                if (logistics != fragment) {
                                    if (!logistics.isAdded()) {
                                        getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.person_homepage, logistics).commit();
                                    } else {
                                        getSupportFragmentManager().beginTransaction().hide(fragment).show(logistics).commit();
                                    }
                                    fragment = logistics;
                                }
                                break;
                            case R.id.rb_Recruits:
                                if (recruits == null)
                                    recruits = new Person_Recruits();
                                if (recruits != fragment) {
                                    if (!recruits.isAdded()) {
                                        getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.person_homepage, recruits).commit();
                                    } else {
                                        getSupportFragmentManager().beginTransaction().hide(fragment).show(recruits).commit();
                                    }
                                    fragment = recruits;
                                }
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        result = false;
        super.onDestroy();
    }

    @Override
    public void onLoginOut() {
        result = false;
        loginName = "个人用户名";
        id = "";
        phone = "";
        login_person = null;

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
