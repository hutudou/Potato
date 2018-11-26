package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.BaseEvent;
import com.example.administrator.potato.utils.EventBusUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseEventBusActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_event_bus);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时清空延时任务
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void initView() {
        handler = new Handler();
        initToolBar(toolbar, "EventBus发送消息界面", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.buttonSendNormal, R.id.buttonSendStick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonSendNormal:
                //发送普通消息 如果不延时发送 如果消息发出组件尚未建立 则无法正常接受消息
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventBusUtil.getInstance().postSticky(new BaseEvent(1, "这是一条普通消息哦..."));
                    }
                }, 200);

                //使用平时方法启动活动
               /* Intent intentNormal = new Intent(mContext, ReceiveActivity.class);
                startActivity(intentNormal);*/

                //使用BaseActivity中的方法启动活动
                gotoActivity(ReceiveActivity.class);
                break;
            case R.id.buttonSendStick:
                //发送粘性消息 即使消息发出时组件没有建立 也仍然可以继续接受消息
                EventBusUtil.getInstance().postSticky(new BaseEvent(1, "这是一条粘性消息哦..."));
               /* Intent intentSticky = new Intent(mContext, ReceiveActivity.class);
                startActivity(intentSticky);*/
                gotoActivity(ReceiveActivity.class);
                break;
        }
    }
}
