package com.aode.buyoapp.LL.Personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.aode.buyoapp.LL.Presenter.UserLoginOutPresenter;
import com.aode.buyoapp.LL.view.IUserLoginOutView;
import com.aode.buyoapp.R;
import com.aode.buyoapp.qinxiaoshou.ConsumerOrderListActivity;
import com.aode.buyoapp.qinxiaoshou.activity.ConsumerPermissionPageActivity;


public class Person_Personal extends Fragment implements View.OnClickListener, IUserLoginOutView {
    private View view;
    private ImageView iv_message, iv_order, iv_limit, iv_changePassword;
    private ImageButton btn_login_out;

    private onLoginOutListener listener;

    UserLoginOutPresenter userLoginOutPresenter = new UserLoginOutPresenter(this);

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_personal, container, false);
        init();
        return view;
    }

    public void init() {
        iv_message = (ImageView) view.findViewById(R.id.iv_message);
        iv_order = (ImageView) view.findViewById(R.id.iv_order);
        iv_limit = (ImageView) view.findViewById(R.id.iv_limit);
        iv_changePassword = (ImageView) view.findViewById(R.id.iv_changePassword);
        btn_login_out = (ImageButton) view.findViewById(R.id.btn_login_out);
        iv_message.setOnClickListener(this);
        iv_order.setOnClickListener(this);
        iv_limit.setOnClickListener(this);
        iv_changePassword.setOnClickListener(this);
        btn_login_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_message:
                startActivity(new Intent(getActivity(), Person_Message.class));
                break;
            case R.id.iv_order:
                //个人订单页面
                startActivity(new Intent(getActivity(), ConsumerOrderListActivity.class));
                break;
            case R.id.iv_limit:
                //个人可看到的库存量商品
                startActivity(new Intent(getActivity(), ConsumerPermissionPageActivity.class));
                break;
            case R.id.iv_changePassword:
                //个人修改密码
                startActivity(new Intent(getActivity(), Person_password_update.class));
                break;
            case R.id.btn_login_out:
                //注销
                userLoginOutPresenter.LoginOut();
                break;
        }
    }

    //fragment和activity交互接口
    public interface onLoginOutListener {
        public void onLoginOut();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (onLoginOutListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void toMainActivity() {
        listener.onLoginOut();

        Toast.makeText(getActivity(), "注销成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getActivity(), "注销失败", Toast.LENGTH_SHORT).show();
    }
}