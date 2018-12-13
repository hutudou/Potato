package com.example.administrator.potato.callback;

import android.accessibilityservice.FingerprintGestureController;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.widget.Toast;

import com.example.administrator.potato.application.MyApplication;

/**
 * 作者 Administrator
 * 时间 2018/12/12
 * <p>
 * 指纹验证回调接口
 */

public class FingerPrintCallBack extends FingerprintManagerCompat.AuthenticationCallback {

    //验证出错
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        super.onAuthenticationError(errMsgId, errString);
        Toast.makeText(MyApplication.getContext(), "验证出错...", Toast.LENGTH_SHORT).show();
    }

    //验证成功
    @Override
    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Toast.makeText(MyApplication.getContext(), "验证成功...", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        super.onAuthenticationHelp(helpMsgId, helpString);
    }

    //验证失败
    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(MyApplication.getContext(), "验证失败...", Toast.LENGTH_SHORT).show();
    }
}
