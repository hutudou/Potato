package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.potato.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author potato
 */
public class DynamicAddLayoutActivity extends BaseActivity {
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_add_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        LayoutInflater inflater = LayoutInflater.from(DynamicAddLayoutActivity.this);
        // 获取需要被添加控件的布局
        LinearLayout linearLayout = findViewById(R.id.linearMain);
        // 获取需要添加的布局
        LinearLayout layout = inflater.inflate(
                R.layout.layout_dynamic_layout, null).findViewById(R.id.linearDynamic);
        // 将布局加入到当前布局中
        linearLayout.addView(layout);
        flag++;
        layout.setTag(flag);
        TextView textView = layout.findViewById(R.id.text);
        EditText editText = layout.findViewById(R.id.edit);
        textView.setText("缺陷" + flag);
        editText.setText("这是缺陷" + flag);
    }
}
