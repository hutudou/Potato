package com.example.administrator.potato.utils.baidumap;

/**
 * 作者:土豆
 * 创建日期:2018/9/6
 * 邮箱:1401552353@qq.com
 */

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.potato.application.MyApplication;
import com.example.administrator.potato.interfaces.BDLocationInterface;

/**
 * 设置定位参数
 */
public class BDLocationParameter {
    public LocationClient locationClient = null;
    private BDLocationInterface bdLocationInterface;
    private BDLocationListener locationListener;

    public BDLocationParameter( BDLocationInterface bdLocationInterface) {
        this.bdLocationInterface = bdLocationInterface;
        locationListener = new BDLocationListener(bdLocationInterface);

    }

    public void setLocationParame() {
        //使用application的context以防止内存泄露
        locationClient = new LocationClient(MyApplication.getContext());

        LocationClientOption option = new LocationClientOption();
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        //可选，设置返回经纬度坐标类型，默认GCJ02
        //GCJ02：国测局坐标；
        //BD09ll：百度经纬度坐标；
        //BD09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标
        option.setCoorType("bd09ll");

        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效
        option.setScanSpan(0);

        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true
        option.setOpenGps(true);

        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
        option.setLocationNotify(false);

        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
        option.setIgnoreKillProcess(false);

        //可选，设置是否收集Crash信息，默认收集，即参数为false
        option.SetIgnoreCacheException(false);

        //可选，V7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位
//        option.setWifiCacheTimeOut(5 * 60 * 1000);

        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        option.setEnableSimulateGps(false);

        //是否获取地址
        option.setIsNeedAddress(true);
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(locationListener);
    }

    /**
     * 不使用定位时 需要注销资源 以防止造成内存泄露
     */
    public void release() {
        if (locationClient != null) {
            //停止定位
            locationClient.stop();
            //注销监听
            locationClient.unRegisterLocationListener(locationListener);
            locationClient = null;
        }
    }
}
