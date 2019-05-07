package com.example.administrator.potato.utils.baidumap;

import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.example.administrator.potato.interfaces.BDLocationInterface;

/**
 * 作者:土豆
 * 创建日期:2018/9/6
 * 邮箱:1401552353@qq.com
 */

public class BDLocationListener extends BDAbstractLocationListener {

    private BDLocationInterface bdLocationInterface;

    public BDLocationListener(BDLocationInterface bdLocationInterface) {
        this.bdLocationInterface = bdLocationInterface;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        int errorCode = bdLocation.getLocType();
        if (errorCode == BDLocation.TypeNetWorkLocation) {
            //成功之后给BDLocationResult 确保在回调之前数据已经赋值
            //设置纬度
            BDLocationResult.latitude = bdLocation.getLatitude();
            //设置经度
            BDLocationResult.longitude = bdLocation.getLongitude();
            //设置详细地址
            BDLocationResult.address = bdLocation.getAddrStr();
            //设置当前定位省份
            BDLocationResult.province = bdLocation.getProvince();
            //设置当前定位城市
            BDLocationResult.city = bdLocation.getCity();
            //定位成功后将flag赋值为true 方便判断定位是否成功
            BDLocationResult.locationFlag = true;
            bdLocationInterface.onSuccess();
        } else {
            bdLocationInterface.onFail(errorCode);
        }
        Log.e("awei", "经度是" + BDLocationResult.longitude + "\n纬度是:" + BDLocationResult.latitude + "\n地址是:" + BDLocationResult.address);
    }
}
