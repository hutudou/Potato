package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.callback.FingerPrintCallBack;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author patato
 */
public class UserFingerprintActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textResult)
    TextView textResult;
    /**
     * 指纹管理器
     */
    private FingerprintManagerCompat manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_fingerprint);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用指纹验证", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        manager = FingerprintManagerCompat.from(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.buttonStart)
    public void onViewClicked() {
        manager.authenticate(null, 0, null, new FingerPrintCallBack(), null);
    }
}
