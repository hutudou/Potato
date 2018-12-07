package com.example.administrator.potato.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.potato.application.MyApplication;
import com.example.administrator.potato.interfaces.ConfirmDialogInterface;
import com.example.administrator.potato.interfaces.DealWithPermission;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 作者:土豆
 * 创建日期:2018/8/21
 * 邮箱:1401552353@qq.com
 */

// 动态权限管理类
public class PermissionUtils {

    /**
     * 获取权限 可以是单个也可以是多个 但是每个权限得判断结果是单独抛出得
     *
     * @param activity         活动
     * @param iPermissionEvent 不同状态得回调结果 具体业务在调用处进行 以接口形式抛出
     * @param permissions      需要获取的权限组
     */
    public static void getPermission(Activity activity, final IPermissionEvent iPermissionEvent, String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions
                .requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {//权限已获取
                            iPermissionEvent.onAlreadyGet(permission);
                        } else if (permission.shouldShowRequestPermissionRationale) {//权限已拒绝 但不是永久拒绝
                            iPermissionEvent.onPartRefuse(permission);
                        } else {//权限已经永久拒绝
                            iPermissionEvent.onCompleteRefuse(permission);
                        }
                    }
                });
    }

    /**
     * 某权限是否已经获取
     *
     * @param activity   调用方法所在的活动
     * @param permission 需要验证的权限
     * @return boolean              已经获取返回true 没有获取返回false
     */
    public static boolean isHasPermission(@NonNull Activity activity, @NonNull String permission) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        return rxPermissions.isGranted(permission);
    }

    public interface IPermissionEvent {
        //权限已获取
        void onAlreadyGet(Permission permission);

        //权限本次拒绝 但是下次仍然可以获取
        void onPartRefuse(Permission permission);

        //权限已彻底拒绝
        void onCompleteRefuse(Permission permission);
    }

    public static boolean isNeedRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        } else {
            return false;
        }
    }
}
