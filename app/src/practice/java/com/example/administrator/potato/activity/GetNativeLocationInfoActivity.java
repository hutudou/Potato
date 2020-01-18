package com.example.administrator.potato.activity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.view.ExpandLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 */
public class GetNativeLocationInfoActivity extends BaseActivity {
    @Bind(R.id.testExpand)
    ExpandLinearLayout testExpand;
    private LocationManager lManager;
    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_native_location_info);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //活动销毁时注销管理器
        if (lManager != null) {
            lManager = null;
        }
    }

    @Override
    protected void initView() {
       /* GetIdUtil getIdUtil = new GetIdUtil(mContext);
        text.setText(getIdUtil.getBaseStationInformation());*/
        testExpand=new ExpandLinearLayout(mContext);
    }

    @Override
    protected void initData() {
    }
   /* @SuppressLint("MissingPermission")
    private String getLngAndLat(Context context) {
        double latitude = 0.0;
        double longitude = 0.0;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  //从gps获取经纬度
            @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            } else {//当GPS信号弱没获取到位置的时候又从网络获取
//                return getLngAndLatWithNetwork();
                Toast.makeText(mContext,"location位置信息为0，说明gps没有定到位",Toast.LENGTH_SHORT).show();
            }
        } else {    //从网络获取经纬度
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }
        return longitude + "," + latitude;
    }
    LocationListener locationListener = new LocationListener() {

        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
        }
    };*/
}
