package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.ToastMessage;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 */
public class UseNiceSpinnerActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.niceSpinner1)
    NiceSpinner niceSpinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_nice_spinner);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用NiceSpinner插件", true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        List<String> listData = new ArrayList<>(16);
        listData.add("一月");
        listData.add("二月");
        listData.add("三月");
        listData.add("四月");
        listData.add("五月");
        listData.add("六月");
        niceSpinner1.attachDataSource(listData);
        niceSpinner1.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                ToastMessage.toastWarn("点击了第" + position + "项，他的值是:" + parent.getItemAtPosition(position),false);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
