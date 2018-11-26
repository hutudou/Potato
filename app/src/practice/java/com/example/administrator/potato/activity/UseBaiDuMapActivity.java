package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.interfaces.BDLocationInterface;
import com.example.administrator.potato.utils.PermissionUtils;
import com.example.administrator.potato.utils.ToastMessage;
import com.example.administrator.potato.utils.baidumap.BDLocationParameter;
import com.example.administrator.potato.utils.baidumap.BDLocationResult;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        bdLocationParameter.release();
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
        if (PermissionUtils.isHasPermission(UseBaiDuMapActivity.this, "android.permission.ACCESS_FINE_LOCATION")) {
            ToastMessage.toastSuccess("权限已经获取了", true);
        } else {
            ToastMessage.toastError("权限没有获取", true);
            return;
        }
        bdLocationParameter = new BDLocationParameter(new BDLocationInterface() {
            @Override
            public void onSuccess() {//处理定位成功事件
                textResult.setText(String.format("经度是:%s\n纬度是:%s\n地址是:%s", BDLocationResult.LONGITUDE, BDLocationResult.LATITUDE, BDLocationResult.ADDRESS));

            }

            @Override
            public void onFail() {//处理定位失败事件
                textResult.setText("定位失败啦");

            }
        });
        bdLocationParameter.setLocationParame();
        bdLocationParameter.locationClient.start();
    }

}
