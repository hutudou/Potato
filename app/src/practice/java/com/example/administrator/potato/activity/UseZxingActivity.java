package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.CodeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用zxing生成二维码
 *
 * @author potato
 * @date 2020/5/21
 */
public class UseZxingActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_zxing);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("使用Zxing生成二维码");
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        //生成二维码之前需要进行判空操作
        if (!TextUtils.isEmpty(editText.getText().toString())) {
            imageView.setImageBitmap(CodeUtils.createQrcode(editText.getText().toString()));
        }
    }
}
