package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.potato.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseCockroachActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_cockroach);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用防崩溃框架Cockroach", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.buttonTypeOne, R.id.buttongTypeTwo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonTypeOne:
                String[] testString = {"1", "2", "3"};
                Toast.makeText(mContext, "" + testString[testString.length], Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttongTypeTwo:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String[] testString = {"1", "2", "3"};
                        Log.d("awei", "" + testString[testString.length]);
                    }
                }).start();
                break;
        }
    }
}
