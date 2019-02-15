package com.example.administrator.potato.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.activity.BaseActivity;
import com.example.administrator.potato.activity.ChangeCityActivity;
import com.example.administrator.potato.utils.ToastMessage;
import com.example.administrator.potato.utils.baidumap.BDLocationResult;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者 Administrator
 * 时间 2019/1/24
 */

public class WeatherFragment extends BaseFragment {

    //常量
    private static final String ARG_PARAM1 = "param1";
    @Bind(R.id.text)
    TextView text;
    private String mParam1;
    private View view;
    private String city;
    private String province;

    public static WeatherFragment newInstance(String param1) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initView() {
        if (TextUtils.isEmpty(BDLocationResult.city)) {//处理定位失败的情况
            showConfirmDialog("温馨提示", "系统定位失败，将使用默认的城市进行查询天气，如有需要请自行选择城市", new BaseActivity.ConfirmDialogInterface() {
                @Override
                public void onConfirmClickListener() {

                }

                @Override
                public void onCancelClickListener() {

                }
            });
            city = "成都";
            province = "四川";
            text.setText(String.format("%s[切换]", city));

        } else {
            text.setText(String.format("%s[切换]", BDLocationResult.city));
            city = BDLocationResult.city;
            province = BDLocationResult.province;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.text)
    public void onViewClicked() {
        gotoActivity(ChangeCityActivity.class);
    }
}
