package com.aode.buyoapp.LL.Homepage.AllCloth;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.bean.Item;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.ConsumerProductDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ClothListFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private MLManager mlManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // register
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_homepage_clothlist, container, false);
        return view;
    }

    /**
     * List点击时会发送些事件，接收到事件后更新详情
     */
    public void onEventMainThread(Item item) {
        if (item != null) {
            //处理信息
            recyclerView = (RecyclerView) view.findViewById(R.id.rv_detail);
            //设置布局管理器,重写使之自适应
            recyclerView.setLayoutManager(mlManager = new MLManager(getActivity(), 2));
            //设置adapter
            List<Cloth> cloths = new ArrayList<Cloth>();
            for (int i = 0; i < 10; i++) {
                //引用
                Cloth cloth = new Cloth();
                cloth.setTitle(i + item.getContent() + ":" + item.getContent());
                cloth.setPrice((double) ((i * 10) + 12));
                cloths.add(cloth);
            }
            recyclerView.setAdapter(listAdapter = new ListAdapter(getActivity(), cloths));
            //点击事件
            listAdapter.setOnItemClickLitener(new ListAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    //点击进入商品详情
                    Intent intent = new Intent(getActivity(), ConsumerProductDetailsActivity.class);
                    startActivity(intent);
                    System.out.println("位置:" + position);
                }
            });
        }
    }
}
