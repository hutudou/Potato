package com.example.administrator.potato.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.potato.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者 Administrator
 * 时间 2019/1/24
 */

public class SecretFragment extends BaseFragment {

    //常量
    private static final String ARG_PARAM1 = "param1";
    @Bind(R.id.text)
    TextView text;
    private String mParam1;
    private View view;

    public static SecretFragment newInstance(String param1) {
        SecretFragment fragment = new SecretFragment();
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
        text.setText(mParam1);
        return view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
