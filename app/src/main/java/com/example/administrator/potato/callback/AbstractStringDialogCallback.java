package com.example.administrator.potato.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.base.Request;

/**
 * 展示等待框的网络请求
 *
 * @author potato
 */

public abstract class AbstractStringDialogCallback extends StringCallback {
    /**
     * 需要显示的dialog
     */
    private ProgressDialog progressDialog;

    protected AbstractStringDialogCallback(Activity activity) {
        initDialog(activity);
    }

    /**
     * 初始化dialog
     *
     * @param activity 需要显示dialog的活动
     */
    private void initDialog(Activity activity) {
        progressDialog = new ProgressDialog(activity);
        //设置无标题
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置手指点击dialog不消失
        progressDialog.setCanceledOnTouchOutside(false);
        //设置dialog的内容
        progressDialog.setMessage("正在全力加载中...");
        //设置dialog样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        //dialog不为空且不在展示的状态才显示
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        //dialog不为空且在展示的状态才隐藏
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
