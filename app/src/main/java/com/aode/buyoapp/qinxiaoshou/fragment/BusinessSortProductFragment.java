package com.aode.buyoapp.qinxiaoshou.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aode.buyoapp.R;


/**
 * 商品分类fragment
 * @author 覃培周
 * @// FIXME: 2016/4/7
 */
@SuppressLint("ValidFragment")
public class BusinessSortProductFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.business_sort_product_content, null);
	}
}
