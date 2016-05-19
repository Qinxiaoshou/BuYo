package com.aode.buyoapp.LL.Homepage.AllCloth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.UserClothTypePresenter;
import com.aode.buyoapp.LL.bean.ClothCategory;
import com.aode.buyoapp.LL.bean.Event;
import com.aode.buyoapp.LL.bean.For_ClothType;
import com.aode.buyoapp.LL.view.IUserClothTypeView;
import com.aode.buyoapp.R;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ClothTypeFragment extends Fragment implements IUserClothTypeView {

    private RecyclerView recyclerView;
    private TypeAdapter typeAdapter;
    private View view;
    private List<String> type;

    UserClothTypePresenter userClothTypePresenter = new UserClothTypePresenter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Register
        让EventBus扫描当前类，把所有onEvent开头的方法记录下来.
        使用Map，Key为方法的参数类型，Value中包含我们的方法。
        */
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_homepage_clothtype, container, false);
        return view;
    }

    //onViewCreated在onCreateView执行完后立即执行。
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userClothTypePresenter.getClothType();
    }

    //接收消息
    public void onEventMainThread(final Event.ClothTypeListEvent event) {
        type = new ArrayList<String>();
        type.add("尺寸");
        For_ClothType.clothType_for(type, event.getClothCategory().getSize());
        type.add("颜色");
        For_ClothType.clothType_for(type, event.getClothCategory().getColor());
        type.add("图案");
        For_ClothType.clothType_for(type, event.getClothCategory().getPattern());
        type.add("宽度");
        For_ClothType.clothType_for(type, event.getClothCategory().getWidth());

        //处理信息
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        //设置布局管理器,重写使之自适应
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        recyclerView.setAdapter(typeAdapter = new TypeAdapter(getActivity(), type));
        //默认加载
        EventBus.getDefault().post("-1");
        //点击事件
        typeAdapter.setOnItemClickLitener(new TypeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //判断是否只是标签
                if (!"尺寸".equals(type.get(position)) && !"颜色".equals(type.get(position))
                        && !"图案".equals(type.get(position)) && !"宽度".equals(type.get(position)))

                    //不是标签,记录这个类型属于哪个标签下
                    for (int i = position; i >= 0; i--) {

                        if ("尺寸".equals(type.get(i))) {
                            ClothListFragment.label = "size";
                            BusinessClothListFragment.label = "size";
                            break;
                        } else if ("颜色".equals(type.get(i))) {
                            ClothListFragment.label = "color";
                            BusinessClothListFragment.label = "color";
                            break;
                        } else if ("图案".equals(type.get(i))) {
                            ClothListFragment.label = "pattern";
                            BusinessClothListFragment.label = "pattern";
                            break;
                        } else if ("宽度".equals(type.get(i))) {
                            ClothListFragment.label = "width";
                            BusinessClothListFragment.label = "width";
                            break;
                        }

                    }
                //不是标签,调用这个点击事件
                EventBus.getDefault().post(type.get(position));
            }
        });
    }

    @Override
    public void toMainActivity(ClothCategory clothCategory) {
        EventBus.getDefault().post(new Event.ClothTypeListEvent(clothCategory));
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getActivity(), "加载失败，请检查网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNone() {
        Toast.makeText(getActivity(), "未加载出数据！", Toast.LENGTH_SHORT).show();
    }
}
