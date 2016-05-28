package com.aode.buyoapp.LL.Homepage.AllCloth;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.BusinessNoMeProductsPresenter;
import com.aode.buyoapp.LL.Presenter.UserClothListPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessNoMeProductView;
import com.aode.buyoapp.LL.view.IUserClothListView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessProductDetailsActivity;
import com.aode.buyoapp.qinxiaoshou.activity.ConsumerProductDetailsActivity;

import java.util.List;
import java.util.logging.Handler;

import de.greenrobot.event.EventBus;

public class BusinessClothListFragment extends Fragment implements IBusinessNoMeProductView, IUserClothListView {
    private View view;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private MLManager mlManager;
    private String type;
    public static String label = "";

    BusinessNoMeProductsPresenter businessNoMeProductsPresenter = new BusinessNoMeProductsPresenter(this);
    UserClothListPresenter userClothListPresenter = new UserClothListPresenter(this);

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
    public void onEventMainThread(String string) {
        if (string != null) {
            if ("-1".equals(string)) {
                businessNoMeProductsPresenter.QueryAllProduct();
            } else {
                type = string;
                userClothListPresenter.getClothList();
            }
        } else {
            Toast.makeText(getActivity(), "未知类型!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void toMainActivity(final List<Cloth> clothlist) {
        //处理信息
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_detail);
        //设置布局管理器,重写使之自适应
        recyclerView.setLayoutManager(mlManager = new MLManager(getActivity(), 2));
        //设置adapter
        recyclerView.setAdapter(listAdapter = new ListAdapter(getActivity(), clothlist));
        //点击事件
        listAdapter.setOnItemClickLitener(new ListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //点击进入商品详情
                Intent intent = new Intent(getActivity(), BusinessProductDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cloth",clothlist.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
                System.out.println("位置:" + position);
            }
        });
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getActivity(), "加载失败，请检查网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNone() {
        Toast.makeText(getActivity(), "未查到任何商品!", Toast.LENGTH_SHORT).show();
    }
}
