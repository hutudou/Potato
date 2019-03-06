package com.example.administrator.potato.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.potato.R;
import com.example.administrator.potato.bmobbeen.Secret;
import com.example.administrator.potato.utils.ToastMessage;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddSecretActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editSecretName)
    EditText editSecretName;
    @Bind(R.id.editSecretContent)
    EditText editSecretContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_secret);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_submit, menu);
        MenuItem item = menu.findItem(R.id.submit);
        SpannableString spannableString = new SpannableString(item.getTitle());
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spannableString.length(), 0);
        item.setTitle(spannableString);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "树洞", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }, true, R.menu.menu_submit, new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.submit:
                        if (TextUtils.isEmpty(editSecretContent.getText().toString())) {
                            ToastMessage.toastError("发布内容不能为空", true);
                            break;
                        }
                        Secret secret = new Secret();
                        secret.setSecretContent(editSecretContent.getText().toString());
                        if (TextUtils.isEmpty(editSecretName.getText().toString())) {
                            secret.setNickName("陌生人");
                        } else {
                            secret.setNickName(editSecretName.getText().toString());
                        }
                        secret.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    ToastMessage.toastSuccess("发表成功...", true);
                                    finish();
                                } else {
                                    ToastMessage.toastError("发表失败..." + e.getMessage(), true);
                                }
                            }
                        });
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }

}
