package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.interfaces.ConfirmDialogInterface;
import com.example.administrator.potato.temp.BDLocationUtils;
import com.example.administrator.potato.utils.DateUtils;
import com.example.administrator.potato.utils.ToastMessage;

import java.util.Timer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PracticeActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private int num = 0;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "练习代码界面", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button1)
    public void onViewClicked() {
        showConfirmDialog("来自土豆的提示", "您确定要提交吗？", new ConfirmDialogInterface() {
            @Override
            public void onConfirmClickListener() {
                ToastMessage.toastSuccess("您点击了确定按钮!", true);
            }

            @Override
            public void onCancelClickListener() {
                ToastMessage.toastSuccess("您点击了取消按钮", true);
            }
        });
    }

    @OnClick(R.id.button2)
    public void onButton2Clicked() {

    }


    @OnClick(R.id.button3)
    public void onButton3Clicked() {
        ToastMessage.toastSuccess("" + DateUtils.getLocalDate("yyyy年MM月dd日"), true);
    }

}
