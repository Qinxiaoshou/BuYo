package com.aode.buyoapp.LL;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aode.buyoapp.LL.Homepage.Business_HomePage2;
import com.aode.buyoapp.LL.Logistics.Person_Logistics;
import com.aode.buyoapp.LL.Personal.Business_Personal;
import com.aode.buyoapp.LL.Recruits.Person_Recruits;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.R;

public class Home_business extends AppCompatActivity {
    private Business_HomePage2 homePage;
    private Person_Logistics logistics;
    private Person_Recruits recruits;
    private Business_Personal personal;
    private RadioGroup tabs;
    private long exitTime = 0;

    public static Fragment fragment;
    public static Business business;

    public static Home_business instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_business);
        instance = this;
        //按钮
        initView();
    }

    public void initView() {
        homePage = new Business_HomePage2();
        getSupportFragmentManager().beginTransaction().add(R.id.business_homepage, homePage).commit();
        fragment = homePage;
        tabs = (RadioGroup) findViewById(R.id.person_menu);
        tabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.rb_Page:
                        if (homePage != fragment) {
                            if (!homePage.isAdded()) {
                                getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.business_homepage, homePage).commit();
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
                                getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.business_homepage, logistics).commit();
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
                                getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.business_homepage, recruits).commit();
                            } else {
                                getSupportFragmentManager().beginTransaction().hide(fragment).show(recruits).commit();
                            }
                            fragment = recruits;
                        }
                        break;
                    case R.id.rb_Personal:
                        if (personal == null) {
                            personal = new Business_Personal();
                        }
                        if (personal != fragment) {
                            if (!personal.isAdded()) {
                                getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.business_homepage, personal).commit();
                            } else {
                                getSupportFragmentManager().beginTransaction().hide(fragment).show(personal).commit();
                            }
                            fragment = personal;
                        }
                        break;
                    default:
                        break;
                }

            }
        });
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
}

