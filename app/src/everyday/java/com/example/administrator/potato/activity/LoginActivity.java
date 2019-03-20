package com.example.administrator.potato.activity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.potato.R;
import com.mob.MobSDK;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editAccount)
    EditText editAccount;
    @Bind(R.id.editPassword)
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "Welcome to Use EveryDay", false, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.buttonLogin)
    public void onViewClicked() {
        //先直接跳转主activity
        gotoActivity(EveryDayMainActivity.class, ActivityOptions.makeSceneTransitionAnimation(this).toBundle(), false);
    }

    //找回密码
    @OnClick(R.id.textForgetPassWord)
    public void onTextForgetPassWord() {
    }

    //注册
    @OnClick(R.id.textRegister)
    public void onTextRegister() {
        gotoActivity(RegisterActivity.class, ActivityOptions.makeSceneTransitionAnimation(this).toBundle(), false);

    }
}
