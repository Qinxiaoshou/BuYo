package com.aode.buyoapp.LL.Homepage;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Homepage.About.Person_Homepage_About;
import com.aode.buyoapp.LL.Homepage.AllCloth.Person_HomePage_AllCloth;
import com.aode.buyoapp.LL.Presenter.QuerySaleBestFourGoodsPresenter;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.UQuerySaleBestFourGoodsView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.business.BrowseBusinessActivity;
import com.aode.buyoapp.qinxiaoshou.activity.ConsumerProductDetailsActivity;
import com.aode.buyoapp.qinxiaoshou.activity.SearchActivity;
import com.aode.buyoapp.qinxiaoshou.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 布约app第二版首页界面
 *
 * @author 陈映苗
 * @// FIXME: 2016/5/6
 */
public class Person_HomePage2 extends Fragment implements ViewPager.OnPageChangeListener, UQuerySaleBestFourGoodsView {
    private ImageView iv;
    private RelativeLayout rl_changshang_browser, rl_homepage_aboutme, rl_homepage_allcloth;
    private LinearLayout ll;
    private TextView tv;
    private ViewPager viewPager;
    private boolean isStop = false; //是否停止子线程，默认不会停止
    private int previousEnabledPosition = 0; //前一个被选中的“点”的索引
    private List<ImageView> list = new ArrayList<ImageView>();
    private View view;
    private View view2;
    private List<Cloth> saleBestFourGoodsList;
    private LinearLayout.LayoutParams params;
    QuerySaleBestFourGoodsPresenter querySaleBestFourGoodsPresenter = new QuerySaleBestFourGoodsPresenter(this);
    private String[] imageDescriptionArray = {
            "五一特价，全国包邮",
            "高档织锦缎！！富贵花系列",
            "优质韩国布匹厂家直销",
            "国庆特价！！1米包邮！！",
            "布匹来袭，欧美风格",
    };

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        querySaleBestFourGoodsPresenter.QuerySaleBestFourGoods();
        view2 = inflater.inflate(R.layout.fragment_person_homepage2, container, false);

        init();

        //开启线程无限自动移动
        new Thread() {
            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(4000);    //每隔五秒钟，发送一条消息到主线程，更新viewpager的界面

                    //runOnUiThread此方法在主线程执行，也可以使用Handler
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int newIndex = viewPager.getCurrentItem() + 1;
                            viewPager.setCurrentItem(newIndex);
                        }
                    });
                }
            }
        }.start();
        return view2;
    }

    @Override
    public void onDestroy() {
        isStop = true;
        super.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
            querySaleBestFourGoodsPresenter.QuerySaleBestFourGoods();
    }

    private void init() {


        ImageView iv_search_img = (ImageView) view2.findViewById(R.id.iv_search_img);
        iv_search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        rl_changshang_browser = (RelativeLayout) view2.findViewById(R.id.rl_changshang_browser);
        //点击厂商一览进入厂商一览界面
        rl_changshang_browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BrowseBusinessActivity.class));
            }
        });
        //个人布匹大全
        rl_homepage_allcloth = (RelativeLayout) view2.findViewById(R.id.rl_homepage_allcloth);
        rl_homepage_allcloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Person_HomePage_AllCloth.class));
            }
        });

        rl_homepage_aboutme = (RelativeLayout) view2.findViewById(R.id.rl_homepage_aboutme);
        //点击关于我们进入关于我们界面
        rl_homepage_aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Person_Homepage_About.class));
            }
        });
        viewPager = (ViewPager) view2.findViewById(R.id.viewPager);
        ll = (LinearLayout) view2.findViewById(R.id.ll_point_group);
        tv = (TextView) view2.findViewById(R.id.tv_image_description);

        int[] imageResID = {R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4, R.drawable.ad5};
        for (int id : imageResID) {
            iv = new ImageView(getContext());
            iv.setBackgroundResource(id);
            list.add(iv);

            //每循环一次，添加一个点到LinearLayout中
            view = new View(getContext());
            view.setBackgroundResource(R.drawable.point_background);
            params = new LinearLayout.LayoutParams(10, 10);
            params.leftMargin = 10;
            view.setEnabled(false);
            view.setLayoutParams(params);
            ll.addView(view);   //向线性布局中添加"点"
        }
        viewPager.setAdapter(new MyAdapter());
        viewPager.addOnPageChangeListener(this);

        //初始化ViewPager的默认position为Integer.Max_value的一半
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % list.size());
        viewPager.setCurrentItem(index);    //设置当前viewpager选中的pager页,会默认触发OnPageChangeListener.onPageSelected
    }

    /**
     * page滑动的回调方法
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * page被选中的回调方法
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //取余后的索引
        int newPosition = position % list.size();

        //根据索引位置设置图片的描述
        tv.setText(imageDescriptionArray[newPosition]);

        //把上一个点设置为未选中
        ll.getChildAt(previousEnabledPosition).setEnabled(false);

        //根据索引设置哪一个点被选中
        ll.getChildAt(newPosition).setEnabled(true);

        previousEnabledPosition = newPosition;
    }

    /**
     * page滑动状态被改变的回调方法
     *
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //查询热销商品的方法
    //热销商品展示是渣到爆的代码，别笑！
    @Override
    public void QuerySaleBestFourGoodsToMainActivity(final List<Cloth> cloths) {
        System.out.println("查询热销商品成功");
        //热销四个商品数据配置
        try {

            ImageView iv_picture_01 = (ImageView) view2.findViewById(R.id.iv_picture_01);
            ImageView iv_picture_02 = (ImageView) view2.findViewById(R.id.iv_picture_02);
            ImageView iv_picture_03 = (ImageView) view2.findViewById(R.id.iv_picture_03);
            ImageView iv_picture_04 = (ImageView) view2.findViewById(R.id.iv_picture_04);


            TextView tv_goods_name01 = (TextView) view2.findViewById(R.id.tv_goods_name01);
            TextView tv_goods_name02 = (TextView) view2.findViewById(R.id.tv_goods_name02);
            TextView tv_goods_name03 = (TextView) view2.findViewById(R.id.tv_goods_name03);
            TextView tv_goods_name04 = (TextView) view2.findViewById(R.id.tv_goods_name04);

            TextView tv_good_descripe_text01 = (TextView) view2.findViewById(R.id.tv_good_descripe_text01);
            TextView tv_good_descripe_text02 = (TextView) view2.findViewById(R.id.tv_good_descripe_text02);
            TextView tv_good_descripe_text03 = (TextView) view2.findViewById(R.id.tv_good_descripe_text03);
            TextView tv_good_descripe_text04 = (TextView) view2.findViewById(R.id.tv_good_descripe_text04);


            new ImageLoader(cloths.get(0), iv_picture_01).resume();
            new ImageLoader(cloths.get(1), iv_picture_02).resume();
            new ImageLoader(cloths.get(2), iv_picture_03).resume();
            new ImageLoader(cloths.get(3), iv_picture_04).resume();
            tv_goods_name01.setText(cloths.get(0).getTitle());
            tv_goods_name02.setText(cloths.get(1).getTitle());
            tv_goods_name03.setText(cloths.get(2).getTitle());
            tv_goods_name04.setText(cloths.get(3).getTitle());

            tv_good_descripe_text01.setText("￥ "+cloths.get(0).getPrice());
            tv_good_descripe_text02.setText("￥ "+cloths.get(1).getPrice());
            tv_good_descripe_text03.setText("￥ "+cloths.get(2).getPrice());
            tv_good_descripe_text04.setText("￥ "+cloths.get(3).getPrice());

            iv_picture_01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     //跳转到商品详情
                    Intent intent = new Intent(getActivity(), ConsumerProductDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cloth", cloths.get(0));
                    System.out.println("cloth:&&&"+cloths.get(0));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            iv_picture_02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到商品详情
                    Intent intent = new Intent(getActivity(), ConsumerProductDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cloth", cloths.get(1));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            iv_picture_03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到商品详情
                    Intent intent = new Intent(getActivity(), ConsumerProductDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cloth", cloths.get(2));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            iv_picture_04.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到商品详情
                    Intent intent = new Intent(getActivity(), ConsumerProductDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cloth", cloths.get(3));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showQuerySaleBestFourGoodsFailedError() {
        System.out.println("查询热销商品失败");
        Toast.makeText(getContext(), "查询热销商品失败", Toast.LENGTH_SHORT).show();
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * 服用对象
         * true 复用对象
         * false 用的是object
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 销毁对象
         *
         * @param container
         * @param position  将要被销毁对象的索引位置
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position % list.size()));
        }

        /**
         * 初始化一个view对象
         *
         * @param container
         * @param position  将要被创建的对象的索引位置
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            //先把对象添加到viewpager中,再返回当前对象
            container.addView(list.get(position % list.size()));
            return list.get(position % list.size());
        }
    }
}