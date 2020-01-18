package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.administrator.potato.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 */
public class UseCustomSpinnerActivity extends BaseActivity {
    /**
     * spinner的数据来源 需要是一个String型的数组
     */
    String[] arr = {"JAVA", "Android", "xml", "ios"};
    private List<String> list=new ArrayList<>();
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_custom_spinner);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用自定义的spinner", true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.textView, list);
        spinner.setAdapter(adapter);
    }
}
