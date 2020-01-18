package com.example.administrator.potato.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.example.administrator.potato.R;
import com.example.administrator.potato.bean.UserInfo;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 */
public class UseTableActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.table)
    SmartTable table;
    private TableData tableData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_table);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("使用table");
    }

    @Override
    protected void initData() {
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("江海", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("江海", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("江海", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("江海", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("沈阳", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("乌鲁木齐", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("乌鲁木齐", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("乌鲁木齐", 100, 150, 50, 240, 1100, 450, 23458,250));
        list.add(new UserInfo("乌鲁木齐", 100, 150, 50, 240, 1100, 450, 23458,250));
        table.getConfig().setColumnTitleBackgroundColor(Color.GREEN);
        //是否展示行头
        table.getConfig().setShowXSequence(false);
        table.getConfig().setContentStyle(new FontStyle(20, Color.BLUE));
        table.setData(list);

    }

}
