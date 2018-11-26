package com.example.administrator.potato.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.administrator.potato.interfaces.ConfirmDialogInterface;
import com.example.administrator.potato.interfaces.DealWithPermission;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

/**
 * 作者:土豆
 * 创建日期:2018/8/21
 * 邮箱:1401552353@qq.com
 */

public class PermissionUtils {
    public static void getPermission(Activity activity, final DealWithPermission dealWithPermission, String... permissions){
        XXPermissions.with(activity)
                .permission(permissions)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        ToastMessage.toastSuccess("权限获取成功", true);
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                            dealWithPermission.noPermission();
                    }
                });
    }

    /**
     *  某权限是否已经获取
     * @param activity              调用方法所在的活动
     * @param permission            需要验证的权限
     * @return boolean              已经获取返回true 没有获取返回false
     */
    public static boolean isHasPermission(@NonNull Activity activity, @NonNull String permission){
        RxPermissions rxPermissions = new RxPermissions(activity);
        return rxPermissions.isGranted(permission);
    }

}
