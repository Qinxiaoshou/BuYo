package com.aode.buyoapp.LL.Homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.BusinessQueryAllProductsPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.IBusinessProductView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessProductDetailsActivity;
import com.aode.buyoapp.qinxiaoshou.activity.ConsumerProductDetailsActivity;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Business_HomePage extends Fragment implements IBusinessProductView {

    private View view;
    private RecyclerView recyclerView;

    //recyclerView
    private MyAdapter myAdapter;
    private MLManager mlManager;
    private List<commodity> commoditys;

    private String choose;
    private EditText editText;
    private String etsearch;
    private Button btn_search;

    //广告栏
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private ConvenientBanner convenientBanner;

    //交互层
    BusinessQueryAllProductsPresenter businessQueryAllProductsPresenter = new BusinessQueryAllProductsPresenter(this);


    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_business_homepage, container, false);
        //加载广告栏
        convenientBanner();
        //加载商品
        recyclerView();
        //scrollview的操作
        scroll();
        //搜索框
        search();
        return view;
    }

    public void search() {
        Spinner spinner_search = (Spinner) view.findViewById(R.id.spinner_search);
        editText = (EditText) view.findViewById(R.id.et_Search);
        btn_search = (Button) view.findViewById(R.id.btn_search);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                //清除输入框焦点,强制关闭系统输入法
                editText.clearFocus();
                InputMethodManager imm =
                        (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                return false;
            }
        });
        //OnClickListenerImpl按钮点击事件
        //下拉框选择事件
        spinner_search.setOnItemSelectedListener(new OnISListenerImpl());
        //搜索按钮事件
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etsearch = editText.getText().toString().trim();
                Toast.makeText(getContext(), "类型:" + choose + ",内容:" + etsearch, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void toMainActivity(final List<Cloth> clothlist) {
        System.out.println("加载商品" + clothlist);
        recyclerView = (RecyclerView) view.findViewById(R.id.homepage_recyclerView);
        //设置布局管理器,重写使之自适应
        recyclerView.setLayoutManager(mlManager = new MLManager(getActivity(), 2));
        //设置adapter
        recyclerView.setAdapter(myAdapter = new MyAdapter(getContext(), clothlist));
        myAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                //点击进入商品详情
                Intent intent = new Intent(getActivity(), BusinessProductDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cloth", clothlist.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getActivity(), "加载失败，请检查网络", Toast.LENGTH_SHORT).show();
    }

    //下拉框选择事件
    private class OnISListenerImpl implements OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            choose = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // TODO Auto-generated method stub
        }

    }

    public void scroll() {
        MyScrollview scrollView = (MyScrollview) view.findViewById(R.id.homepage_scrollview);
        //设置开始位置为顶部
        scrollView.smoothScrollTo(0, 0);

    }


    /**
     * 加载商家商品
     */
    public void recyclerView() {
        //获取商品信息
        businessQueryAllProductsPresenter.QueryAllProduct();
    }

    class MLManager extends GridLayoutManager {

        public MLManager(Context context, int spanCount) {
            super(context, spanCount);
        }

        public MLManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }

        private int[] mMeasuredDimension = new int[2];

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
            final int widthMode = View.MeasureSpec.getMode(widthSpec);
            final int heightMode = View.MeasureSpec.getMode(heightSpec);
            final int widthSize = View.MeasureSpec.getSize(widthSpec);
            final int heightSize = View.MeasureSpec.getSize(heightSpec);

            int width = 0;
            int height = 0;
            int count = getItemCount();
            int span = getSpanCount();
            for (int i = 0; i < count; i++) {
                measureScrapChild(recycler, i,
                        View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                        mMeasuredDimension);

                if (getOrientation() == HORIZONTAL) {
                    if (i % span == 0) {
                        width = width + mMeasuredDimension[0];
                    }
                    if (i == 0) {
                        height = mMeasuredDimension[1];
                    }
                } else {
                    if (i % span == 0) {
                        height = height + mMeasuredDimension[1];
                    }
                    if (i == 0) {
                        width = mMeasuredDimension[0];
                    }
                }
            }

            switch (widthMode) {
                case View.MeasureSpec.EXACTLY:
                    width = widthSize;
                case View.MeasureSpec.AT_MOST:
                case View.MeasureSpec.UNSPECIFIED:
            }

            switch (heightMode) {
                case View.MeasureSpec.EXACTLY:
                    height = heightSize;
                case View.MeasureSpec.AT_MOST:
                case View.MeasureSpec.UNSPECIFIED:
            }

            setMeasuredDimension(width, height);
        }

        private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                       int heightSpec, int[] measuredDimension) {
            if (position < getItemCount()) {
                try {
                    View view = recycler.getViewForPosition(0);//fix 动态添加时报IndexOutOfBoundsException
                    if (view != null) {
                        RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
                        int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                                getPaddingLeft() + getPaddingRight(), p.width);
                        int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                                getPaddingTop() + getPaddingBottom(), p.height);
                        view.measure(childWidthSpec, childHeightSpec);
                        measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                        measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
                        recycler.recycleView(view);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 广告栏
     */
    public void convenientBanner() {
        loadTestDatas();
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.cb_convenientBanner);

        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.login_point, R.drawable.login_point_selected})
                        //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
                /*      //设置点击监听事件
                .setOnItemClickListener((OnItemClickListener) this);*/
    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            //拿到传入的drawable文件反射获得variableName名称的属性
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void loadTestDatas() {
        //本地图片集合
        for (int position = 0; position < 5; position++) {
            localImages.add(getResId("buliao" + (position + 1), R.drawable.class));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.startTurning(3000);

    }

    @Override
    public void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

}
