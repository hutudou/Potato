package com.example.administrator.potato.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.administrator.potato.BuildConfig;
import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.TextViewShowWordOneByOne;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {
    private static final String welcomeWord = "了解每一天,发现每一天,记录每一天";
    //倒计时的总时间
    private static final int TIME_COUNT_TOTAL_TIME = 9000;
    //倒计时的间隔时间
    private static final int TIME_COUNT_TOTAL_TIME_INTERVAL = 1000;
    //textView逐字显示的间隔时间
    private static final int SHOW_WORD_ONE_BY_ONE_TIME = 200;
    @Bind(R.id.textVersion)
    TextView textVersion;
    @Bind(R.id.textSkip)
    TextView textSkip;
    @Bind(R.id.lottieAnimationView)
    LottieAnimationView lottieAnimationView;
    @Bind(R.id.textWordShow)
    TextView textWordShow;
    private TimeCount timeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timeCount != null) {
            timeCount.cancel();
        }
        lottieAnimationView.cancelAnimation();
    }

    @Override
    protected void initView() {
        //逐字显示textView
        TextViewShowWordOneByOne showWordOneByOne = new TextViewShowWordOneByOne(textWordShow, welcomeWord, SHOW_WORD_ONE_BY_ONE_TIME, true);
        showWordOneByOne.startTv(0);
        timeCount = new TimeCount(TIME_COUNT_TOTAL_TIME, TIME_COUNT_TOTAL_TIME_INTERVAL);
        timeCount.start();
        lottieAnimationView.setSpeed(2.0f);
        textVersion.setText(String.format("当前版本:    V %s", BuildConfig.VERSION_NAME));
        //监听动画状态
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                try {
                    //根据flavor选择进入不同的页面
                    if (BuildConfig.FLAVOR.equals(BuildConfig.practice)) {
                        Class clazz = Class.forName("com.example.administrator.potato.activity.MainActivity");
                        gotoActivity(clazz);
                    }
                    if (BuildConfig.FLAVOR.equals(BuildConfig.everyday)) {
                        Class clazz = Class.forName("com.example.administrator.potato.activity.EveryDayMainActivity");
                        gotoActivity(clazz);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
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

    @OnClick(R.id.textSkip)
    public void onViewClicked() {
        //点击跳过的时候 直接结束动画 跳转活动
        lottieAnimationView.cancelAnimation();
    }

    class TimeCount extends CountDownTimer {

        TimeCount(long totalTime, long timeInterval) {
            super(totalTime, timeInterval);
        }

        @Override
        public void onTick(long l) {
            textSkip.setText(String.format("跳过%d", l / TIME_COUNT_TOTAL_TIME_INTERVAL));
        }

        @Override
        public void onFinish() {
        }


    }
}
