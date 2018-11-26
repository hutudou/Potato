package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.potato.R;
import com.example.administrator.potato.application.MyApplication;
import com.example.administrator.potato.utils.NotificationUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseNotificationActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_notification);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用Notification", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.buttonSendNormal, R.id.buttonSendCustom, R.id.buttonClearAllNotification})
    public void onViewClicked(View view) {
        NotificationUtil notificationUtil = new NotificationUtil(mContext, "1", "测试普通通知");
        switch (view.getId()) {
            case R.id.buttonSendNormal:
                notificationUtil.sendNormalNotification(-1, -1, "大声说出你的梦想", "Hello Word", 10);
                break;
            case R.id.buttonSendCustom:
                break;
            case R.id.buttonClearAllNotification:
                if (notificationUtil.clearAllNotification()) {
                    Toast.makeText(MyApplication.getContext(), "清除成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
