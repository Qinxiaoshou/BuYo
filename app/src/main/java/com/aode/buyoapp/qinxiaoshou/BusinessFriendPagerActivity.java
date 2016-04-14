package com.aode.buyoapp.qinxiaoshou;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.aode.buyoapp.LL.Home_business;
import com.aode.buyoapp.LL.Presenter.BusinessFriendShowPresenter;
import com.aode.buyoapp.LL.Presenter.BusinessFriendToMeShowPresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.view.IBusinessFriendToMeView;
import com.aode.buyoapp.LL.view.IBusinessFriendView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessCheckWhoHaveProductPermissionFragment;
import com.aode.buyoapp.qinxiaoshou.fragment.BusinessSettingBusinessFriendFragment;
import java.util.ArrayList;
import java.util.List;


/**
 * 友好商家界面
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessFriendPagerActivity extends AppCompatActivity implements IBusinessFriendView ,IBusinessFriendToMeView{
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private List<Business> bList;   //本店设置过权限的商家
    private List<Business> toBList;  //别的商家对我设置过权限的集合
    Boolean page1 = false;
    Boolean page2 =false;
    Adapter adapter = new Adapter(getSupportFragmentManager());
    BusinessFriendShowPresenter businessFriendShowPresenter = new BusinessFriendShowPresenter(this);
    BusinessFriendToMeShowPresenter businessFriendToMeShowPresenter = new BusinessFriendToMeShowPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_list_viewpager_layout);
        //获得本店已设置权限商家数据
        businessFriendToMeShowPresenter.getFriendToMe();
        businessFriendShowPresenter.getFriend();
        toolbar = (Toolbar) findViewById(R.id.appbar);
        toolbar.setNavigationIcon(R.drawable.left_arrow);//设置导航栏图标
        toolbar.setTitle("友好商家");
        //返回键
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    /**
     * 创建创建2个页面跳转的页面碎片
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
            viewPager.setAdapter(adapter);

    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


    @Override
    public String getId() {
        return Home_business.business.getId();
    }

    @Override
    public String ToMegetId() {
        return Home_business.business.getId();
    }

    @Override
    public void toFriendToMeMainActivity(List<Business> businesses) {
        this.toBList = businesses;
        adapter.addFragment(new BusinessCheckWhoHaveProductPermissionFragment(toBList), "拥有权限的商品");
        this.page1 = true;
    }



    @Override
    public void toMainActivity(List<Business> businesses) { //我设置过权限的商家集合
        this.bList = businesses;
        System.out.println("1111111111111--》"+businesses);
        adapter.addFragment(new BusinessSettingBusinessFriendFragment(bList), "我的友好商家");
        this.page2 = true;
        init();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplicationContext(), "查找友好商家失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNo() {
        Toast.makeText(getApplicationContext(), "查找不到友好商家", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ToMeshowFailedError() {
        Toast.makeText(getApplicationContext(), "查找拥有权限的商品失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ToMeshowNo() {
        Toast.makeText(getApplicationContext(), "查找不到拥有权限的商家", Toast.LENGTH_SHORT).show();
    }


    /**
     * 获得数据后就启动viewpager
     */
    public void init(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            //获得本店设置权限商家
            setupViewPager(viewPager);
        }
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabTextColors(Color.GRAY, 0xFFFF4D00);
        tabLayout.setupWithViewPager(viewPager);

    }

}
