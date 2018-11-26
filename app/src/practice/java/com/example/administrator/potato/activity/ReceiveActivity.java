package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.BaseEvent;
import com.example.administrator.potato.utils.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiveActivity extends BaseActivity implements EventBusUtil.IEventBus {

    @Bind(R.id.textReceive)
    TextView textReceive;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregister();
    }

    @Override
    protected void initView() {
        register();
        initToolBar(toolbar, "EventBus接受消息界面", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void register() {
        EventBusUtil.getInstance().registerEventBus(this);
    }

    @Override
    public void unregister() {
        EventBusUtil.getInstance().unregisterEventBus(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    @Override
    public void onEvent(BaseEvent event) {
        if (event.data != null) {
            //0表示一般消息
            if (event.eventType == 0) {
                textReceive.setText(String.format("%s", event.data));
            }
            //1表示粘性消息
            if (event.eventType == 1) {
                textReceive.setText(String.format("%s", event.data));
            }
        }
    }
}
