package com.aode.buyoapp.LL.Homepage.AllCloth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.LL.bean.Event;
import com.aode.buyoapp.LL.bean.Item;
import com.aode.buyoapp.R;

import de.greenrobot.event.EventBus;

public class ClothTypeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TypeAdapter typeAdapter;
    private View view;

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

        // 开启线程加载列表
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000); // 模拟延时
                    /* 发布事件，在后台线程发的事件
                    EventBus会根据post中实参的类型，去Map中查找对于的方法
                    */
                    EventBus.getDefault().post(new Event.ItemListEvent(Item.ITEMS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //接收消息
    public void onEventMainThread(final Event.ItemListEvent event) {
        //处理信息
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        //设置布局管理器,重写使之自适应
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        recyclerView.setAdapter(typeAdapter = new TypeAdapter(getActivity(), event.getItems()));
        //默认加载
        EventBus.getDefault().post(event.getItems().get(1));
        //点击事件
        typeAdapter.setOnItemClickLitener(new TypeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                EventBus.getDefault().post(event.getItems().get(position));
            }
        });
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        *//* Register
        让EventBus扫描当前类，把所有onEvent开头的方法记录下来.
        使用Map，Key为方法的参数类型，Value中包含我们的方法。
        *//*
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 开启线程加载列表
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000); // 模拟延时
                    *//* 发布事件，在后台线程发的事件
                    EventBus会根据post中实参的类型，去Map中查找对于的方法
                    *//*
                    EventBus.getDefault().post(new ItemListEvent(Item.ITEMS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //接收消息
    public void onEventMainThread(ItemListEvent event) {
        //处理信息
        //这样的列表的每一行都只有一行文字。
        setListAdapter(new MyListAdapter(getActivity(), R.layout.clothtype_item, event.getItems()));
        //一开始就先加载第一个内容
        EventBus.getDefault().post(getListView().getItemAtPosition(1));
    }

    //获取listview的点击事件
    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        //getListView().getItemAtPosition(position)的类型为Item类型;
        EventBus.getDefault().post(getListView().getItemAtPosition(position));
    }

    class MyListAdapter extends ArrayAdapter {
        private LayoutInflater mInflater;
        private int resource;
        private List<Item> items;


        public MyListAdapter(Context context, int resource, List<Item> items) {
            super(context, resource, items);
            this.mInflater = LayoutInflater.from(context);
            this.resource = resource;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder holder;
            if (convertView == null) {
                //若为null，没有已知的父视图，导致放进去的view类的android:layout_xyz不会去计算
                convertView = mInflater.inflate(resource, parent, false);
                holder = new MyViewHolder(convertView);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (MyViewHolder) convertView.getTag();//取出ViewHolder对象
            }
            if (!"花型".equals(items.get(position).getContent()) && !"底部".equals(items.get(position).getContent())) {
                holder.title.setBackgroundColor(Color.argb(253, 233, 232, 232));
                holder.title.setTextColor(Color.argb(255, 0, 0, 0));
                holder.title.setTextSize(20);
            }
            *//*getListView().setDivider(new ColorDrawable(Color.argb(255, 255, 255, 255)));
            getListView().setDividerHeight(5);*//*
            holder.title.setText(items.get(position).getContent());
            return convertView;
        }

        public final class MyViewHolder {
            public TextView title;

            public MyViewHolder(View view) {
                title = (TextView) view.findViewById(R.id.id_item_allCloth);
            }

        }
    }*/

}
