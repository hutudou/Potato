package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.potato.R;

import java.util.Observable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.button1)
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用RxJava", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    //button1中使用rxjava为逐步创建的方式
    @OnClick(R.id.button1)
    public void onViewClicked() {
        //创建被观察者
        io.reactivex.Observable<String> observable = io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int i = 0; i < 4; i++) {
                    //发送普通事件
                    e.onNext("线程已经休眠了" + (i + 1) + "秒");
                    Thread.sleep(1000);
                    if (i == 2) {
                        //发送Complete事件 此事件被观察者接收后 此后事件不再接收
                        e.onComplete();
                    }
                }
            }
        });
        //创建观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                button1.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "发生错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(mContext, "接收完毕", Toast.LENGTH_SHORT).show();
            }
        };
        //建立订阅关系
        observable.subscribe(observer);
    }
}
