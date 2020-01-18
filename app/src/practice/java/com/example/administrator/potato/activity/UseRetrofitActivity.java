package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.interfaces.TestInterfaces;
import com.example.administrator.potato.request.RequestUrl;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author Administrator
 */
public class UseRetrofitActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.text)
    TextView text;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_retrofit);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (retrofit != null) {
            retrofit = null;
        }
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用Retrofit", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        request();
        requestHistory();
    }

    @Override
    protected void initData() {

    }

    //retrofit基本使用
    private void request() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl("http://www.baidu.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        TestInterfaces testInterfaces = retrofit.create(TestInterfaces.class);
        Call<String> result = testInterfaces.baidu("http://www.baidu.com/");
        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                text.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext, "与服务器连接失败...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //使用retrofit分析been类
    private void requestHistory() {
        retrofit = new Retrofit.Builder()
                .baseUrl(RequestUrl.TOADY_IN_HISTORY)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        TestInterfaces testInterfaces = retrofit.create(TestInterfaces.class);
        Call<String> todayInHistoryBeenCall = testInterfaces.getHistoryInfo(AppConstant.MOB_APP_KEY, "0404");
        todayInHistoryBeenCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("awei", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext, "与服务器连接失败...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
