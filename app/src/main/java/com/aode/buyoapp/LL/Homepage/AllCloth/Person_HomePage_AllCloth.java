package com.aode.buyoapp.LL.Homepage.AllCloth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.aode.buyoapp.R;

public class Person_HomePage_AllCloth extends AppCompatActivity {
    private ClothTypeFragment typeFragment;
    private ClothListFragment detailFragment;
    private ImageView iv_homepage_allcloth_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person_homepage_allcloth);

        typeFragment = new ClothTypeFragment();
        detailFragment = new ClothListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_list, typeFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, detailFragment).commit();

        iv_homepage_allcloth_back = (ImageView) findViewById(R.id.iv_homepage_allcloth_back);
        iv_homepage_allcloth_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
