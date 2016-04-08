package com.aode.buyoapp.LL;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.aode.buyoapp.R;

public class WelcomeActivity extends AppCompatActivity {
    private final int TIME = 2000;
    private static final int GO_LOGIN = 1000;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==GO_LOGIN)
                login();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler.sendEmptyMessageDelayed(GO_LOGIN, TIME);
    }
    public void login(){
        Intent intent = new Intent(WelcomeActivity.this,ChooseActivity.class);
        startActivity(intent);
    }
}
