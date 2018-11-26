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

    public static void get(@NonNull Context context, @NonNull String url, Map<String, String> map, @NonNull AbsCallback callable) {
        OkGo.<String>get(url)
                .tag(context)
                .params(map)
                .execute(callable);
    }
}
