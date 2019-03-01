package com.example.administrator.potato.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.callback.StringDialogCallback;
import com.example.administrator.potato.javabeen.CityBeen;
import com.example.administrator.potato.request.RequestMethod;
import com.example.administrator.potato.request.RequestUrl;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeCityActivity extends BaseActivity {
    //常量
    public static final int CONFIRM_CITY = 1;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.spinnerProvince)
    MaterialSpinner spinnerProvince;
    @Bind(R.id.spinnerCity)
    MaterialSpinner spinnerCity;
    @Bind(R.id.spinnerArea)
    MaterialSpinner spinnerArea;
    private List<String> provinces;
    private List<String> cities;
    private List<String> areas;
    private List<CityBeen> list;
    private int provincesIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_city);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "选择查看的地区", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        request();
    }

    private void request() {
        Map<String, String> map = new HashMap<>();
        map.put("key", AppConstant.MOB_APP_KEY);
        RequestMethod.get(this, RequestUrl.CHOOSE_CITY, map, new StringDialogCallback(this) {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    list = new ArrayList<>();
                    JSONArray resultArray = jsonObject.optJSONArray("result");
                    if (resultArray.length() > 0) {
                        list = JSON.parseArray(resultArray.toString(), CityBeen.class);
                        //分离省份、城市、区 第一次默认使用每组的第一个值作为spinner的初始值
                        provinces = new ArrayList<>();
                        cities = new ArrayList<>();
                        areas = new ArrayList<>();
                        //获取省
                        for (int i = 0; i < list.size(); i++) {
                            provinces.add(list.get(i).getProvince());
                        }
                        //获取第一个省的市
                        for (int i = 0; i < list.get(0).getCity().size(); i++) {
                            cities.add(list.get(0).getCity().get(i).getCity());
                        }
                        //获取第一个省的第一个市的区
                        for (int i = 1; i < list.get(0).getCity().get(0).getDistrict().size(); i++) {
                            areas.add(list.get(0).getCity().get(0).getDistrict().get(i).getDistrict());
                        }
                        //初始化spinner
                        initSpinners();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                showSnackBar(toolbar, "与服务器连接失败,原因是:" + response.message(), true, null, null);
            }
        });
    }

    //得到指定位置的市集合
    private List setCitiesList(int positionOfProvinces) {
        cities = new ArrayList<>();
        //获取第一个省的市
        for (int i = 0; i < list.get(positionOfProvinces).getCity().size(); i++) {
            cities.add(list.get(positionOfProvinces).getCity().get(i).getCity());
        }
        return cities;
    }

    //得到指定的区集合
    private List setAreasList(int positionOfProvinces, int positionOfCities) {
        areas = new ArrayList<>();
        //获取第一个省的市
        for (int i = 1; i < list.get(positionOfProvinces).getCity().get(positionOfCities).getDistrict().size(); i++) {
            areas.add(list.get(positionOfProvinces).getCity().get(positionOfCities).getDistrict().get(i).getDistrict());
        }
        return areas;
    }

    //初始化 spinner
    private void initSpinners() {
        spinnerProvince.setItems(provinces);
        spinnerCity.setItems(cities);
        spinnerArea.setItems(areas);

        spinnerProvince.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                //选择省的时候更改市和区
                spinnerCity.setItems(setCitiesList(position));
                spinnerArea.setItems(setAreasList(position, 0));
                provincesIndex = position;
            }
        });

        spinnerCity.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                //选择市的时候更改区
                spinnerArea.setItems(setAreasList(provincesIndex, position));
            }
        });
    }

    @OnClick(R.id.buttonConfirm)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.putExtra("province", spinnerProvince.getText().toString());
        intent.putExtra("area", spinnerArea.getText().toString());
        if (TextUtils.isEmpty(spinnerArea.getText().toString())) {//有的地方是不存在第三级地区的 这种情况直接用第二级就行
            intent.putExtra("area", spinnerCity.getText().toString());
        }
        setResult(CONFIRM_CITY, intent);
        finish();
    }
}
