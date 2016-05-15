package com.aode.buyoapp.LL.Logistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.aode.buyoapp.R;

/**
 * Created by LiLei on 2016/5/14.Go.
 * 物流页面
 */
public class Person_Logistics extends Fragment {
    private View view;
    private RadioGroup tabs;
    private Fragment person_logistics_expressWeb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_logistics, container, false);

        initView();
        return view;
    }

    public void initView() {
        tabs = (RadioGroup) view.findViewById(R.id.person_logistics_menu);

        person_logistics_expressWeb = new Person_Logistics_ExpressWeb();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.person_logistics, person_logistics_expressWeb).commit();
    }
}