package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.bean.ItemBeen;
import com.example.administrator.potato.holder.BaseRecyclerViewHolder;
import com.example.administrator.potato.recycler.adapter.CommonRecyclerViewAdapter;
import com.example.administrator.potato.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 * @date 2019/11/11
 */
public class UseTimeAxleActivity extends BaseActivity {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    /**
     * adapter
     */
    private CommonRecyclerViewAdapter<ItemBeen> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_time_axle);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        adapter = new CommonRecyclerViewAdapter<ItemBeen>(mContext, R.layout.list_cell) {
            @Override
            public void convert(BaseRecyclerViewHolder holder, ItemBeen item, int position) {
                //标题
                TextView textView = holder.getView(R.id.textItemTitle);
                textView.setText(item.getTextItemTitle());
                //文本内容
                textView = holder.getView(R.id.textItemText);
                textView.setText(item.getTextItemText());
            }
        };
        //使用线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //用自定义分割线类设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        //为ListView绑定适配器
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        //填充数据
        List<ItemBeen> list = new ArrayList<>();

        ItemBeen itemBeen = new ItemBeen();
        itemBeen.setTextItemText("美国谷歌公司已发出");
        itemBeen.setTextItemTitle("发件人:谷歌 CEO Sundar Pichai");

        ItemBeen itemBeen1 = new ItemBeen();
        itemBeen1.setTextItemText("国际顺丰已收入");
        itemBeen1.setTextItemTitle("等待中转");

        ItemBeen itemBeen2 = new ItemBeen();
        itemBeen2.setTextItemText("国际顺丰转件中");
        itemBeen2.setTextItemTitle("下一站中国");

        ItemBeen itemBeen3 = new ItemBeen();
        itemBeen3.setTextItemText("中国顺丰已收入");
        itemBeen3.setTextItemTitle("下一站广州华南理工大学");

        ItemBeen itemBeen4 = new ItemBeen();
        itemBeen4.setTextItemText("中国顺丰派件中");
        itemBeen4.setTextItemTitle("等待派件");

        ItemBeen itemBeen5 = new ItemBeen();
        itemBeen5.setTextItemText("华南理工大学已签收");
        itemBeen5.setTextItemTitle("收件人:Carson");

        ItemBeen itemBeen6 = new ItemBeen();
        itemBeen6.setTextItemText("已到达二舍快递存放点");
        itemBeen6.setTextItemTitle("收件人:你二大爷");

        list.add(itemBeen);
        list.add(itemBeen1);
        list.add(itemBeen2);
        list.add(itemBeen3);
        list.add(itemBeen4);
        list.add(itemBeen5);
        list.add(itemBeen6);
        adapter.fillList(list);
    }
}
