package com.example.administrator.potato.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.application.MyApplication;
import com.example.administrator.potato.utils.ToastMessage;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 */
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
            "功能九:webView的使用",
            "功能十:MPAndroidChart的使用",
            "功能十一:VideoView的使用",
            "功能十二:EventBus的使用",
            "功能十三:Notification的使用以及封装",
            "功能十四:okHttp的简单使用",
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
            "功能二十五:测试SharedPreferencesUtil",
            "功能二十六:PictureSelector框架的使用以及封装",
            "功能二十七:NumberPicker的使用",
            "功能二十八:手写签名",
            "功能二十九:测试原生GPS的准确性",
            "功能三十:安卓动画简单使用",
            "功能三十一:动态添加xml布局并设置不同id",
            "功能三十二:使用NiceSpinner插件",
            "功能三十三:使用时间线",
            "功能三十四:使用系统Spinner(自定义布局)",
            "功能三十五:使用表格插件",
            "功能三十六:text view字体大小的适配",
            "功能三十七:扫描本地的音乐文件",
            "功能三十八:使用自定义的TextView(可调整图片大小)",
            "功能三十九:使用数据库管理框架----room",
            "功能四十:自动连接指定的wifi",
            "功能四十一:Java使用线程池",
            "功能四十二:使用zing生成二维码"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "功能列表", false, null, true, R.menu.app_toolbar_menu, new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemRefresh:
                        ToastMessage.toastSuccess("刷新成功", true);
                        break;
                    case R.id.itemTitle:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        setListView();
    }

    @Override
    protected void initData() {
        String mac = getLocalMacAddressFromIp(this);
        ToastMessage.toastWarn("mac地址是:" + mac);
    }

    /**
     * 设置点击事件
     */
    private void setListView() {
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
                    //功能六:使用百度地图进行定位(仅获取经纬度)
                    case 6:
                        Intent intent5 = new Intent(mContext, UseBaiDuMapActivity.class);
                        startActivity(intent5);
                        break;
                    //功能七:CircleImageView+自定义view实现带角标的圆形图片
                    case 7:
                        Intent intent6 = new Intent(mContext, UseNumImageViewActivity.class);
                        startActivity(intent6);
                        break;
                    //功能八:tabLayout的使用9214-50=9164
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
                        Intent intent10 = new Intent(mContext, UseMPAndroidChartActivity.class);
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
                    //功能十四:okHttp的简单使用
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
                    //"功能二十六:PictureSelector框架的使用以及封装"
                    case 26:
                        Intent intent26 = new Intent(mContext, PictureSelectorActivity.class);
                        startActivity(intent26);
                        break;
                    //"功能二十七:NumberPicker的使用"
                    case 27:
                        Intent intent27 = new Intent(mContext, UseNumberPickerActivity.class);
                        startActivity(intent27);
                        break;
                    //"功能二十八:手写签名"
                    case 28:
                        Intent intent28 = new Intent(mContext, SignActivity.class);
                        startActivity(intent28);
                        break;
                    //功能二十九:使用原生gps进行定位
                    case 29:
                        Intent intent29 = new Intent(mContext, GetNativeLocationInfoActivity.class);
                        startActivity(intent29);
                        break;
                    //功能三十一:动态添加xml布局并设置不同id
                    case 31:
                        Intent intent30 = new Intent(mContext, DynamicAddLayoutActivity.class);
                        startActivity(intent30);
                        break;
                    //"功能三十二:使用NiceSpinner插件"
                    case 32:
                        Intent intent31 = new Intent(mContext, UseNiceSpinnerActivity.class);
                        startActivity(intent31);
                        break;
                    //功能三十三:使用时间线
                    case 33:
                        Intent intent32 = new Intent(mContext, UseTimeAxleActivity.class);
                        startActivity(intent32);
                        break;
                    //"功能三十四:使用系统Spinner(自定义布局)"
                    case 34:
                        Intent intent33 = new Intent(mContext, UseCustomSpinnerActivity.class);
                        startActivity(intent33);
                        break;
                    //"功能三十五:使用表格插件"
                    case 35:
                        Intent intent34 = new Intent(mContext, UseTableActivity.class);
                        startActivity(intent34);
                        break;
                    //功能三十六:text view字体大小的适配
                    case 36:
                        Intent intent35 = new Intent(mContext, AutoTextSizeActivity.class);
                        startActivity(intent35);
                        break;
                    //功能三十七:扫描本地的音乐文件
                    case 37:
                        Intent intent36 = new Intent(mContext, QueryLocalMusicActivity.class);
                        startActivity(intent36);
                        break;
                    //"功能三十八:使用自定义的TextView(可调整图片大小)"
                    case 38:
                        Intent intent37 = new Intent(mContext, CustomTextViewActivity.class);
                        startActivity(intent37);
                        break;
                    //"功能三十九:使用数据库管理框架----room"
                    case 39:
                        Intent intent38 = new Intent(mContext, UseRoomActivity.class);
                        startActivity(intent38);
                        break;
                    //功能四十:自动连接指定的wifi
                    case 40:
                        Intent intent39 = new Intent(mContext, WifiActivity.class);
                        startActivity(intent39);
                        break;
                    //功能四十一:Java线程池的使用
                    case 41:
                        break;
                    //"功能四十二:使用zing生成二维码"
                    case 42:
                        Intent intent41 = new Intent(mContext, UseZxingActivity.class);
                        startActivity(intent41);
                        break;
                    default:
                        break;

                }
            }
        });
    }

    public static String getLocalMacAddressFromIp(Context context) {
        String mac_s = "";
        try {
            byte[] mac;
            NetworkInterface ne = NetworkInterface.getByInetAddress(InetAddress.getByName(getLocalIpAddress()));
            mac = ne.getHardwareAddress();
            mac_s = byte2hex(mac);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mac_s;
    }

    /**
     * 获取本地IP
     *
     * @return string
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
        }

        return null;
    }

    public static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer(b.length);
        String stmp = "";
        int len = b.length;
        for (int n = 0; n < len; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                hs = hs.append("0").append(stmp);
            } else {
                hs = hs.append(stmp);
            }
        }
        return String.valueOf(hs);
    }

}
