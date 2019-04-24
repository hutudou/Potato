package com.example.administrator.potato.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Explode;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.application.MyApplication;
import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.AppCustomerAttrsUtil;
import com.example.administrator.potato.utils.SharedPreferencesUtil;
import com.lzy.okgo.OkGo;

public abstract class BaseActivity extends AppCompatActivity {
    //上下文对象
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置主题
        if ((int) SharedPreferencesUtil.getData(AppConstant.CURRENT_APP_THEME, -1) == -1) {
            setTheme(R.style.AppVioletTheme);
        } else {
            setTheme((int) SharedPreferencesUtil.getData(AppConstant.CURRENT_APP_THEME, -1));
        }
        //获取上下文对象
        mContext = this;
        initStateBar();
        getWindow().setEnterTransition(new Explode().setDuration(300));
        getWindow().setExitTransition(new Explode().setDuration(300));
        getWindow().setReturnTransition(new Explode().setDuration(300));
    }

    @Override
    protected void onDestroy() {
        //统一关闭OK HTTP请求
        OkGo.getInstance().cancelTag(this);
        super.onDestroy();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideInputWhenTouchOtherView(this, ev);
        return super.dispatchTouchEvent(ev);
    }

    /**
     *      * 当点击其他View时隐藏软键盘
     *      * @param activity
     *      * @param ev
     *      * @param excludeViews  点击这些View不会触发隐藏软键盘动作
     *
     */
    public static final void hideInputWhenTouchOtherView(Activity activity, MotionEvent ev) {


        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = activity.getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
    }
    public static final boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            return !isTouchView(v, event);
        }
        return false;
    }
    public static final boolean isTouchView(View view, MotionEvent event) {
        if (view == null || event == null) {
            return false;
        }
        int[] leftTop = {0, 0};
        view.getLocationInWindow(leftTop);
        int left = leftTop[0];
        int top = leftTop[1];
        int bottom = top + view.getHeight();
        int right = left + view.getWidth();
        if (event.getRawX() > left && event.getRawX() < right
                && event.getRawY() > top && event.getRawY() < bottom) {
            return true;
        }
        return false;
    }
    /**
     * 设置状态栏的颜色与toolbar一致 只有安卓5.0以上才能用
     */
    private void initStateBar() {
        Window window = getWindow();
        //设置状态栏为透明
        //5.0以上使用原生方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            TypedArray typedArray = mContext.obtainStyledAttributes(R.styleable.appCustomerAttrs);
            //设置状态栏的颜色与toolBar的颜色一致
            window.setStatusBarColor(AppCustomerAttrsUtil.getColor(mContext, R.styleable.appCustomerAttrs, R.styleable.appCustomerAttrs_toolBarColor));
//            typedArray.recycle();
        }
    }

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    /**
     * 初始视图
     */
    protected abstract void initView();

    /**
     * 初始数据
     */
    protected abstract void initData();

    /**
     * 显示确认框形式的dialog
     *
     * @param title                  dialog的标题
     * @param msg                    dialog的消息
     * @param confirmDialogInterface 监听dialog确认键以及取消键的点击事件
     */
    protected void showConfirmDialog(@Nullable String title, @Nullable String msg, @NonNull final ConfirmDialogInterface confirmDialogInterface) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        //加载布局
        View view = View.inflate(mContext, R.layout.dialog_confirm, null);
        //获取组件实例
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textContent = view.findViewById(R.id.textContent);
        TextView textConfirm = view.findViewById(R.id.textConfirm);
        TextView textCancel = view.findViewById(R.id.textCancel);

        //设置标题
        textTitle.setText(title);
        //设置消息内容
        textContent.setText(msg);
        //设置需要显示的view
        builder.setView(view);
        //赋值给其父类以获取dismiss方法
        final AlertDialog alertDialog = builder.create();
        //显示dialog
        alertDialog.show();
        //设置确定按钮内容
        textConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确认键业务逻辑处理接口
                confirmDialogInterface.onConfirmClickListener();
                //业务逻辑处理完毕使dialog消失
                alertDialog.dismiss();
            }
        });
        //设置取消按钮内容
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消键业务逻辑处理接口
                confirmDialogInterface.onCancelClickListener();
                //业务逻辑处理完毕使dialog消失
                alertDialog.dismiss();
            }
        });

    }

    /**
     * 初始化toolBar 完整版 左右两边都需要设置
     *
     * @param toolbar                   toolbar
     * @param title                     toolbar标题
     * @param isShowLeft                是否显示左边图标
     * @param navigationOnClickListener 左边图标监听事件
     * @param isShowRight               是否显示右边menu
     * @param menuId                    menu的id
     * @param onMenuItemClickListener   menu的子项监听事件
     */
    protected void initToolBar(android.support.v7.widget.Toolbar toolbar, @NonNull String title, Boolean isShowLeft, View.OnClickListener navigationOnClickListener, Boolean isShowRight
            , @MenuRes Integer menuId, android.support.v7.widget.Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        //使用toolbar替代actionBar
        //setSupportActionBar(toolbar); 这行代码删除后 toolbar.inflateMenu才能生效
        toolbar.setTitle(title);
        //设置左边icon
        if (isShowLeft) {
            toolbar.setNavigationIcon(R.drawable.icon_vector_back);
            if (navigationOnClickListener != null) {
                toolbar.setNavigationOnClickListener(navigationOnClickListener);
            }
        }
        //设置右边menu
        if (isShowRight) {
            if (onMenuItemClickListener != null) {
                toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
            }
            if (menuId != null) {
                //如果要使用toolbar.inflateMenu 则不能使用setSupportActionBar
                toolbar.inflateMenu(menuId);
            } else {
                //不传菜单文件时使用默认的menu
                toolbar.inflateMenu(R.menu.app_toolbar_menu);
            }
        }
    }

    /**
     * 初始toolbar 简略版 因为大多数的活动是不需要设置右边的 所以可以简化操作
     *
     * @param toolbar                   toolBar
     * @param title                     toolBar的标题
     * @param isShowLeft                是否显示左边图标
     * @param navigationOnClickListener 左边图标的点击事件
     */
    protected void initToolBar(android.support.v7.widget.Toolbar toolbar, @NonNull String title, Boolean isShowLeft, View.OnClickListener navigationOnClickListener) {
        //使用toolbar替代actionBar
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        //设置左边icon
        if (isShowLeft) {
            toolbar.setNavigationIcon(R.drawable.icon_vector_back);
            if (navigationOnClickListener != null) {
                toolbar.setNavigationOnClickListener(navigationOnClickListener);
            }
        }
    }

    /**
     * 活动跳转
     *
     * @param clazz            要跳转的活动
     * @param isFinishActivity 是否结束当前活动
     */
    protected void gotoActivity(Class<?> clazz, boolean isFinishActivity) {
        Intent intent = new Intent(MyApplication.getContext(), clazz);
        startActivity(intent);
        if (isFinishActivity) {
            this.finish();
        }
    }

    /**
     * 活动跳转
     *
     * @param clazz            目标跳转活动
     * @param bundle           参数
     * @param isFinishActivity 是否结束当前活动
     */
    protected void gotoActivity(Class<?> clazz, Bundle bundle, boolean isFinishActivity) {
        Intent intent = new Intent(MyApplication.getContext(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isFinishActivity) {
            this.finish();
        }
    }

    /**
     * 活动跳转
     *
     * @param clazz            目标跳转活动
     * @param bundle           参数
     * @param action           action
     * @param isFinishActivity 是否结束当前活动
     */
    protected void gotoActivity(Class<?> clazz, Bundle bundle, String action, boolean isFinishActivity) {
        Intent intent = new Intent(MyApplication.getContext(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (!TextUtils.isEmpty(action)) {
            intent.setAction(action);
        }
        startActivity(intent);
        if (isFinishActivity) {
            this.finish();
        }
    }

    /**
     * 展示snackBar
     *
     * @param view                view
     * @param msg                 消息
     * @param isDismiss           是否自动消失
     * @param action              事件名
     * @param iSnackBarClickEvent 事件处理接口
     */
    protected void showSnackBar(@NonNull View view, @NonNull String msg, boolean isDismiss, String action, final ISnackBarClickEvent iSnackBarClickEvent) {
        //snackBar默认显示时间为LENGTH_LONG
        int duringTime = Snackbar.LENGTH_LONG;
        if (isDismiss) {
            duringTime = Snackbar.LENGTH_LONG;
        } else {
            duringTime = Snackbar.LENGTH_INDEFINITE;
        }
        Snackbar snackbar;
        snackbar = Snackbar.make(view, msg, duringTime)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //以接口方式发送出去，便于使用者处理自己的业务逻辑
                        iSnackBarClickEvent.clickEvent();
                    }
                });
        TypedArray typedArray = mContext.obtainStyledAttributes(R.styleable.appCustomerAttrs);
        //设置snackBar和titleBar颜色一致
        snackbar.getView().setBackgroundColor(typedArray.getColor(R.styleable.appCustomerAttrs_toolBarColor, Color.RED));
        typedArray.recycle();
        //设置action文字的颜色
        snackbar.setActionTextColor(getResources().getColor(R.color.normal_white));
        //设置snackBar图标 这里是获取到snackBar的textView 然后给textView增加左边图标的方式来实现的
        View snackBarView = snackbar.getView();
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        Drawable drawable = getResources().getDrawable(R.drawable.icon_vector_notification);//图片自己选择
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
        //增加文字和图标的距离
        textView.setCompoundDrawablePadding(20);
        //展示snackBar
        snackbar.show();
    }

    /**
     * snackBar的action事件
     */
    public interface ISnackBarClickEvent {
        //点击事件
        void clickEvent();
    }

    /**
     * confirmDialog的点击事件
     */
    public interface ConfirmDialogInterface {

        //监听确认按钮点击事件
        void onConfirmClickListener();

        //监听取消按钮点击事件
        void onCancelClickListener();
    }
}
