package com.example.administrator.potato.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.ToastMessage;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseDialogActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_dialog);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用自定义dialog", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.button1, R.id.button2, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("默认样式dialog")
                        .setMessage("我是颜值担当!!!")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastMessage.toastSuccess("点击了确定键", true);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastMessage.toastSuccess("点击了取消键", true);

                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.button1:
                showConfirmDialog("来自土豆的提示", "这是第一个dialog", new ConfirmDialogInterface() {
                    @Override
                    public void onConfirmClickListener() {
                        Toast.makeText(mContext, "点击了第一个dialog的确认键", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelClickListener() {
                        Toast.makeText(mContext, "点击了第一个dialog的取消键", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.button2:
                showConfirmDialog("温馨提示", "这是第二个dialog！！", new ConfirmDialogInterface() {
                    @Override
                    public void onConfirmClickListener() {
                        ToastMessage.toastSuccess("这是第二个dialog的确认键", true);
                    }

                    @Override
                    public void onCancelClickListener() {
                        ToastMessage.toastSuccess("这是第二个dialog的取消键", true);
                    }
                });
                break;
        }
    }
}
