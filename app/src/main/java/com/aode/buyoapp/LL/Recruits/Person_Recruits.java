package com.aode.buyoapp.LL.Recruits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.R;

/**
 * Created by LiLei on 2016/5/14.Go.
 * 招聘页面
 */
public class Person_Recruits extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private RecruitsListAdapter recruitsListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_recruits, container, false);
        addRecruits();
        return view;
    }

    private void addRecruits() {
        recyclerView = (RecyclerView) view.findViewById(R.id.person_recruits);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recruitsListAdapter = new RecruitsListAdapter(getContext()));
        //点击事件
        recruitsListAdapter.setOnItemClickLitener(new RecruitsListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击进入招聘详情
                Intent intent = new Intent(getActivity(), Person_Recruits_Detail.class);
                startActivity(intent);
                System.out.println("位置:" + position);
            }
        });
    }
}