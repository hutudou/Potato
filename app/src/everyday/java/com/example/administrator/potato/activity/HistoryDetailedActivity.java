package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.javabeen.HistoryInfoResults;
import com.example.administrator.potato.utils.DateUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryDetailedActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textTitle)
    TextView textTitle;
    @Bind(R.id.textContent)
    TextView textContent;
    private HistoryInfoResults historyInfoResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detailed);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "历史事件详情(" + DateUtils.getLocalDate("MM月dd日") + ")", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (getIntent().hasExtra("event")) {
            historyInfoResults = (HistoryInfoResults) getIntent().getSerializableExtra("event");
            textTitle.setText(historyInfoResults.getTitle());
            textContent.setText(historyInfoResults.getEvent());
        }
    }

    @Override
    protected void initData() {

    }
}
