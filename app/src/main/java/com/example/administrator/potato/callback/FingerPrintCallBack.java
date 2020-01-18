package com.example.administrator.potato.callback;

import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.widget.Toast;

import com.example.administrator.potato.application.MyApplication;

/**
 * @author potato
 * @date 2018/12/12
 *
 * 指纹验证回调接口
 */

public class FingerPrintCallBack extends FingerprintManagerCompat.AuthenticationCallback {

    /**
     * 验证出错
     *
     * @param errMsgId  id
     * @param errString 错误信息
     */
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        super.onAuthenticationError(errMsgId, errString);
        Toast.makeText(MyApplication.getContext(), "验证出错...", Toast.LENGTH_SHORT).show();
    }

    /**
     * 验证成功
     *
     * @param result 验证结果
     */
    @Override
    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Toast.makeText(MyApplication.getContext(), "验证成功...", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        super.onAuthenticationHelp(helpMsgId, helpString);
    }

    /**
     * 验证失败
     */
    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(MyApplication.getContext(), "验证失败...", Toast.LENGTH_SHORT).show();
    }

    /**
     * 指纹验证的结果回调
     */
    private interface IFingerPrintResult {

        void onSuccess();

        void onError();

        void onFali();
    }
}
