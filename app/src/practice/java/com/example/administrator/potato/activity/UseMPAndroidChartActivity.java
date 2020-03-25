package com.example.administrator.potato.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.potato.R;
import com.example.administrator.potato.application.MyApplication;
import com.example.administrator.potato.utils.MPAndroidLineChartUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;


public class UseMPAndroidChartActivity extends BaseActivity {
    @Bind(R.id.lineChart)
    LineChart lineChart;
    @Bind(R.id.textLineOne)
    TextView textLineOne;
    @Bind(R.id.textLineTwo)
    TextView textLineTwo;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    //曲线一数据
    private List<Entry> entriesFir;
    //曲线二数据
    private List<Entry> entriesSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_mpandroid_chart);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用MPAndroid----折线图", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        MPAndroidLineChartUtil mpAndroidLineChartUtil = new MPAndroidLineChartUtil(lineChart, getDataSets());
        mpAndroidLineChartUtil.showLineChart();
        Description description = new Description();
        description.setText("测试折线图");
        lineChart.setDescription(description);
        lineChart.setMarker(new IMarker() {
            @Override
            public MPPointF getOffset() {
                return null;
            }

            @Override
            public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
                return null;
            }

            @Override
            public void refreshContent(Entry e, Highlight highlight) {
                Log.d("awei", e.toString());
                //记录当前的position
                int currentPosition = -1;
                //获取点击的曲线是哪一条
                switch (highlight.getDataSetIndex()) {
                    case 0://第一条
                        currentPosition = getPosition(entriesFir, e);
                        break;
                    case 1://第二条
                        currentPosition = getPosition(entriesSec, e);
                        break;
                }
                if (currentPosition == -1) {
                    //使用application的context以防止内存泄漏
                    Toast.makeText(MyApplication.getContext(), "程序错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                //得到当前的位置后就可以在list中找到值了 然后在显示在布局中
                textLineOne.setText(String.format("曲线一当前X轴的值是:%s    Y轴的值是:%s", entriesFir.get(currentPosition).getX(), entriesFir.get(currentPosition).getY()));
                textLineTwo.setText(String.format("曲线二当前X轴的值是:%s    Y轴的值是:%s", entriesSec.get(currentPosition).getX(), entriesSec.get(currentPosition).getY()));
            }

            @Override
            public void draw(Canvas canvas, float posX, float posY) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    //设置dataSet对象
    private ArrayList<ILineDataSet> getDataSets() {

        //第一条曲线
        entriesFir = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            entriesFir.add(new Entry(i, new Random().nextInt(300)));
        LineDataSet dataSet = new LineDataSet(entriesFir, "测试一"); //
        //设置圆点是否是空心圆显示
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(true);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //设置折线的颜色
        dataSet.setColor(Color.parseColor("#7d7d7d"));
        //设置圆点的颜色
        dataSet.setCircleColor(Color.parseColor("#7d7d7d"));
        //设置折线的宽度
        dataSet.setLineWidth(1f);

        //第二条曲线
        entriesSec = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            entriesSec.add(new Entry(i, -(new Random().nextInt(300))));
        LineDataSet dataSet1 = new LineDataSet(entriesSec, "测试二"); // add entries to dataset
        //设置圆点是否是空心圆显示
        dataSet1.setDrawCircles(false);
        //设置折线的颜色
        dataSet1.setColor(Color.parseColor("#3fc3fe"));
        dataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //设置圆点的颜色
        dataSet1.setCircleColor(Color.parseColor("#7d7d7d"));
        //设置折线的宽度
        dataSet1.setLineWidth(1f);
        dataSet1.setDrawValues(true);

        //加入集合中
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        dataSets.add(dataSet1);

        return dataSets;
    }

    //根据x轴的值得到其在数据集合中的位置
    private int getPosition(List<Entry> entries, Entry entry) {
        for (int i = 0; i < entries.size(); i++) {
            //使用x轴的数据做对比是因为x轴的数据都是唯一的 所以可以确保位置的精确性
            if (entries.get(i).getX() == entry.getX()) {
                return i;
            }
        }
        return -1;
    }

}

