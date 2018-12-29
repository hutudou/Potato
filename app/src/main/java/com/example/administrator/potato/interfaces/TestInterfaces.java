package com.example.administrator.potato.interfaces;

import com.example.administrator.potato.been.TodayInHistoryBeen;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 作者 Administrator
 * 时间 2018/12/25
 * <p>
 * 测试retrofit2
 */

public interface TestInterfaces {
    @GET
    Call<String> baidu(@Url String url);

    @GET("/")
    Call<String> getHistoryInfo(@Query("key") String key, @Query("day") String day);
}
