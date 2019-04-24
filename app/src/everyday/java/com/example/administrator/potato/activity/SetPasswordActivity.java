package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.potato.R;
import com.example.administrator.potato.application.MyApplication;
import com.example.administrator.potato.bmobbeen.Person;
import com.example.administrator.potato.utils.MD5Utils;
import com.example.administrator.potato.utils.ToastMessage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class SetPasswordActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editPassword)
    EditText editPassword;
    @Bind(R.id.editAgainPassword)
    EditText editAgainPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "设置密码", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDialog("温馨提示", "现在退出的话需要重新获取验证码,你确定仍要退出吗?(每一个手机号一天只有五次获取验证码的机会)", new ConfirmDialogInterface() {
                    @Override
                    public void onConfirmClickListener() {
                        finish();
                    }

                    @Override
                    public void onCancelClickListener() {

                    }
                });
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.buttonSubmit)
    public void onViewClicked() {
        if (editPassword.getText().toString().length() < 6 || editPassword.getText().toString().length() > 15) {
            ToastMessage.toastWarn("密码应该在6至15位...", true);
            return;
        }
        if (!editPassword.getText().toString().equals(editAgainPassword.getText().toString())) {
            ToastMessage.toastWarn("两次密码应该一致...", true);
            return;
        }
        final Person personInsert = new Person();
        personInsert.setAccount(getIntent().getStringExtra("account"));
        personInsert.setPassword(MD5Utils.encode(editPassword.getText().toString()));
        personInsert.setIntroduce("欢迎使用Everyday,祝你玩得愉快!");
        personInsert.setNickName("EveryDay用户");
        personInsert.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {//提交到远程服务器成功
                    ToastMessage.toastSuccess("注册成功...", true);
                    Bundle bundle = new Bundle();
                    bundle.putString("account", getIntent().getStringExtra("account"));
                    gotoActivity(LoginActivity.class, bundle, true);
                } else {
                    ToastMessage.toastError("" + e.getLocalizedMessage(), true);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        showConfirmDialog("温馨提示", "现在退出的话需要重新获取验证码,你确定仍要退出吗?(每一个手机号一天只有五次获取验证码的机会)", new ConfirmDialogInterface() {
            @Override
            public void onConfirmClickListener() {
                finish();
            }

            @Override
            public void onCancelClickListener() {

            }
        });
    }
}
