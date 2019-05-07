package com.example.administrator.potato.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.bmobbeen.Person;
import com.example.administrator.potato.service.GetLocationService;
import com.example.administrator.potato.utils.MD5Utils;
import com.example.administrator.potato.utils.PermissionUtils;
import com.example.administrator.potato.utils.SharedPreferencesUtil;
import com.example.administrator.potato.utils.ToastMessage;
import com.tbruyelle.rxpermissions2.Permission;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editAccount)
    EditText editAccount;
    @Bind(R.id.editPassword)
    EditText editPassword;
    @Bind(R.id.buttonShowOrHide)
    Button buttonShowOrHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAllServices();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "Welcome to Use EveryDay", false, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent().hasExtra("account")) {
            editAccount.setText(getIntent().getStringExtra("account"));
        }
        //预设上一个登陆用户的账号
        if (!TextUtils.isEmpty((String) SharedPreferencesUtil.getData(AppConstant.ACCOUNT, ""))) {
            editAccount.setText((String) SharedPreferencesUtil.getData(AppConstant.ACCOUNT, ""));
        }
        //预设上一个登陆用户的密码
        if (!TextUtils.isEmpty((String) SharedPreferencesUtil.getData(AppConstant.PASSWORD, ""))) {
            editPassword.setText((String) SharedPreferencesUtil.getData(AppConstant.PASSWORD, ""));
        }
        buttonShowOrHide.setOnTouchListener(new View.OnTouchListener() {//监听按钮的按下 和 松开事件 按下时显示密码 松开是隐藏密码
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.getId() == R.id.buttonShowOrHide) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        if (PermissionUtils.isHasPermission(this, "android.permission.ACCESS_FINE_LOCATION")) {//取得定位权限后直接开启定位服务
            startAllServices();
        } else {
            PermissionUtils.getPermission(this, new PermissionUtils.IPermissionEvent() {
                @Override
                public void onAlreadyGet(Permission permission) {
                    startGetLocationServices();
                }

                @Override
                public void onPartRefuse(Permission permission) {
                    //开启gps权限
                    showConfirmDialog("温馨提示", "定位权限已被拒绝，需要手动开启权限,是否立刻前往开启定位服务?（具体步骤:点击\"权限\"---->选择\"定位\"---->选择\"允许\"）", new ConfirmDialogInterface() {
                        @Override
                        public void onConfirmClickListener() {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelClickListener() {

                        }
                    });
                }

                @Override
                public void onCompleteRefuse(Permission permission) {
                    //开启gps权限
                    showConfirmDialog("温馨提示", "定位权限已被永久拒绝，需要手动开启权限,是否立刻前往开启定位服务?（具体步骤:点击\"权限\"---->选择\"定位\"---->选择\"允许\"）", new ConfirmDialogInterface() {
                        @Override
                        public void onConfirmClickListener() {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelClickListener() {

                        }
                    });
                }
            }, "android.permission.ACCESS_FINE_LOCATION");
        }
    }

    @OnClick(R.id.buttonLogin)
    public void onViewClicked() {
        if (TextUtils.isEmpty(editAccount.getText().toString())) {
            ToastMessage.toastError("请输入手机号...", false);
            return;
        }
        if (TextUtils.isEmpty(editPassword.getText().toString())) {
            ToastMessage.toastError("请输入密码...", false);
            return;
        }
        showWaitDialog(this);
        //根据手机号查找数据库中是否存在该用户
        BmobQuery<Person> queryPerson = new BmobQuery<>();
        queryPerson.doSQLQuery("select * from Person where account='" + editAccount.getText().toString() + "'", new SQLQueryListener<Person>() {
            @Override
            public void done(BmobQueryResult<Person> bmobQueryResult, BmobException e) {
                hideWaitDialog();
                if (e == null) {
                    if (bmobQueryResult.getResults().size() != 0) {
                        if (bmobQueryResult.getResults().get(0).getPassword().equals(MD5Utils.encode(editPassword.getText().toString()))) {
                            //存储当前用户登陆信息
                            SharedPreferencesUtil.saveData(AppConstant.ACCOUNT, editAccount.getText().toString());
                            SharedPreferencesUtil.saveData(AppConstant.PASSWORD, editPassword.getText().toString());
                            SharedPreferencesUtil.saveData(AppConstant.NICKNAME, bmobQueryResult.getResults().get(0).getNickName());
                            SharedPreferencesUtil.saveData(AppConstant.INTRODUCE, bmobQueryResult.getResults().get(0).getIntroduce());
                            ToastMessage.toastSuccess("登陆成功", false);
                            gotoActivity(EveryDayMainActivity.class, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle(), false);
                        } else {
                            ToastMessage.toastError("手机号或者密码错误,请重试...", false);
                        }
                    } else {
                        ToastMessage.toastError("手机号或者密码错误,请重试...", false);
                    }
                } else {
                    ToastMessage.toastError("与服务器连接失败,原因是:" + e.getLocalizedMessage(), false);
                }
            }
        });
    }

    private void stopAllServices() {
        stopService(new Intent(mContext, GetLocationService.class));
    }

    //开启app中所有服务
    private void startAllServices() {
        startGetLocationServices();
    }

    private void startGetLocationServices() {
        Intent intent = new Intent(mContext, GetLocationService.class);
        startService(intent);
    }

    //找回密码
    @OnClick(R.id.textForgetPassWord)
    public void onTextForgetPassWord() {

    }

    //注册
    @OnClick(R.id.textRegister)
    public void onTextRegister() {
        gotoActivity(RegisterActivity.class, ActivityOptions.makeSceneTransitionAnimation(this).toBundle(), false);
    }
}
