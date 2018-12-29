package com.example.administrator.potato.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.potato.R;
import com.example.administrator.potato.application.MyApplication;
import com.example.administrator.potato.utils.PermissionUtils;
import com.example.administrator.potato.utils.ToastMessage;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String[] type = {

            "练习:练习代码时的Activity",
            "功能一:Toasty的使用和简单封装",
            "功能二:butterknife的使用",
            "功能三:popupWindow的使用",
            "功能四:自定义dialog的使用以及封装",
            "功能五:安卓6.0以上权限管理框架RxPermission的使用",
            "功能六:百度sdk定位的使用",
            "功能七:CircleImageView+自定义view实现带角标的圆形图片",
            "功能八:tabLayout的使用",
            "功能九:webview的使用",
            "功能十:MPAndroidChart的使用",
            "功能十一:VideoView的使用",
            "功能十二:EventBus的使用",
            "功能十三:Notification的使用以及封装",
            "功能十四:okhttp的简单使用",
            "功能十五:使用Lottie来解析json格式动画",
            "功能十六:SnackBar的使用以及封装",
            "功能十七:服务的使用",
            "功能十八:查找手机本地的word文档",
            "功能十九:使用多条件查询框架----DropDawnMenu",
            "功能二十:防崩溃框架Cockroach的使用",
            "功能二十一:安卓文件IO操作",
            "功能二十二:RxJava的使用",
            "功能二十三:使用指纹验证",
            "功能二十四:Retrofit的简单使用",
            "功能二十五:测试SharedPreferencesUtil"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PermissionUtils.getPermission(this, new PermissionUtils.IPermissionEvent() {
            @Override
            public void onAlreadyGet(Permission permission) {
                Toast.makeText(getApplicationContext(), "权限已经获取了哟", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPartRefuse(Permission permission) {
                Toast.makeText(getApplicationContext(), "权限已经被拒绝了哟", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCompleteRefuse(Permission permission) {
                Toast.makeText(getApplicationContext(), "权限已经被永久拒绝了哟", Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        initView();
        initData();
    }

    protected void initView() {
        initToolBar(toolbar, "功能列表", false, null, true, R.menu.app_toolbar_menu, new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemRefresh:
                        ToastMessage.toastSuccess("刷新成功", true);
                        break;
                }
                return true;
            }
        });
    }

    protected void initData() {
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(MyApplication.getContext(), android.R.layout.simple_list_item_1, type);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //练习代码使用的Activity
                    case 0:
                        Intent intentPractice = new Intent(MyApplication.getContext(), PracticeActivity.class);
                        startActivity(intentPractice);
                        break;
                    //功能一:Toasty的使用和简单封装
                    case 1:
                        Intent intent0 = new Intent(mContext, UseToastyActivity.class);
                        startActivity(intent0);
                        break;
                    //功能二:butterKnife的使用
                    case 2:
                        Intent intent1 = new Intent(mContext, UseButterKnifeActivity.class);
                        startActivity(intent1);
                        break;
                    //功能三:popupWindow的使用
                    case 3:
                        Intent intent2 = new Intent(mContext, PopupWindowActivity.class);
                        startActivity(intent2);
                        break;
                    //功能四:自定义dialog的使用以及封装
                    case 4:
                        Intent intent3 = new Intent(mContext, UseDialogActivity.class);
                        startActivity(intent3);
                        break;
                    //功能五:安卓6.0以上权限管理框架RxPermission的使用
                    case 5:
                        Intent intent4 = new Intent(mContext, UseRxPermissionsActivity.class);
                        startActivity(intent4);
                        break;
                    case 6:
                        Intent intent5 = new Intent(mContext, UseBaiDuMapActivity.class);
                        startActivity(intent5);
                        break;
                    //功能七:CircleImageView+自定义view实现带角标的圆形图片
                    case 7:
                        Intent intent6 = new Intent(mContext, UseNumImageViewActivity.class);
                        startActivity(intent6);
                        break;
                    //功能八:tabLayout的使用
                    case 8:
                        Intent intent8 = new Intent(mContext, TabLayoutActivity.class);
                        startActivity(intent8);
                        break;
                    //"功能九:webview的使用"
                    case 9:
                        Intent intent9 = new Intent(mContext, UseWebViewActivity.class);
                        startActivity(intent9);
                        break;
                    //功能十:MPAndroidChart的使用
                    case 10:
                        Intent intent10 = new Intent(mContext, UseMPAndroidChart.class);
                        startActivity(intent10);
                        break;
                    //功能十一:MPAndroidChart的使用
                    case 11:
                        Intent intent11 = new Intent(mContext, UseVideoViewActivity.class);
                        startActivity(intent11);
                        break;
                    //功能十二:EventBus的使用
                    case 12:
                        Intent intent12 = new Intent(mContext, UseEventBusActivity.class);
                        startActivity(intent12);
                        break;
                    //功能十三:Notification的使用以及封装
                    case 13:
                        Intent intent13 = new Intent(mContext, UseNotificationActivity.class);
                        startActivity(intent13);
                        break;
                    //功能十四:okhttp的简单使用
                    case 14:
                        Intent intent14 = new Intent(mContext, UseOkHttpActivity.class);
                        startActivity(intent14);
                        break;
                    //功能十五:使用Lottie来解析json格式动画
                    case 15:
                        Intent intent15 = new Intent(mContext, UseLottieActivity.class);
                        startActivity(intent15);
                        break;
                    //"功能十六:SnackBar的使用以及封装"
                    case 16:
                        Intent intent16 = new Intent(mContext, UseSnackBarActivity.class);
                        startActivity(intent16);
                        break;
                    //"功能十七:服务的使用"
                    case 17:
                        Intent intent17 = new Intent(mContext, UseServiceActivity.class);
                        startActivity(intent17);
                        break;
                    //功能十八:查找手机本地的word文档"
                    case 18:
                        Intent intent18 = new Intent(mContext, ChooseDocActivity.class);
                        startActivity(intent18);
                        break;
                    //"功能十九:使用多条件查询框架----DropDawnMenu"
                    case 19:
                        Intent intent19 = new Intent(mContext, UseDropDawnMenuActivity.class);
                        startActivity(intent19);
                        break;
                    //"功能二十:防崩溃框架Cockroach的使用"
                    case 20:
                        Intent intent20 = new Intent(mContext, UseCockroachActivity.class);
                        startActivity(intent20);
                        break;
                    //"功能二十一:安卓文件IO操作"
                    case 21:
                        Intent intent21 = new Intent(mContext, FileIOActivity.class);
                        startActivity(intent21);
                        break;
                    //"功能二十二:RxJava的使用"
                    case 22:
                        Intent intent22 = new Intent(mContext, RxJavaActivity.class);
                        startActivity(intent22);
                        break;
                    //"功能二十三:使用指纹验证"
                    case 23:
                        Intent intent23 = new Intent(mContext, UserFingerprintActivity.class);
                        startActivity(intent23);
                        break;
                    //"功能二十四:Retrofit的简单使用"
                    case 24:
                        Intent intent24 = new Intent(mContext, UseRetrofitActivity.class);
                        startActivity(intent24);
                        break;
                    //"功能二十五:测试SharedPreferencesUtil"
                    case 25:
                        Intent intent25 = new Intent(mContext, TestSharedPreferencesUtilActivity.class);
                        startActivity(intent25);
                        break;
                }
            }
        });
    }


}
