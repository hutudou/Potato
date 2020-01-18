package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.view.SignatureView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author potato
 */
public class SignActivity extends BaseActivity {

    @Bind(R.id.signatureView)
    SignatureView signatureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        signatureView.setPaintWidth(8);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.buttonClear, R.id.buttonReturn, R.id.buttonSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonClear:
                signatureView.clear();
                break;
            case R.id.buttonReturn:
                signatureView.goBack();
                break;
            case R.id.buttonSave:
                break;
            default:
                break;
        }
    }
}
