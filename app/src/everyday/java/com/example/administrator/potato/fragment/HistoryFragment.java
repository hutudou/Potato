package com.example.administrator.potato.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.activity.HistoryDetailedActivity;
import com.example.administrator.potato.callback.StringDialogCallback;
import com.example.administrator.potato.holder.BaseRecyclerViewHolder;
import com.example.administrator.potato.javabeen.HistoryInfoResults;
import com.example.administrator.potato.recyclerView.adapter.CommonRecyclerViewAdapter;
import com.example.administrator.potato.recyclerView.click.ItemClickListener;
import com.example.administrator.potato.request.RequestMethod;
import com.example.administrator.potato.request.RequestUrl;
import com.example.administrator.potato.utils.DateUtils;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * 作者 Administrator
 * 时间 2018/12/14
 */

public class HistoryFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private View view;
    private CommonRecyclerViewAdapter<HistoryInfoResults> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initView() {
        adapter = new CommonRecyclerViewAdapter<HistoryInfoResults>(getActivity(), R.layout.item_recyler_history) {
            @Override
            public void convert(BaseRecyclerViewHolder holder, HistoryInfoResults item, int position) {

                TextView textView = holder.getView(R.id.textTitle);
                textView.setText(item.getTitle());

                textView = holder.getView(R.id.textContent);
                textView.setText(item.getEvent());

                textView = holder.getView(R.id.textDate);
                textView.setText(String.format("发生于%s年%s月%s日", item.getDate().substring(0, 4), item.getDate().substring(4, 6), item.getDate().substring(6, item.getDate().length())));
            }
        };

        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(adapter);
        animationAdapter.setDuration(800);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//      recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(animationAdapter);
        recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView, new ItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("event", adapter.getItem(position));
                gotoActivity(HistoryDetailedActivity.class, bundle);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Override
    protected void initData() {
        request();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //请求历史上的今天
    private void request() {
        Map<String, String> map = new HashMap<>();
        map.put("key", AppConstant.MOB_APP_KEY);
        map.put("day", DateUtils.getLocalDate("MMdd"));//得到月和日
        RequestMethod.get(getActivity(), RequestUrl.TOADY_IN_HISTORY, map, new StringDialogCallback(getActivity()) {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.optJSONArray("result");
                    if (array.length() > 0) {
                        List<HistoryInfoResults> list = JSON.parseArray(array.toString(), HistoryInfoResults.class);
                        adapter.fillList(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                showSnackBar(recyclerView, "与服务器通信失败...", true, null, null);
            }
        });
    }
}
