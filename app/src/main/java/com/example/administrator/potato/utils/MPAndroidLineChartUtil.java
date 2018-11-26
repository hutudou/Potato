package com.example.administrator.potato.utils;

import android.graphics.Canvas;
import android.graphics.Color;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

/**
 * MPAndroidChart折线图管理工具
 */
public class MPAndroidLineChartUtil {
    private LineChart lineChart;
    //一个dataSet对象就是一条折线
    private ArrayList<ILineDataSet> dataSets;
    private XAxis xAxis;

    //初始化传入数据
    public MPAndroidLineChartUtil(LineChart lineChart, ArrayList<ILineDataSet> dataSets) {
        this.lineChart = lineChart;
        this.dataSets = dataSets;
        xAxis = lineChart.getXAxis();
    }

    /**
     * 显示折线图
     */
    public void showLineChart() {
        setXAxis();
        setYAxis();
        setLineChart();
        setLegend();
        setDescription();
        setLineChartAni();
        LineData lineData = new LineData(dataSets);
        //绑定数据
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    /**
     * 设置X轴参数
     */
    private void setXAxis() {
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        //xAxis.setTextSize(20f);//设置字体
        //xAxis.setTextColor(Color.BLACK);//设置字体颜色
        //设置竖线的显示样式为虚线
        //lineLength控制虚线段的长度
        //spaceLength控制线之间的空间
        xAxis.enableGridDashedLine(10f, 10f, 0f);
//        xAxis.setAxisMinimum(0f);//设置x轴的最小值
//        xAxis.setAxisMaximum(10f);//设置最大值
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
//        设置x轴显示标签数量
//        xAxis.setLabelCount(10);
//        xAxis.setTextColor(Color.BLUE);//设置轴标签的颜色
//        xAxis.setTextSize(24f);//设置轴标签的大小
//        xAxis.setGridLineWidth(10f);//设置竖线大小
//        xAxis.setGridColor(Color.RED);//设置竖线颜色
//        xAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        xAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        xAxis.setValueFormatter();//格式化x轴标签显示字符
    }

    /**
     * 设置Y轴参数
     */
    private void setYAxis() {
        //获取右边的轴线
        YAxis rightAxis = lineChart.getAxisRight();
        //设置是否显示右边的y轴
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = lineChart.getAxisLeft();
        //设置网格线为虚线效果
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);
        rightAxis.setAxisMaximum(dataSets.get(0).getYMax() * 2);
        leftAxis.setAxisMaximum(dataSets.get(0).getYMax() * 2);
       /* for (int i=0;i<dataSets.size();i++){
            //将左右边的y轴放大两倍
            rightAxis.setAxisMaximum(dataSets.get(i).getYMax() * 2);
            leftAxis.setAxisMaximum(dataSets.get(i).getYMax() * 2);
        }*/

    }

    /**
     * 设置图标交互事件
     */
    private void setLineChart() {
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        lineChart.setScaleYEnabled(false); //是否可以缩放 仅y轴
        lineChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        lineChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
    }

    /**
     * 设置图标动画
     */
    private void setLineChartAni() {
//        animateX(int durationMillis) : 水平轴动画 在指定时间内 从左到右
//        animateY(int durationMillis) : 垂直轴动画 从下到上
//        animateXY(int xDuration, int yDuration) : 两个轴动画，从左到右，从下到上
        lineChart.animateXY(2000, 2000);
    }

    /**
     * 更新数据
     */
    private void updateXY() {

    }

    /**
     * 设置图例
     */
    private void setLegend() {
        /*//透明化图例
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        legend.setTextColor(Color.WHITE);*/
    }

    /**
     * 设置x轴描述
     */
    private void setDescription() {
        Description description = new Description();
        description.setEnabled(false);

    }

}
