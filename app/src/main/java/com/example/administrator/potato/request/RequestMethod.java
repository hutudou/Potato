package com.example.administrator.potato.request;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import io.reactivex.annotations.NonNull;

/**
 * 不同格式的请求方法
 */

public class RequestMethod {

    /**
     * get请求上传map
     *
     * @param context  上下文对象
     * @param url      请求地址
     * @param map      map
     * @param callable 结果回调
     */
    public static void get(@NonNull Context context, @NonNull String url, Map<String, String> map, @NonNull AbsCallback callable) {
        OkGo.<String>get(url)
                .tag(context)
                .params(map)
                .execute(callable);
    }

    /**
     * post请求上传map
     *
     * @param context  上下文对象
     * @param url      请求地址
     * @param map      map
     * @param callable 结果回调
     */
    public static void post(Context context, String url, Map<String, String> map, @NonNull AbsCallback callable) {
        OkGo.<String>post(url)
                .tag(context)
                .params(map)
                .execute(callable);
    }

    /**
     * post请求上传json
     *
     * @param context    上下文对象
     * @param url        请求地址
     * @param jsonObject json
     * @param callable   结果回调
     */
    public static void post(Context context, String url, JSONObject jsonObject, @NonNull AbsCallback callable) {
        OkGo.<String>post(url)
                .tag(context)
                .upJson(jsonObject)
                .execute(callable);
    }
}
