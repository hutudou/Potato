package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.potato.R;
import com.example.administrator.potato.application.MyApplication;
import com.example.administrator.potato.utils.DateUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseButterKnifeActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private int year = 2000;
    private int month = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_butter_knife);
        ButterKnife.bind(this);
        initView();
        initData();
        register();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void register() {
    }

    protected void initData() {
    }

    protected void initView() {
        initToolBar(toolbar, "使用ButterKnife", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Toast.makeText(this, "你点击了按钮一", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:

                Toast.makeText(this, "你点击了按钮二", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(this, "你点击了按钮三", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                //使用applicationContext以防止内存泄漏
                Toast.makeText(MyApplication.getContext(), "" + year + "年" + month + "月有" + "" + DateUtils.getEndDayOfMonth(year, month) + "天", Toast.LENGTH_SHORT).show();
                //ToastMessage.toastSuccess("2018年13月的天数是:"+DateUtils.getEndDayofMonth(2018,13),true);
                year++;
                month++;
                break;
        }
    }

}
