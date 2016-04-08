package com.aode.buyoapp.LL.Personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aode.buyoapp.R;


public class Business_Personal extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView iv_message;
    private TextView tv_business_name;
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_business_personal, container, false);
        init();
        return view;
    }

    public void init() {
        iv_message = (ImageView) view.findViewById(R.id.iv_message);
        iv_message.setOnClickListener(this);
        tv_business_name = (TextView) view.findViewById(R.id.tv_business_name);
        tv_business_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent_Message = new Intent(getActivity(),Business_Message.class);
        switch (v.getId()) {
            case R.id.iv_message:
                startActivity(intent_Message);
                break;
            case R.id.tv_business_name:
                startActivity(intent_Message);
                break;
        }
    }
}