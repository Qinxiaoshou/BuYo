package com.aode.buyoapp.LL.Logistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.R;

/**
 * Created by LiLei on 2016/5/14.Go.
 * 物流页面-快递官网
 */
public class Person_Logistics_ExpressWeb extends Fragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_logistics_web, container, false);
        return view;
    }

}