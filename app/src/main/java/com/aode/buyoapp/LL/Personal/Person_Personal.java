package com.aode.buyoapp.LL.Personal;

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
    private ImageView iv_amity;

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
        iv_amity = (ImageView) view.findViewById(R.id.iv_message);
        iv_amity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_amity:
                Toast.makeText(getActivity(), "店家信息", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}