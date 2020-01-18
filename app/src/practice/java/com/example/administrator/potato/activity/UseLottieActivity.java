package com.example.administrator.potato.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.administrator.potato.BuildConfig;
import com.example.administrator.potato.R;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 */
public class UseLottieActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.lottieAnimationView)
    LottieAnimationView lottieAnimationView;
    @Bind(R.id.textVersion)
    TextView textVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_lottie);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lottieAnimationView.cancelAnimation();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用lottie解析json动画", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //设置动画播放速度
        lottieAnimationView.setSpeed(2.0f);
        textVersion.setText(String.format("当前版本:    V %s", BuildConfig.VERSION_NAME));
        //监听动画状态
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                gotoActivity(MainActivity.class,false);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
