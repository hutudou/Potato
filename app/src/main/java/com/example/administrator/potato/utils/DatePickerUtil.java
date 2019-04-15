package com.example.administrator.potato.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.potato.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 作者 Administrator
 * 时间 2019/4/3
 */

public class DatePickerUtil {
    //常量
    //月
    private final String[] MONTHS = {"01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月"};
    //日
    private final String[] DAYS = {"01日", "02日", "03日", "04日", "05日", "06日", "07日", "08日", "09日", "10日",
            "11日", "12日", "13日", "14日", "15日", "16日", "17日", "18日", "19日", "20日",
            "21日", "22日", "23日", "24日", "25日", "26日", "27日", "28日", "29日", "30日", "31日"};
    //时
    private final String[] HOURS = {
            "01时", "02时", "03时", "04时", "05时", "06时", "07时", "08时", "09时", "10时",
            "11时", "12时", "13时", "14时", "15时", "16时", "17时", "18时", "19时", "20时",
            "21时", "22时", "23时", "24时"};
    //分
    private final String[] MINUTES = {
            "01分", "02分", "03分", "04分", "05分", "06分", "07分", "08分", "09分", "10分",
            "11分", "12分", "13分", "14分", "15分", "16分", "17分", "18分", "19分", "20分",
            "21分", "22分", "23分", "24分", "25分", "26分", "27分", "28分", "29分", "30分",
            "31分", "32分", "33分", "34分", "35分", "36分", "37分", "38分", "39分", "40分",
            "41分", "42分", "43分", "44分", "45分", "46分", "47分", "48分", "49分", "50分",
            "51分", "52分", "53分", "54分", "55分", "56分", "57分", "58分", "59分"
    };
    //秒
    private final String[] SECONDS = {
            "01秒", "02秒", "03秒", "04秒", "05秒", "06秒", "07秒", "08秒", "09秒", "10秒",
            "11秒", "12秒", "13秒", "14秒", "15秒", "16秒", "17秒", "18秒", "19秒", "20秒",
            "21秒", "22秒", "23秒", "24秒", "25秒", "26秒", "27秒", "28秒", "29秒", "30秒",
            "31秒", "32秒", "33秒", "34秒", "35秒", "36秒", "37秒", "38秒", "39秒", "40秒",
            "41秒", "42秒", "43秒", "44秒", "45秒", "46秒", "47秒", "48秒", "49秒", "50秒",
            "51秒", "52秒", "53秒", "54秒", "55秒", "56秒", "57秒", "58秒", "59秒"
    };
    private static final String DISMISS = "dismiss";
    private static final String SHOW = "show";
    //picker类型一 年月
    public static final String CHOOSE_YEAR_MONTH = "chooseYearMonth";
    //picker类型二 月日
    public static final String CHOOSE_MONTH_DAY = "chooseMonthDay";
    //picker类型三 年月日
    public static final String CHOOSE_YEAR_MONTH_DAY = "chooseYearMonthDay";
    //picker类型四  时分秒
    public static final String CHOOSE_HOUR_MINUTE_SECOND = "chooseHourMinuteSecond";
    //picker类型五 年月日 时分秒
    public static final String CHOOSE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "chooseYearMonthDayHourMinuteSecond";
    //年
    private ArrayList<String> years;
    private Activity activity;
    //当前年份
    private int currentYear;
    //当前月份
    private int currentMonth;
    //当前日
    private int currentDay;
    //当前时
    private int currentHour;
    //当前分
    private int currentMinute;
    //当前秒
    private int currentSecond;
    //view
    private View popupView;
    private NumberPicker pickerYear;
    private NumberPicker pickerMonth;
    private NumberPicker pickerDay;
    private NumberPicker pickerHour;
    private NumberPicker pickerMinute;
    private NumberPicker pickerSecond;
    private TextView textCancel;
    private TextView textConfirm;

    public DatePickerUtil(Activity activity) {
        this.activity = activity;
        years = new ArrayList<>();
        getCurrentDate();
        initView();
        initPicker();
        initPickerListener();
    }

    //将当前的日期的年、月、日、时、分、秒拆分出来
    private void getCurrentDate() {
        long t = System.currentTimeMillis();
        SimpleDateFormat dfYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat dfMonth = new SimpleDateFormat("MM");
        SimpleDateFormat dfDay = new SimpleDateFormat("dd");
        SimpleDateFormat dfHour = new SimpleDateFormat("HH");
        SimpleDateFormat dfMinute = new SimpleDateFormat("mm");
        SimpleDateFormat dfSecond = new SimpleDateFormat("ss");
        try {
            currentYear = Integer.parseInt(dfYear.format(t));
            currentMonth = Integer.parseInt(dfMonth.format(t));
            currentDay = Integer.parseInt(dfDay.format(t));
            currentHour = Integer.parseInt(dfHour.format(t));
            currentMinute = Integer.parseInt(dfMinute.format(t));
            currentSecond = Integer.parseInt(dfSecond.format(t));
        } catch (Exception e) {
            ToastMessage.toastError("日期转化int失败,请联系开发者哦...", false);
        }
        //因为年份是随着当前年份浮动的 所以需要动态赋值
        for (int i = currentYear - 10; i < currentYear + 10; i++) { //当前年份上下十年
            years.add(i + "年");
        }
    }

    //把numberpicker镶嵌在dialog中
    public void showDatePicker(@NonNull final String pickerType, final IPickEvent pickEvent) {
        //获取popupwindow布局
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //是否可以获取事件 设为false则事件会被父view拦截
        popupWindow.setFocusable(true);
        //设置是否可以在popubview外部进行点击
        popupWindow.setOutsideTouchable(true);
        //设置动画
        popupWindow.setAnimationStyle(R.style.popup_window_anim);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                change(DISMISS);
            }
        });
        switch (pickerType) {
            case CHOOSE_YEAR_MONTH://选择年月
                pickerDay.setVisibility(View.GONE);
                pickerHour.setVisibility(View.GONE);
                pickerMinute.setVisibility(View.GONE);
                pickerSecond.setVisibility(View.GONE);
                break;
            case CHOOSE_YEAR_MONTH_DAY://选择年月日
                pickerHour.setVisibility(View.GONE);
                pickerMinute.setVisibility(View.GONE);
                pickerSecond.setVisibility(View.GONE);
                break;
            case CHOOSE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND://选择年月日  时分秒
                break;
            case CHOOSE_HOUR_MINUTE_SECOND://选择时分秒
                pickerYear.setVisibility(View.GONE);
                pickerMonth.setVisibility(View.GONE);
                pickerDay.setVisibility(View.GONE);
                break;
            case CHOOSE_MONTH_DAY://选择月日
                pickerYear.setVisibility(View.GONE);
                pickerHour.setVisibility(View.GONE);
                pickerMinute.setVisibility(View.GONE);
                pickerSecond.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        change(SHOW);
        //更新popupWindow
        popupWindow.update();
        //设置点击事件
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickEvent.onCancel();
                ToastMessage.toastWarn("未选择日期...", false);
                popupWindow.dismiss();
            }
        });
        textConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result;
                switch (pickerType) {
                    case CHOOSE_YEAR_MONTH://选择年月
                        result = "" + years.get(pickerYear.getValue()) + "" + MONTHS[pickerMonth.getValue()];
                        break;
                    case CHOOSE_YEAR_MONTH_DAY://选择年月日
                        result = "" + years.get(pickerYear.getValue()) + "" + MONTHS[pickerMonth.getValue()] + "" + DAYS[pickerDay.getValue()];
                        break;
                    case CHOOSE_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND://选择年月日  时分秒
                        result = "" + years.get(pickerYear.getValue()) + "" + MONTHS[pickerMonth.getValue()] + "" + DAYS[pickerDay.getValue()] + "" + HOURS[pickerHour.getValue()] + "" + MINUTES[pickerMinute.getValue()] + "" + SECONDS[pickerSecond.getValue()];
                        break;
                    case CHOOSE_HOUR_MINUTE_SECOND://选择时分秒
                        result = "" + HOURS[pickerHour.getValue()] + "" + MINUTES[pickerMinute.getValue()] + "" + SECONDS[pickerSecond.getValue()];
                        break;
                    case CHOOSE_MONTH_DAY://选择月日
                        result = "" + MONTHS[pickerMonth.getValue()] + "" + DAYS[pickerDay.getValue()];
                        break;
                    default:
                        result = "" + years.get(pickerYear.getValue()) + "" + MONTHS[pickerMonth.getValue()] + "" + DAYS[pickerDay.getValue()] + "" + HOURS[pickerHour.getValue()] + "" + MINUTES[pickerMinute.getValue()] + "" + SECONDS[pickerSecond.getValue()];
                        break;
                }
                pickEvent.onConfirm(result);
                popupWindow.dismiss();
            }
        });
        //设置popupWindow的位置 相对于屏幕
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
        //此方法同样是设置popupWindow的位置 相对于组件
        //popupWindow.showAsDropDown(buttonOpen,0,0);
    }

    //设置picker的范围以及初始值
    private void initPicker() {
        //初始年
        String[] y = years.toArray(new String[0]);
        pickerYear.setDisplayedValues(y);
        pickerYear.setMinValue(0);
        pickerYear.setMaxValue(years.size() - 1);
        for (int i = 0; i < y.length; i++) {//设置pickerYear    从y数组中找到当前年份的位置
            if (y[i].contains(currentYear + "")) {
                pickerYear.setValue(i);
            }
        }
        //初始月
        pickerMonth.setDisplayedValues(MONTHS);
        pickerMonth.setMinValue(0);
        pickerMonth.setMaxValue(MONTHS.length - 1);
        pickerMonth.setValue(currentMonth - 1);
        //初始日
        pickerDay.setDisplayedValues(DAYS);
        pickerDay.setMinValue(0);
        pickerDay.setMaxValue(DAYS.length - 1);
        pickerDay.setValue(currentDay - 1);
        //初始时
        pickerHour.setDisplayedValues(HOURS);
        pickerHour.setMinValue(0);
        pickerHour.setMaxValue(HOURS.length - 1);
        pickerHour.setValue(currentHour - 1);
        //初始分
        pickerMinute.setDisplayedValues(MINUTES);
        pickerMinute.setMinValue(0);
        pickerMinute.setMaxValue(MINUTES.length - 1);
        pickerMinute.setValue(currentMinute - 1);
        //初始秒
        pickerSecond.setDisplayedValues(SECONDS);
        pickerSecond.setMinValue(0);
        pickerSecond.setMaxValue(SECONDS.length - 1);
        pickerSecond.setValue(currentSecond - 1);
        setPickerByDate(currentYear);
    }

    //设置日的取值范围（因为大小月 闰年平年时 月份的大小是不固定的 所以需要动态判定赋值）
    private void setPickerByDate(int currentYear) {
        if (isLeapYear(currentYear)) {//先判断是否是闰年
            if (pickerMonth.getValue() == 1) {//再判断是否是二月
                pickerDay.setMaxValue(DAYS.length - 3);//闰年29天
            } else {
                if (isLargeMonth(pickerMonth.getValue())) {
                    pickerDay.setMaxValue(DAYS.length - 1);//平年大月31天
                } else {
                    pickerDay.setMaxValue(DAYS.length - 2);//平年小月30天
                }
            }
        } else {//不是闰年
            if (pickerMonth.getValue() == 1) {//再判断是否是二月
                pickerDay.setMaxValue(DAYS.length - 4);//不是闰年28天
            } else {
                if (isLargeMonth(pickerMonth.getValue())) {
                    pickerDay.setMaxValue(DAYS.length - 1);//平年大月31天
                } else {
                    pickerDay.setMaxValue(DAYS.length - 2);//平年小月30天
                }
            }
        }
    }

    //添加picker事件
    private void initPickerListener() {
        pickerYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int currentYear = Integer.valueOf(years.get(pickerYear.getValue()).replace("年", ""));
                setPickerByDate(currentYear);
            }
        });
        pickerMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int currentYear = Integer.valueOf(years.get(pickerYear.getValue()).replace("年", ""));
                setPickerByDate(currentYear);
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

    //获取控件
    private void initView() {
        popupView = activity.getLayoutInflater().inflate(R.layout.popup_view_date_picker, null);
        //获取布局中的控件
        pickerYear = popupView.findViewById(R.id.pickerYear);
        pickerMonth = popupView.findViewById(R.id.pickerMonth);
        pickerDay = popupView.findViewById(R.id.pickerDay);
        pickerHour = popupView.findViewById(R.id.pickerHour);
        pickerMinute = popupView.findViewById(R.id.pickerMinute);
        pickerSecond = popupView.findViewById(R.id.pickerSecond);
        textCancel = popupView.findViewById(R.id.textCancel);
        textConfirm = popupView.findViewById(R.id.textConfirm);
    }

    //popubView在显示和消失时改变背景屏幕的透明度
    private void change(String type) {
        Window window = activity.getWindow();
        final WindowManager.LayoutParams params = window.getAttributes();
        if (type.equals("dismiss")) {
            params.alpha = 1.0f;
        } else if (type.equals("show")) {
            params.alpha = 0.5f;
        }
        window.setAttributes(params);
    }

    public interface IPickEvent {
        void onCancel();//取消事件

        void onConfirm(String dateResult);//日期确认选择  返回字符型时间和选择时间的时间戳
    }
}
