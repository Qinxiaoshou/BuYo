package com.aode.buyoapp.qinxiaoshou;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Listener.BSearchListener;
import com.aode.buyoapp.LL.Presenter.BusinessSearchPresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.view.IBusinessSearchView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.activity.BusinessChooseBusinessAndPermissionActivity;
import com.aode.buyoapp.qinxiaoshou.adapter.SearchAdapter;
import com.aode.buyoapp.qinxiaoshou.model.Bean;
import com.aode.buyoapp.qinxiaoshou.view.MySearchView;

import java.util.ArrayList;
import java.util.List;


/**
 * 店家搜索其他店家的界面
 *
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
public class BusinessSearchOtherBusinessPagerActivity extends Activity implements MySearchView.SearchViewListener ,IBusinessSearchView{
    BusinessSearchPresenter businessSearchPresenter = new BusinessSearchPresenter(this);
    /**
     * 搜索结果列表view
     */
    private ListView lvResults;
    private String name;
    private List<Business> businessList ;
    private List<Business> businessList2;
    /**
     * 搜索view
     */
    private MySearchView searchView;

    /**
     * 热搜框列表adapter
     */
   /* private ArrayAdapter<String> hintAdapter;*/

    /**
     * 自动补全列表adapter
     */
    private ArrayAdapter<String> autoCompleteAdapter;

    /**
     * 搜索结果列表adapter
     */
    private SearchAdapter resultAdapter;


    /**
     * 热搜版数据
     */
    private List<String> hintData;

    /**
     * 搜索过程中自动补全数据
     */
    private List<String> autoCompleteData;


    /**
     * 默认提示框显示项的个数
     */
    private static int DEFAULT_HINT_SIZE = 4;

    /**
     * 提示框显示项的个数
     */
    private static int hintSize = DEFAULT_HINT_SIZE;
    /**
     * 返回键
     */
    private ImageView iv_back;
    /**
     * 设置提示框显示项的个数
     *
     * @param hintSize 提示框显示个数
     */
    public static void setHintSize(int hintSize) {
        BusinessSearchOtherBusinessPagerActivity.hintSize = hintSize;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.business_search_otherbusiness_search_layout);
        iv_back = (ImageView) findViewById(R.id.search_btn_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initViews();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        lvResults = (ListView) findViewById(R.id.main_lv_search_results);
        searchView = (MySearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener((MySearchView.SearchViewListener) this);

        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int position, long l) {
                view.setOnClickListener(new View.OnClickListener() {  //监听列表条目信息跳转的控件
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(BusinessSearchOtherBusinessPagerActivity.this, BusinessChooseBusinessAndPermissionActivity.class);
                        intent.putExtra("bId",businessList.get(position).getId());  //传递商家id
                        startActivity(intent);

                    }
                });
            }
        });
    }


    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {
        if (autoCompleteData == null) {
            //初始化
            autoCompleteData = new ArrayList<>(hintSize);
        } else {
            // 根据text 获取目标商家信息
            autoCompleteData.clear();

        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, autoCompleteData);
        } else {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取搜索结果data和adapter
     */
    private void getResultData(String text) {
        if (businessList2 == null) {
            businessList2 = businessList;
        } else {
            businessList2.clear();
            for (int i = 0; i < businessList.size(); i++) {
                if (businessList.get(i).getName().contains(text.trim())) {
                    businessList2.add(businessList.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new SearchAdapter(this, businessList2, R.layout.business_search_otherbusiness__item_bean_list_content);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 当搜索框 文本改变时 触发的回调 ,更新自动补全数据
     *
     * @param text
     */
    @Override
    public void onRefreshAutoComplete(String text) {
        //更新数据
        getAutoCompleteData(text);
    }

    /**
     * 点击搜索键时edit text触发的回调
     *
     * @param text
     */
    @Override
    public void onSearch(String text) {
        name = text;
        //更新result数据
        businessSearchPresenter.Search();

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void toMainActivity(List<Business> businessList) {
        System.out.println("我联网了");
        this.businessList = businessList;

        getResultData(name);
        lvResults.setVisibility(View.VISIBLE);
        //第一次获取结果 还未配置适配器
        if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
            lvResults.setAdapter(resultAdapter);
        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getApplication(),"搜索失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNo() {
        Toast.makeText(getApplication(),"搜索不到",Toast.LENGTH_SHORT).show();
    }
}
