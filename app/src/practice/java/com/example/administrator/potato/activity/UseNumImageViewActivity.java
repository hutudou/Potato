package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.NumImageView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UseNumImageViewActivity extends BaseActivity {
    Timer timer;
    NumImageView view;
    NumImageView view1;
    NumImageView view2;
    private static int num = 0;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_num_image_view);
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
        initToolBar(toolbar, "带角标的圆形imageview", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        timer = new Timer();
        view = findViewById(R.id.imageView);
        view1 = findViewById(R.id.imageView1);
        view2 = findViewById(R.id.imageView2);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        num += 1;
                        view.setNum(num);
                        view1.setNum(num);
                        view2.setNum(num);
                    }
                });

            }
        }, 0, 50);
    }

    @Override
    protected void initData() {

    }
}
