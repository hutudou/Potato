package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.ToastMessage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseSnackBarActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.coordinatorCustom)
    CoordinatorLayout coordinatorCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_snack_bar);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用SnackBar并封装", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.buttonNormal)
    public void onViewClicked() {
        Snackbar.make(toolbar, "这是系统的snackBar", Snackbar.LENGTH_INDEFINITE)
                .setAction("你瞅啥", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastMessage.toastWarn("瞅你咋地", true);
                    }
                })
                .show();
    }

    @OnClick(R.id.buttonCustom)
    public void onButtonCustomClicked() {
        showSnackBar(coordinatorCustom, "这是我们自己封装的snackBar", false, "再瞅一个试试", new ISnackBarClickEvent() {
            @Override
            public void onSnackBarClickEvent() {
                ToastMessage.toastWarn("试试就试试", true);
            }
        });
    }
}
