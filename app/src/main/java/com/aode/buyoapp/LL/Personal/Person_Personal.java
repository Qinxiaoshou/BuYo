package com.aode.buyoapp.LL.Personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aode.buyoapp.R;


public class Person_Personal extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView iv_message, iv_order, iv_limit, iv_changePassword;

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_personal, container, false);
        init();
        return view;
    }

    public void init() {
        iv_message = (ImageView) view.findViewById(R.id.iv_message);
        iv_order = (ImageView) view.findViewById(R.id.iv_order);
        iv_limit = (ImageView) view.findViewById(R.id.iv_limit);
        iv_changePassword = (ImageView) view.findViewById(R.id.iv_changePassword);
        iv_message.setOnClickListener(this);
        iv_order.setOnClickListener(this);
        iv_limit.setOnClickListener(this);
        iv_changePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_message:
                startActivity(new Intent(getActivity(), Person_Message.class));
                break;
            case R.id.iv_order:
                Toast.makeText(getActivity(), "店家信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_limit:

                break;
            case R.id.iv_changePassword:
                startActivity(new Intent(getActivity(), Person_password_update.class));
                break;
        }
    }
}