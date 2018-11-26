package com.example.administrator.potato.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.service.TestService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseServiceActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_service);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用服务", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.buttonStartTestService, R.id.buttonStopTestService})
    public void onViewClicked(View view) {
        Intent intent = new Intent(mContext, TestService.class);
        switch (view.getId()) {
            case R.id.buttonStartTestService:
                //开启服务
                startService(intent);
                break;
            case R.id.buttonStopTestService:
                //关闭服务
                stopService(intent);
                break;
        }
    }
}
