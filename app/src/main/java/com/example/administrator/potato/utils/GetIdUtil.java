package com.example.administrator.potato.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * 作者 Administrator
 * 时间 2019/9/9
 *
 *获取基站信息
 */

public class GetIdUtil {
    private Context mContext;
    private TelephonyManager mTelephonyManager;

    public GetIdUtil(Context context) {
        this.mContext = context;
    }

    /**
     * 获取 基站 信息
     * @return
     */
    public String getBaseStationInformation() {
        if (mTelephonyManager == null) {
            mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        }
        // 返回值MCC + MNC （注意：电信的mnc 对应的是 sid）
        String operator = mTelephonyManager.getNetworkOperator();
        int mcc = 460;
        int mnc = 1;
        if (operator != null && operator.length() > 3) {
            mcc = Integer.parseInt(operator.substring(0, 3));
            mnc = Integer.parseInt(operator.substring(3));
        }

             // 获取邻区基站信息
        @SuppressLint("MissingPermission") List<NeighboringCellInfo> infos = mTelephonyManager.getNeighboringCellInfo();
        StringBuffer sb = new StringBuffer("总数 : " + infos.size() + "\n");
            // 根据邻区总数进行循环
        for (NeighboringCellInfo info1 : infos) {
            // 取出当前邻区的LAC
            sb.append(" LAC : " + info1.getLac());
            // 取出当前邻区的CID
            sb.append("\n CID : " + info1.getCid());
            // 获取邻区基站信号强度
            sb.append("\n BSSS : " + (-113 + 2 * info1.getRssi()) + "\n");
        }
        int type = mTelephonyManager.getNetworkType();

        Toast.makeText(mContext, "type:= " + type, Toast.LENGTH_LONG).show();
        //需要判断网络类型，因为获取数据的方法不一样
        // 电信cdma网
        if (type == TelephonyManager.NETWORK_TYPE_CDMA
                || type == TelephonyManager.NETWORK_TYPE_1xRTT
                || type == TelephonyManager.NETWORK_TYPE_EVDO_0
                || type == TelephonyManager.NETWORK_TYPE_EVDO_A
                || type == TelephonyManager.NETWORK_TYPE_EVDO_B
                || type == TelephonyManager.NETWORK_TYPE_LTE) {
            @SuppressLint("MissingPermission") CdmaCellLocation cdma = (CdmaCellLocation) mTelephonyManager.getCellLocation();
            if (cdma != null) {
                sb.append(" MCC = " + mcc);
                sb.append("\n cdma.getBaseStationLatitude()" + cdma.getBaseStationLatitude() / 14400 + "\n"
                        + "cdma.getBaseStationLongitude() " + cdma.getBaseStationLongitude() / 14400 + "\n"
                        + "cdma.getBaseStationId()(cid)  " + cdma.getBaseStationId()
                        + "\n  cdma.getNetworkId()(lac)   " + cdma.getNetworkId()
                        + "\n  cdma.getSystemId()(mnc)   " + cdma.getSystemId());
            } else {
                sb.append("can not get the CdmaCellLocation");
            }
            // 移动和联通GSM网
        } else if (type == TelephonyManager.NETWORK_TYPE_GPRS
                || type == TelephonyManager.NETWORK_TYPE_EDGE
                || type == TelephonyManager.NETWORK_TYPE_HSDPA
                || type == TelephonyManager.NETWORK_TYPE_UMTS
                || type == TelephonyManager.NETWORK_TYPE_LTE) {
            @SuppressLint("MissingPermission") GsmCellLocation gsm = (GsmCellLocation) mTelephonyManager.getCellLocation();
            if (gsm != null) {
                //移动联通 cid
                sb.append("  gsm.getCid()(cid)   " + gsm.getCid() + "  \n "
                        //移动联通 lac
                        + "gsm.getLac()(lac) " + gsm.getLac() + "  \n "
                        + "gsm.getPsc()  " + gsm.getPsc());
            } else {
                sb.append("can not get the GsmCellLocation");
            }
        } else if (type == TelephonyManager.NETWORK_TYPE_UNKNOWN) {
            Toast.makeText(mContext, "电话卡不可用！", Toast.LENGTH_LONG).show();
        }

        Log.d("spp", "mTelephonyManager.getNetworkType(); " + mTelephonyManager.getNetworkType());
        Log.i("awei", " 获取邻区基站信息:" + sb.toString());
        return sb.toString();
    }
}
