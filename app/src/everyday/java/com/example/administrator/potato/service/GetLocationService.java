package com.example.administrator.potato.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.potato.interfaces.BDLocationInterface;
import com.example.administrator.potato.utils.ToastMessage;
import com.example.administrator.potato.utils.baidumap.BDLocationParameter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者 Administrator
 * 时间 2019/2/12
 */

public class GetLocationService extends Service {
    private BDLocationParameter bdLocationParameter;
    private Disposable disposable;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //两分钟进行一次定位
        disposable = Flowable.interval(0, 2, TimeUnit.MINUTES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getLocation(aLong);
                    }
                });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (bdLocationParameter != null) {
            bdLocationParameter.release();
            bdLocationParameter = null;
        }
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //开始定位
    private void getLocation(final Long time) {
        bdLocationParameter = new BDLocationParameter(new BDLocationInterface() {
            @Override
            public void onSuccess() {//处理定位成功事件
                Log.e("awei", "****************************************");
                Log.e("awei", "*定位服务第" + (time + 1) + "次定位成功");
                Log.e("awei", "****************************************");
            }

            @Override
            public void onFail(int errorCode) {//处理定位失败事件
                ToastMessage.toastError("定位服务异常,其返回码是:" + errorCode, false);
            }
        });
        bdLocationParameter.setLocationParame();
        bdLocationParameter.locationClient.start();
    }
}
