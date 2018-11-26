package com.example.administrator.potato.callback;

import android.util.JsonReader;
import android.util.Log;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/11/2.
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
       /* ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        T data = null;
        Gson gson = new Gson();
        com.google.gson.stream.JsonReader jsonReader = new com.google.gson.stream.JsonReader(body.charStream());
        //如果服务器端返回的是list 集合 则使用type方式进行解析
        if (type != null) {
            try {
                data = gson.fromJson(jsonReader, type);
            }catch (Exception e){
                Log.d("awei","json解析异常");
            }
        }
        //其他格式均直接解析
        if (clazz != null) {
            try {
                data = gson.fromJson(jsonReader, clazz);
            }catch (Exception e){
                Log.d("awei","json解析异常,详细原因是:"+e.toString());
            }
        }*/
//        return data;
        return null;
    }
}
