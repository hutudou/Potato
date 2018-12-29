package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.SharedPreferencesUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestSharedPreferencesUtilActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shared_preferences_util);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "测试SharedPreferencesUtil", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SharedPreferencesUtil.savaData("testString", "Hello Word");
        SharedPreferencesUtil.savaData("testInt", 666);
        SharedPreferencesUtil.savaData("testFloat", 66.66f);
        SharedPreferencesUtil.savaData("testLong", 666666L);
        SharedPreferencesUtil.savaData("testBoole", true);
    }

    @Override
    protected void initData() {
        text.setText(String.format("存储并读取String类型的值\n它的值是:%s" +
                        "\n存储并读取int类型的值\n它的值是:%s" +
                        "\n存储并读取float类型的值\n它的值是:%s" +
                        "\n存储并读取long类型的值\n它的值是:%s" +
                        "\n存储并读取boole类型的值\n它的值是:%s"
                , SharedPreferencesUtil.getData("testString", "error")
                , SharedPreferencesUtil.getData("testInt", -1)
                , SharedPreferencesUtil.getData("testFloat", -1f)
                , SharedPreferencesUtil.getData("testLong", -1L)
                , SharedPreferencesUtil.getData("testBoole", false)
        ));
    }
}
