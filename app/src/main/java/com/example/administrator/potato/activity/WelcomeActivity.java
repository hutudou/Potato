package com.example.administrator.potato.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.administrator.potato.BuildConfig;
import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.TextViewShowWordOneByOne;
import com.example.administrator.potato.utils.ToastMessage;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
    private Disposable disposable;

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
        cancelAnimation();
    }

    @Override
    protected void initView() {
        disposable = Flowable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        int restTime = (int) (7 - aLong);
                        textSkip.setText(String.format("跳过%d", restTime));
                        if (restTime == 0) {
                            cancelAnimation();
                        }
                    }
                });
        //逐字显示textView
        TextViewShowWordOneByOne showWordOneByOne = new TextViewShowWordOneByOne(textWordShow, welcomeWord, SHOW_WORD_ONE_BY_ONE_TIME, true);
        showWordOneByOne.startTv(0);
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

    //关闭动画 取消订阅
    private void cancelAnimation() {
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
        }
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @OnClick(R.id.textSkip)
    public void onViewClicked() {
        //点击跳过的时候 直接结束动画 跳转活动
        cancelAnimation();
    }

}
