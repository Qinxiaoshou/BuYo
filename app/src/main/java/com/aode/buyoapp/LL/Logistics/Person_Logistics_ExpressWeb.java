package com.aode.buyoapp.LL.Logistics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aode.buyoapp.R;

/**
 * Created by LiLei on 2016/5/14.Go.
 * 物流页面-快递官网
 */
public class Person_Logistics_ExpressWeb extends Fragment implements View.OnClickListener {
    private View view;
    private RelativeLayout rl_logistics_shunfeng, rl_logistics_huitong, rl_logistics_yuantong, rl_logistics_yunda, rl_logistics_shentong;
    private Intent intent;
    private Uri uri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_logistics_web, container, false);
        init();
        return view;
    }

    public void init() {
        rl_logistics_shunfeng = (RelativeLayout) view.findViewById(R.id.rl_logistics_shunfeng);
        rl_logistics_huitong = (RelativeLayout) view.findViewById(R.id.rl_logistics_huitong);
        rl_logistics_yuantong = (RelativeLayout) view.findViewById(R.id.rl_logistics_yuantong);
        rl_logistics_yunda = (RelativeLayout) view.findViewById(R.id.rl_logistics_yunda);
        rl_logistics_shentong = (RelativeLayout) view.findViewById(R.id.rl_logistics_shentong);
        rl_logistics_shunfeng.setOnClickListener(this);
        rl_logistics_huitong.setOnClickListener(this);
        rl_logistics_yuantong.setOnClickListener(this);
        rl_logistics_yunda.setOnClickListener(this);
        rl_logistics_shentong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_logistics_shunfeng:
                uri = Uri.parse("http://www.sf-express.com/cn/sc/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.rl_logistics_huitong:
                uri = Uri.parse("http://www.htky365.com/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.rl_logistics_yuantong:
                uri = Uri.parse("http://www.yto.net.cn/gw/index/index.html");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.rl_logistics_yunda:
                uri = Uri.parse("http://www.yundaex.com/cn/index.php");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.rl_logistics_shentong:
                uri = Uri.parse("http://www.sto.cn/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

        }
    }
}