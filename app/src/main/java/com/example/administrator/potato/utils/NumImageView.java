package com.example.administrator.potato.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * @author potato
 */
public class NumImageView extends android.support.v7.widget.AppCompatImageView {

    /**
     * 要显示的数量数量
     */
    private int num = 0;
    /**
     * 色圆圈的半径
     */
    private float radius;
    /**
     * 圆圈内数字的半径
     */
    private float textSize;
    /**
     * 右边和上边内边距
     */
    private int paddingRight;
    private int paddingTop;
    /**
     * 角标text的xy
     */
    private float textX;
    private float textY;
    /**
     * 画笔
     */
    Paint paint = new Paint();

    public NumImageView(Context context) {
        super(context);
    }

    public NumImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //设置显示的数量
    public void setNum(int num) {
        this.num = num;
        initView();
        //重新绘制画布
        invalidate();
    }

    private void initView() {
        //初始化数值时需要延时执行 因为初始化时 activity onCreate时控件并没有绘制完成 所以此时getWidth返回的数值是0
        radius = getWidth() / 6;
        setTextSize(num);
        setCircleXY();
        //初始化边距
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        //设置抗锯齿
        paint.setAntiAlias(true);
    }

    //设置字体大小
    private void setTextSize(int num) {
        if (num > 0 && num < 10) {
            textSize = radius + 5;
        } else if (num >= 10 && num < 100) {
            textSize = radius;
        } else if (num >= 100 && num < 1000) {
            textSize = radius - 5;
        }
    }

    //设置text的x轴和y轴
    private void setCircleXY() {
        if (num > 0 && num < 10) {
            textX = getWidth() - radius - textSize / 4 - paddingRight / 2;
        } else if (num >= 10 && num < 100) {
            textX = getWidth() - radius - textSize / 1.7f - paddingRight / 2;
        } else if (num >= 100 && num < 1000) {
            textX = getWidth() - radius - textSize / 1.15f - paddingRight / 2;
        } else if (num >= 1000) {
            num = 999;
        }
        textY = radius + textSize / 3 + paddingTop / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (num > 0) {
            //设置颜色为红色
            paint.setColor(0xffff4444);
            //设置填充样式为充满
            paint.setStyle(Paint.Style.FILL);
            //画圆
            canvas.drawCircle(getWidth() - radius - paddingRight / 2, radius + paddingTop / 2, radius, paint);
            //设置颜色为白色
            paint.setColor(0xffffffff);
            //设置字体大小
            paint.setTextSize(textSize);
            //画数字
            canvas.drawText("" + num, textX, textY, paint);
        }
    }
}