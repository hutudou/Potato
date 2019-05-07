package com.example.administrator.potato.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.activity.BaseActivity;
import com.example.administrator.potato.activity.ChangeCityActivity;
import com.example.administrator.potato.bmobbeen.Person;
import com.example.administrator.potato.callback.StringDialogCallback;
import com.example.administrator.potato.holder.BaseRecyclerViewHolder;
import com.example.administrator.potato.javabeen.WeatherBeen;
import com.example.administrator.potato.recyclerView.adapter.CommonRecyclerViewAdapter;
import com.example.administrator.potato.request.RequestMethod;
import com.example.administrator.potato.request.RequestUrl;
import com.example.administrator.potato.utils.ToastMessage;
import com.example.administrator.potato.utils.baidumap.BDLocationResult;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 作者 Administrator
 * 时间 2019/1/24
 */

public class WeatherFragment extends BaseFragment {

    //常量
    private static final String ARG_PARAM1 = "param1";
    private static final int CHANGE_CITY = 0;
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.textTemperature)
    TextView textTemperature;
    @Bind(R.id.textWeather)
    TextView textWeather;
    @Bind(R.id.textAirQuality)
    TextView textAirQuality;
    @Bind(R.id.textWind)
    TextView textWind;
    @Bind(R.id.textCold)
    TextView textCold;
    @Bind(R.id.textSport)
    TextView textSport;
    @Bind(R.id.textClothes)
    TextView textClothes;
    @Bind(R.id.textAirHumidity)
    TextView textAirHumidity;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    private String mParam1;
    private View view;
    private String city;
    private String province;
    private CommonRecyclerViewAdapter<WeatherBeen> adapter;
    private Handler handler;

    public static WeatherFragment newInstance(String param1) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == ChangeCityActivity.CONFIRM_CITY) {
                province = data.getStringExtra("province");
                city = data.getStringExtra("area");
                if (TextUtils.isEmpty(province) || TextUtils.isEmpty(city)) {
                    ToastMessage.toastError("更换城市出错,将使用默认定位开始查询天气...", true);
                    province = "四川";
                    city = "成都";
                }
                text.setText(String.format("%s[切换]", city));
                request();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView() {
        handler = new Handler();

        if (TextUtils.isEmpty(BDLocationResult.city)) {//处理定位失败的情况
            showConfirmDialog("温馨提示", "系统定位失败,将使用默认的城市进行查询天气,请检查应用是否已经获取了定位权限...", new BaseActivity.ConfirmDialogInterface() {
                @Override
                public void onConfirmClickListener() {

                }

                @Override
                public void onCancelClickListener() {

                }
            });
            city = "成都";
            province = "四川";
            text.setText(String.format("%s[切换]", city));

        } else {
            text.setText(String.format("%s[切换]", BDLocationResult.city));
            city = BDLocationResult.city;
            province = BDLocationResult.province;
        }
        adapter = new CommonRecyclerViewAdapter<WeatherBeen>(getActivity(), R.layout.recycler_weather_item) {
            @Override
            public void convert(BaseRecyclerViewHolder holder, WeatherBeen item, int position) {
                //星期
                TextView textView = holder.getView(R.id.textWeek);
                textView.setText(item.getWeek());
                //日期
                textView = holder.getView(R.id.textDate);
                textView.setText(item.getDate());
                //天气状况
                textView = holder.getView(R.id.textWeather);
                textView.setText(item.getDayTime());
                //温度
                textView = holder.getView(R.id.textTemperature);
                textView.setText(item.getTemperature());
            }
        };
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        request();
    }


    private void request() {
        Map<String, String> map = new HashMap<>();
        map.put("key", AppConstant.MOB_APP_KEY);
        map.put("city", city.replace("市", ""));
        map.put("province", province.replace("省", ""));
        RequestMethod.get(getActivity(), RequestUrl.Query_Weather, map, new StringDialogCallback(getActivity()) {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    JSONArray jsonArray = jsonObject.optJSONArray("result");
                    if (jsonArray.length() > 0) {
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        textTemperature.setText(jsonObject1.optString("temperature"));
                        textWeather.setText(jsonObject1.optString("weather"));
                        textAirQuality.setText(String.format("空气质量:%s ", jsonObject1.optString("airCondition")));
                        textWind.setText(String.format("风力情况:%s ", jsonObject1.optString("wind")));
                        textCold.setText(String.format("感冒指数:%s ", jsonObject1.optString("coldIndex")));
                        textSport.setText(String.format("运动指数:%s ", jsonObject1.optString("exerciseIndex")));
                        textClothes.setText(String.format("穿衣指数:%s ", jsonObject1.optString("dressingIndex")));
                        textAirHumidity.setText(String.format("空气%s", jsonObject1.optString("humidity")));
                        JSONArray jsonArray1 = jsonObject1.optJSONArray("future");
                        if (jsonArray1.length() > 0) {
                            List<WeatherBeen> list = JSON.parseArray(jsonArray1.toString(), WeatherBeen.class);
                            adapter.fillList(list);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }


    @OnClick(R.id.text)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), ChangeCityActivity.class);
        startActivityForResult(intent, CHANGE_CITY, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }
}
