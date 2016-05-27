package com.aode.buyoapp.qinxiaoshou.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessDownProductFragment;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessSortProductFragment;
import com.aode.buyoapp.qinxiaoshou.fragment.MessageFragment;


/**
 * 店家管理商品界面
 * @// FIXME: 2016/4/7
 * @author 覃培周
 */
public class BusinessProductManageSwitchActivity extends FragmentActivity {

    private Button btn_message, btn_call,btn_dowm;
    private ImageView iv_back; //返回键
    private BusinessSortProductFragment callFragment;
    private MessageFragment messageFragment;
    private BusinessDownProductFragment downProductFragment;

    public static final int MESSAGE_FRAGMENT_TYPE = 1;
    public static final int CALL_FRAGMENT_TYPE = 2;
    public static final int DOWNPRODUCTFRAGMENT=3;
    public int currentFragmentType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.business_product_list_layout);

        btn_message = (Button) findViewById(R.id.btn_message);
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_dowm = (Button) findViewById(R.id.btn_down);
        btn_message.setOnClickListener(onClicker);
        btn_call.setOnClickListener(onClicker);
        btn_dowm .setOnClickListener(onClicker);
        iv_back = (ImageView) findViewById(R.id.iv_back_bussines);
        iv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            int type = savedInstanceState.getInt("currentFragmentType");
            messageFragment = (MessageFragment) fragmentManager.findFragmentByTag("message");
            callFragment = (BusinessSortProductFragment) fragmentManager.findFragmentByTag("call");
            downProductFragment = (BusinessDownProductFragment) fragmentManager.findFragmentByTag("dowmproduct");
            if (type > 0)
                loadFragment(type);
        } else {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment mainFragment = fragmentManager.findFragmentByTag("message");
            if (mainFragment != null) {
                transaction.replace(R.id.fl_content, mainFragment);
                transaction.commit();
            } else {
                loadFragment(MESSAGE_FRAGMENT_TYPE);  //默认打开界面
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("lastFragmentTag", currentFragmentType);
    }

    private void switchFragment(int type) {
        switch (type) {
            case MESSAGE_FRAGMENT_TYPE:
                loadFragment(MESSAGE_FRAGMENT_TYPE);
                break;
            case CALL_FRAGMENT_TYPE:
                loadFragment(CALL_FRAGMENT_TYPE);
                break;
            case DOWNPRODUCTFRAGMENT:
                loadFragment(DOWNPRODUCTFRAGMENT);
                break;
        }

    }

    public void loadFragment(int type) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (type == CALL_FRAGMENT_TYPE) {
            if (callFragment == null) {
                callFragment = new BusinessSortProductFragment();

                transaction.add(R.id.fl_content, callFragment, "zhishi");
            } else {
                transaction.show(callFragment);
            }
            if (messageFragment != null&&downProductFragment!=null) {
                transaction.hide(messageFragment);
                transaction.hide(downProductFragment);
            }
            currentFragmentType = MESSAGE_FRAGMENT_TYPE;
        }else if(type==DOWNPRODUCTFRAGMENT){
            if(downProductFragment ==null){
                downProductFragment = new BusinessDownProductFragment();

                transaction.add(R.id.fl_content, downProductFragment, "downproduct");
            } else {
                transaction.show(downProductFragment);
            }
            if (messageFragment != null&&callFragment!=null) {
                transaction.hide(messageFragment);
                transaction.hide(callFragment);
            }
            currentFragmentType = MESSAGE_FRAGMENT_TYPE;

        }else {
            if (messageFragment == null) {
                messageFragment = new MessageFragment();
                transaction.add(R.id.fl_content, messageFragment, "wenda");
            } else {
                transaction.show(messageFragment);
            }
            if (callFragment != null&downProductFragment!=null) {
                transaction.hide(callFragment);
                transaction.hide(downProductFragment);
            }
            currentFragmentType = CALL_FRAGMENT_TYPE;
        }
        transaction.commitAllowingStateLoss();
    }

    private OnClickListener onClicker = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_message:
                    btn_message.setTextColor(Color.parseColor("#df3031"));
                    btn_call.setTextColor(Color.WHITE);
                    btn_dowm.setTextColor(Color.WHITE);
                    btn_message.setBackgroundResource(R.drawable.baike_btn_pink_left_f_96);
                    btn_call.setBackgroundResource(R.drawable.baike_btn_trans_right_f_96);
                    btn_dowm.setBackgroundResource(R.drawable.baike_btn_trans_center_f_96);
                    switchFragment(MESSAGE_FRAGMENT_TYPE);

                    break;
                case R.id.btn_call:
                    btn_dowm.setTextColor(Color.WHITE);
                    btn_message.setTextColor(Color.WHITE);
                    btn_call.setTextColor(Color.parseColor("#df3031"));
                    btn_message.setBackgroundResource(R.drawable.baike_btn_trans_left_f_96);
                    btn_call.setBackgroundResource(R.drawable.baike_btn_pink_right_f_96);
                    btn_dowm.setBackgroundResource(R.drawable.baike_btn_trans_center_f_96);
                    switchFragment(CALL_FRAGMENT_TYPE);

                    break;

                case R.id.btn_down:
                    btn_dowm.setTextColor(Color.parseColor("#df3031"));
                    btn_message.setTextColor(Color.WHITE);
                    btn_call.setTextColor(Color.WHITE);
                    btn_call.setBackgroundResource(R.drawable.baike_btn_trans_right_f_96);
                    btn_message.setBackgroundResource(R.drawable.baike_btn_trans_left_f_96);
                    btn_dowm.setBackgroundResource(R.drawable.baike_btn_pink_center_f_96);
                    switchFragment(DOWNPRODUCTFRAGMENT);

                    break;

            }
        }
    };

}
