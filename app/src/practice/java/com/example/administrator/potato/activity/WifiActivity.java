package com.example.administrator.potato.activity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.ToastMessage;
import com.example.administrator.potato.utils.WifiAutoConnectManager;

/**
 * @author potato
 * @date 2020/5/15
 */
public class WifiActivity extends BaseActivity {
    /**
     * wifi管理器
     */
    private WifiManager wifiManager;
    /**
     * wifi自动连接管理器
     */
    private WifiAutoConnectManager autoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        autoManager = new WifiAutoConnectManager(wifiManager);
        autoManager.connect("HANDU", "Hdkj@0017hd;", WifiAutoConnectManager.WifiCipherType.WIFICIPHER_WPA, new WifiAutoConnectManager.IWifiStatus() {
            @Override
            public void connectionSuccess() {
                ToastMessage.toastSuccess("wifi连接成功...");
            }
        });
    }

    @Override
    protected void initData() {

    }
}
