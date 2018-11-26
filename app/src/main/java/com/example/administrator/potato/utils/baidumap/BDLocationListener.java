package com.example.administrator.potato.utils.baidumap;

import android.util.Log;

import com.baidu.location.Address;
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

    public BDLocationListener(BDLocationInterface bdLocationInterface){
        this.bdLocationInterface=bdLocationInterface;
    }
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        int errorCode = bdLocation.getLocType();
        if(errorCode==BDLocation.TypeNetWorkLocation){
            //成功之后给BDLocationResult 确保在回调之前数据已经赋值
            //得到纬度
            BDLocationResult.LATITUDE=bdLocation.getLatitude();
            //得到经度
            BDLocationResult.LONGITUDE = bdLocation.getLongitude();
            //得到详细地址
            BDLocationResult.ADDRESS=bdLocation.getAddrStr();
            bdLocationInterface.onSuccess();
        }else {
            bdLocationInterface.onFail();
        }


        Log.d("awei","经度是"+ BDLocationResult.LONGITUDE+"\n纬度是:"+BDLocationResult.LATITUDE+"\n地址是:"+BDLocationResult.ADDRESS);

    }
}
