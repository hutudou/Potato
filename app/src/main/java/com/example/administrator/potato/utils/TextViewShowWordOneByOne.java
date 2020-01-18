package com.example.administrator.potato.utils;

import android.widget.TextView;

/**
 * 让textView的文字逐步显示
 */
public class TextViewShowWordOneByOne {

    //textview不能设置为静态的 否则会造成内存泄漏
    private TextView tv;
    private static String s;
    private static int length;
    private static long time;
    static int n = 0;
    private static int nn;
    //是否重复播放
    private boolean isRepeat = false;

    public TextViewShowWordOneByOne(TextView tv, String s, long time, boolean isRepeat) {
        this.tv = tv;
        //字符串
        TextViewShowWordOneByOne.s = s;
        //间隔时间
        TextViewShowWordOneByOne.time = time;
        length = s.length();
        this.isRepeat = isRepeat;
    }

    /**
     * 逐字显示的实现思想是:使用一个变量 n 来控制读取的字 在ui线程更新后 n数量加一 然后线程休眠 再判断字符串是不是读取完毕
     *
     * @param n 控制读取的字符的位置
     */
    public void startTv(final int n) {

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String stv = s.substring(0, n);//截取要填充的字符串
                            tv.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText(stv);
                                }
                            });
                            //休息片刻
                            Thread.sleep(time);
                            nn = n + 1;//读取下个字
                            if (nn <= length) {//如果还有汉字，那么继续开启线程，相当于递归的感觉
                                startTv(nn);
                            } else {
                                if (isRepeat) {//是否重复播放文字
                                    startTv(0);
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
}

