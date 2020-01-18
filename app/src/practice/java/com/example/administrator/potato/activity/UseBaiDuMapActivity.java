package com.example.administrator.potato.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.potato.R;
import com.example.administrator.potato.interfaces.BDLocationInterface;
import com.example.administrator.potato.utils.PermissionUtils;
import com.example.administrator.potato.utils.baidumap.BDLocationParameter;
import com.example.administrator.potato.utils.baidumap.BDLocationResult;
import com.tbruyelle.rxpermissions2.Permission;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author potato
 */
public class UseBaiDuMapActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private BDLocationParameter bdLocationParameter;
    @Bind(R.id.textResult)
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_bai_du_map);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        if (bdLocationParameter != null) {
            bdLocationParameter.release();
            bdLocationParameter = null;
        }
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用百度地图进行定位", true, new View.OnClickListener() {
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
        //确定是否获得了定位权限
        if (PermissionUtils.isHasPermission(UseBaiDuMapActivity.this, "android.permission.ACCESS_FINE_LOCATION")) {
            startLocation();
        } else {
            PermissionUtils.getPermission(this, new PermissionUtils.IPermissionEvent() {
                @Override
                public void onAlreadyGet(Permission permission) {
                    startLocation();
                }

                @Override
                public void onPartRefuse(Permission permission) {
                    Toast.makeText(mContext, "权限已被拒绝，请开启权限后才能使用定位服务...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCompleteRefuse(Permission permission) {
                    //开启gps权限
                    showConfirmDialog("温馨提示", "定位权限已被永久拒绝，需要手动开启权限,是否立刻前往开启定位服务?（具体步骤:点击\"选项\"---->选择\"定位\"---->选择\"允许\"）", new IConfirmDialogInterface() {
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

    /**
     * 开始定位
     */
    private void startLocation() {
        bdLocationParameter = new BDLocationParameter(new BDLocationInterface() {
            @Override
            public void onSuccess() {//处理定位成功事件
                textResult.setText(String.format("经度是:%s\n纬度是:%s\n地址是:%s", BDLocationResult.longitude, BDLocationResult.latitude, BDLocationResult.address));
            }

            @Override
            public void onFail(int errorCode) {//处理定位失败事件
                textResult.setText("定位失败啦");
            }
        });
        bdLocationParameter.setLocationParam();
        bdLocationParameter.locationClient.start();
    }

}
