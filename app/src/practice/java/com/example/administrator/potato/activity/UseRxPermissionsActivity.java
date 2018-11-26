package com.example.administrator.potato.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.ToastMessage;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class UseRxPermissionsActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usexxpermissions);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @SuppressLint("CheckResult")
    private void getPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            /**
                             * 当获取多项权限时 这里是一项项获取的 可以根据permission.name来控制每一次获取的权限是哪一个
                             */
                            ToastMessage.toastSuccess(permission.name + "权限已经成功获取了", true);
                            Log.d("awei", permission.name + "权限已经成功获取了");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            ToastMessage.toastWarn(permission.name + "权限获取失败，下次仍然可以继续获取", true);
                            Log.d("awei", permission.name + "权限获取失败，下次仍然可以继续获取");
                        } else {
                            ToastMessage.toastError(permission.name + "权限被永久拒绝", true);
                            Log.d("awei", permission.name + "权限被永久拒绝");
                        }
                    }
                });
    }

    @Override
    protected void initView() {
    initToolBar(toolbar, "使用PxPermission", true, new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        getPermission();

    }
}
