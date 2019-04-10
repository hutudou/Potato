package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.NumberPicker;

import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.DatePickerUtil;
import com.example.administrator.potato.utils.ToastMessage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseNumberPickerActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.pickerYear)
    NumberPicker pickerYear;
    @Bind(R.id.pickerMonth)
    NumberPicker pickerMonth;
    @Bind(R.id.pickerDay)
    NumberPicker pickerDay;
    @Bind(R.id.pickerHour)
    NumberPicker pickerHour;
    @Bind(R.id.pickerMinute)
    NumberPicker pickerMinute;
    @Bind(R.id.pickerSecond)
    NumberPicker pickerSecond;

    private String[] years = {"1999年", "2000年", "2001年", "2002年", "2003年", "2004年", "2005年"};
    private String[] months = {"01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月"};
    private String[] days = {"01日", "02日", "03日", "04日", "05日", "06日", "07日", "08日", "09日", "10日", "11日", "12日", "13日", "14日", "15日", "16日", "17日", "18日", "19日", "20日"
            , "21日", "22日", "23日", "24日", "25日", "26日", "27日", "28日", "29日", "30日", "31日"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_number_picker);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用numberPicker", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pickerYear.setDisplayedValues(years);
        pickerMonth.setDisplayedValues(months);
        pickerDay.setDisplayedValues(days);
        pickerYear.setMinValue(0);
        pickerYear.setMaxValue(years.length - 1);
        pickerMonth.setMinValue(0);
        pickerMonth.setMaxValue(months.length - 1);
        pickerDay.setMinValue(0);
        pickerDay.setMaxValue(days.length - 1);
        pickerYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int currentYear = Integer.valueOf(years[pickerYear.getValue()].replace("年", ""));
                if (isLeapYear(currentYear)) {//先判断是否是闰年
                    if (pickerMonth.getValue() == 1) {//再判断是否是二月
                        pickerDay.setMaxValue(days.length - 3);//闰年29天
                    } else {
                        if (isLargeMonth(pickerMonth.getValue())) {
                            pickerDay.setMaxValue(days.length - 1);//平年大月31天
                        } else {
                            pickerDay.setMaxValue(days.length - 2);//平年小月30天
                        }
                    }
                } else {//不是闰年
                    if (pickerMonth.getValue() == 1) {//再判断是否是二月
                        pickerDay.setMaxValue(days.length - 4);//不是闰年28天
                    } else {
                        if (isLargeMonth(pickerMonth.getValue())) {
                            pickerDay.setMaxValue(days.length - 1);//平年大月31天
                        } else {
                            pickerDay.setMaxValue(days.length - 2);//平年小月30天
                        }
                    }
                }
            }
        });
        pickerMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int currentYear = Integer.valueOf(years[pickerYear.getValue()].replace("年", ""));
                if (isLeapYear(currentYear)) {//先判断是否是闰年
                    if (pickerMonth.getValue() == 1) {//再判断是否是二月
                        pickerDay.setMaxValue(days.length - 3);//闰年29天
                    } else {
                        if (isLargeMonth(pickerMonth.getValue())) {
                            pickerDay.setMaxValue(days.length - 1);//平年大月31天
                        } else {
                            pickerDay.setMaxValue(days.length - 2);//平年小月30天
                        }
                    }
                } else {//不是闰年
                    if (pickerMonth.getValue() == 1) {//再判断是否是二月
                        pickerDay.setMaxValue(days.length - 4);//不是闰年28天
                    } else {
                        if (isLargeMonth(pickerMonth.getValue())) {
                            pickerDay.setMaxValue(days.length - 1);//平年大月31天
                        } else {
                            pickerDay.setMaxValue(days.length - 2);//平年小月30天
                        }
                    }
                }
            }
        });
    }

    //判断是否是闰年
    private boolean isLeapYear(int valueYear) {
        return (valueYear % 4 == 0 && valueYear % 100 != 0) || valueYear % 400 == 0;
    }

    //判断是否是大月
    private boolean isLargeMonth(int valueMonth) {
        valueMonth += 1;//月份需要加一
        return valueMonth == 1 || valueMonth == 3 || valueMonth == 5 || valueMonth == 7 || valueMonth == 8 || valueMonth == 10 || valueMonth == 12;
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        ToastMessage.toastWarn("当前时间是:" + years[pickerYear.getValue()] + "" + months[pickerMonth.getValue()] + "" + days[pickerDay.getValue()], false);
    }

    @OnClick(R.id.buttonChooseDate)
    public void onButtonChooseDate() {
        DatePickerUtil datePickerUtil = new DatePickerUtil(UseNumberPickerActivity.this);
        datePickerUtil.showDatePicker(new DatePickerUtil.IPickEvent() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onConfirm(String dateResult) {
            ToastMessage.toastSuccess("选择的日期是:"+dateResult,false);
            }
        });
    }
}
