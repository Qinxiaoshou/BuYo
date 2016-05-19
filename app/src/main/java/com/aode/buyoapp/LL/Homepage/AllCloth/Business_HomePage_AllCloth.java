package com.aode.buyoapp.LL.Homepage.AllCloth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.aode.buyoapp.R;

public class Business_HomePage_AllCloth extends AppCompatActivity {
    private ClothTypeFragment typeFragment;
    private BusinessClothListFragment businessClothListFragment;
    private ImageView iv_homepage_allcloth_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_business_person_homepage_allcloth);

        typeFragment = new ClothTypeFragment();
        businessClothListFragment = new BusinessClothListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_list, typeFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, businessClothListFragment).commit();

        iv_homepage_allcloth_back = (ImageView) findViewById(R.id.iv_homepage_allcloth_back);
        iv_homepage_allcloth_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
