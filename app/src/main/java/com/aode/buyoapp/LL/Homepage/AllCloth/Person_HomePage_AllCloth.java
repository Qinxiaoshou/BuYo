package com.aode.buyoapp.LL.Homepage.AllCloth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aode.buyoapp.R;

public class Person_HomePage_AllCloth extends AppCompatActivity {
    private ClothTypeFragment typeFragment;
    private ClothListFragment detailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person_homepage_allcloth);

        typeFragment = new ClothTypeFragment();
        detailFragment = new ClothListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_list, typeFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, detailFragment).commit();
    }
}
