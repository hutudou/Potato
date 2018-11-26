package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.recyclerView.adapter.CommonRecyclerViewAdapter;
import com.example.administrator.potato.been.TodayInHistoryBeen;
import com.example.administrator.potato.callback.StringDialogCallback;
import com.example.administrator.potato.holder.BaseRecyclerViewHolder;
import com.example.administrator.potato.request.RequestMethod;
import com.example.administrator.potato.request.RequestUrl;
import com.example.administrator.potato.utils.ToastMessage;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseOkHttpActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private CommonRecyclerViewAdapter<TodayInHistoryBeen> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_okhttp);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        adapter = new CommonRecyclerViewAdapter<TodayInHistoryBeen>(this, R.layout.item_today_in_history) {
            @Override
            public void convert(BaseRecyclerViewHolder holder, TodayInHistoryBeen item, int position) {
                TextView textView = holder.getView(R.id.textTitle);
                textView.setText(item.getTitle());

                textView = holder.getView(R.id.textEvent);
                textView.setText(item.getEvent());
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加默认的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Map<String, String> map = new HashMap<>();
        map.put("key", AppConstant.mobAppkey);
        map.put("day", "0404");
        RequestMethod.get(this, RequestUrl.TOADY_IN_HISTORY, map, new StringDialogCallback(this) {
            @Override
            public void onSuccess(Response<String> response) {
                String msg = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(msg);
                    if (!TextUtils.isEmpty(msg)) {
                        List<TodayInHistoryBeen> list = JSON.parseArray(String.valueOf(jsonObject.optJSONArray("result")), TodayInHistoryBeen.class);
                        if (list.size() != 0) {
                            adapter.fillList(list);
                        }
                    } else {
                        ToastMessage.toastWarn("暂无数据", true);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                ToastMessage.toastError("网络请求错误", true);
                super.onError(response);
            }
        });
    }

}
