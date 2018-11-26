package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.ToastMessage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseToastyActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_toasty);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected void initData() {

    }

    protected void initView() {
        initToolBar(toolbar, "使用toasty框架", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.buttonSuccess, R.id.buttonError, R.id.buttonNormal, R.id.buttonWarn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonSuccess:
                ToastMessage.toastSuccess("这是成功的消息", true);
                break;
            case R.id.buttonError:
                ToastMessage.toastError("这是错误的消息", true);
                break;
            case R.id.buttonNormal:
                ToastMessage.toastNormal("这是一般消息");
                break;
            case R.id.buttonWarn:
                ToastMessage.toastWarn("这是警告消息", true);
                break;
        }
    }
}
