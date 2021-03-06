package com.aode.buyoapp.LL.Recruits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.RecruitsPresenter;
import com.aode.buyoapp.LL.bean.Recruit;
import com.aode.buyoapp.LL.view.RecruitsView;
import com.aode.buyoapp.R;

import java.util.List;

/**
 * Created by LiLei on 2016/5/14.Go.
 * 招聘页面
 */
public class Person_Recruits extends Fragment implements RecruitsView {
    private View view;
    private RecyclerView recyclerView;
    private RecruitsListAdapter recruitsListAdapter;

    RecruitsPresenter recruitsPresenter = new RecruitsPresenter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_recruits, container, false);
        recruitsPresenter.recruitsList();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
            recruitsPresenter.recruitsList();
    }

    @Override
    public void toMainActivity(final List<Recruit> recruits) {
        recyclerView = (RecyclerView) view.findViewById(R.id.person_recruits);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recruitsListAdapter = new RecruitsListAdapter(getContext(), recruits));
        //点击事件
        recruitsListAdapter.setOnItemClickLitener(new RecruitsListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击进入招聘详情
                Intent intent = new Intent(getActivity(), Person_Recruits_Detail.class);
                intent.putExtra("id", recruits.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getActivity(), "招聘信息获取失败", Toast.LENGTH_SHORT).show();
    }
}