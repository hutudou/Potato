package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.interfaces.ConfirmDialogInterface;
import com.example.administrator.potato.interfaces.DealWithPermission;
import com.example.administrator.potato.temp.BDLocationUtils;
import com.example.administrator.potato.utils.DateUtils;
import com.example.administrator.potato.utils.NumImageView;
import com.example.administrator.potato.utils.PermissionUtils;
import com.example.administrator.potato.utils.ToastMessage;
import com.hjq.permissions.XXPermissions;

import java.util.Timer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PracticeActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private int num = 0;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "练习代码界面", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final NumImageView myIv = findViewById(R.id.imageView);
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        num += 1;
                        myIv.setNum(num);
                    }
                }, 1000, 50);
            }
        });*/

    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.button1)
    public void onViewClicked() {
        showConfirmDialog("来自土豆的提示", "您确定要提交吗？", new ConfirmDialogInterface() {
            @Override
            public void onConfirmClickListener() {
                ToastMessage.toastSuccess("您点击了确定按钮!", true);
            }

            @Override
            public void onCancelClickListener() {
                ToastMessage.toastSuccess("您点击了取消按钮", true);
            }
        });
    }

    @OnClick(R.id.button2)
    public void onButton2Clicked() {

        if (isHasPermission()) {
            BDLocationUtils bdLocationUtils = new BDLocationUtils(this);
            bdLocationUtils.doLocation();//开启定位
            bdLocationUtils.mLocationClient.start();//开始定位
        } else {
            getPermission();
        }
    }

    private boolean isHasPermission() {
        return XXPermissions.isHasPermission(mContext, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS");
    }

    private void getPermission() {
        PermissionUtils.getPermission(this, new DealWithPermission() {
            @Override
            public void noPermission() {
                showConfirmDialog("温馨提示", "使用该功能需要开启某些功能，是否现在开启?", new ConfirmDialogInterface() {
                    @Override
                    public void onConfirmClickListener() {
                        XXPermissions.gotoPermissionSettings(mContext);
                    }

                    @Override
                    public void onCancelClickListener() {
                        ToastMessage.toastError("权限未获取，无法使用leak工具", true);
                    }
                });
            }
        }, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS");
       /* XXPermissions.with(this)
                .permission("android.permission.ACCESS_FINE_LOCATION")
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        ToastMessage.toastSuccess("权限获取成功", true);
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        showConfirmDialog("温馨提示", "使用该功能需要开启摄像头权限，是否现在立即去开启相机权限", new ConfirmDialogInterface() {
                            @Override
                            public void onConfirmClickListener() {
                                XXPermissions.gotoPermissionSettings(mContext);
                            }

                            @Override
                            public void onCancelClickListener() {
                                ToastMessage.toastError("权限未获取，无法使用百度地图的功能", true);
                            }
                        });
                    }
                });*/
    }


    @OnClick(R.id.button3)
    public void onButton3Clicked() {
        ToastMessage.toastSuccess("" + DateUtils.getLocalDate("yyyy年MM月dd日"), true);
    }

}
