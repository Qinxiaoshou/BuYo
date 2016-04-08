package com.aode.buyoapp.LL;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aode.buyoapp.R;


public class ChooseActivity extends AppCompatActivity {
    private Button cpButton;
    private Button cbButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        init();
    }

    public void init() {
        cpButton = (Button) findViewById(R.id.btn_chooseperson);
        cbButton = (Button) findViewById(R.id.btn_chooseBusiness);

        cpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, Home_person.class);
                startActivity(intent);
            }
        });
        cbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, Home_business.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);//true对任何Activity都适用
        return;
    }
}
