package com.aode.buyoapp.LL;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.aode.buyoapp.LL.Homepage.Business_HomePage;
import com.aode.buyoapp.LL.Personal.Business_Personal;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.R;

public class Home_business extends AppCompatActivity {
    private Business_HomePage homePage;
    private Business_Personal personal;
    private RadioGroup tabs;

    public static Fragment fragment;
    public static Business business ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_business);
        //按钮
        initView();
    }

    public void initView() {
        homePage = new Business_HomePage();
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
                        /*if (address == null) {
                            address = new FragmentAddress();
                        }
                        *//*Log.i("MyFragment", "FragmentAddress");*//*
                        getSupportFragmentManager().beginTransaction().replace(R.id.id_fragment_content, address).commit();*/
                        break;
                    case R.id.rb_Recruits:
                        /*find = new FragmentFind();
                        getSupportFragmentManager().beginTransaction().replace(R.id.id_fragment_content, find).commit();*/
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
}
