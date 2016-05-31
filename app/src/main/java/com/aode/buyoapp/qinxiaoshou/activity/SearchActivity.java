package com.aode.buyoapp.qinxiaoshou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.SearchPresenter;
import com.aode.buyoapp.LL.bean.Business;
import com.aode.buyoapp.LL.bean.Cloth;
import com.aode.buyoapp.LL.view.SearchView;
import com.aode.buyoapp.R;

import java.util.List;

/**
 * 布约app搜索界面
 *
 * @author 覃培周
 * @// FIXME: 2016/5/7
 */
public class SearchActivity extends AppCompatActivity implements SearchView {
    EditText editText;
    Button btn_right_text;
    public String choose;
    Spinner spinner_search;
    private ImageView iv_back;
    private String etsearch;

    SearchPresenter searchPresenter = new SearchPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityt_search);
        spinner_search = (Spinner) findViewById(R.id.spinner_search);
        editText = (EditText) findViewById(R.id.et_serch);
        btn_right_text = (Button) findViewById(R.id.btn_right_text);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        //返回键事件
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //下拉框选择事件
        spinner_search.setOnItemSelectedListener(new OnISListenerImpl());
        //搜索按钮事件
        btn_right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etsearch = editText.getText().toString().trim();
                Toast.makeText(getApplicationContext(), "类型:" + choose + ",内容:" + etsearch, Toast.LENGTH_SHORT).show();
                searchPresenter.Search();
            }
        });

    }

    @Override
    public String getKey() {
        return choose;
    }

    @Override
    public String getChooseTitle() {
        return etsearch;
    }

    @Override
    public void toMainClothActivity(List<Cloth> list) {
        if (!list.isEmpty())
            Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toMainBusinessActivity(List<Business> list) {
        if (!list.isEmpty())
            Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "搜索失败,请检查你的网络是否有效", Toast.LENGTH_SHORT).show();
    }

    //下拉框选择事件
    class OnISListenerImpl implements AdapterView.OnItemSelectedListener {
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

}






