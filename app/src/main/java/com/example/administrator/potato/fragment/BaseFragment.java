package com.example.administrator.potato.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.example.administrator.potato.activity.BaseActivity;
import com.example.administrator.potato.application.MyApplication;
import com.lzy.okgo.OkGo;

/**
 * 封装BaseFragment
 * <p>
 * 作者 Administrator
 * 时间 2018/11/9
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment {
    //上下文对象
    private Context mContext;
    //页面是否已经加载完毕
    private boolean isPrepared = false;
    //页面是否可见
    private boolean isViewVisibility = false;
    //是否是第一次加载
    private boolean isFirstLoad = true;

    private ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lozyLoad();
    }

    @Override
    public void onDestroyView() {
        //统一关闭okgo
        OkGo.getInstance().cancelTag(this);
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isViewVisibility = true;
            lozyLoad();
        } else {

        }
    }

    //懒加载
    private void lozyLoad() {
        if (!isViewVisibility || !isPrepared || !isFirstLoad) {
            return;
        }
        initView();
        initData();
        isFirstLoad = false;
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
    protected void showConfirmDialog(@Nullable String title, @Nullable String msg, @NonNull final BaseActivity.ConfirmDialogInterface confirmDialogInterface) {
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
     * 活动跳转
     *
     * @param clazz 要跳转的活动
     */
    protected void gotoActivity(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    //初始化网络请求dialog
    private void initWaitDialog(Fragment fragment) {
        progressDialog = new ProgressDialog(fragment.getContext());
        //无标题
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置手指点击dialog不消失
        progressDialog.setCanceledOnTouchOutside(false);
        //设置dialog的内容
        progressDialog.setMessage("正在全力加载中...");
        //设置dialog样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    //bmob内部封装了okhttp请求 所以需要手动添加dialog来提示用户正在进行网络请求
    protected void showWaitDialog(Fragment fragment) {
        initWaitDialog(fragment);
        //dialog不为空且不在展示的状态才显示
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    protected void hideWaitDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
    /**
     * 活动跳转
     *
     * @param clazz  目标跳转活动
     * @param bundle 参数
     */
    protected void gotoActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 活动跳转
     *
     * @param clazz  目标跳转活动
     * @param bundle 参数
     * @param action action
     */
    protected void gotoActivity(Class<?> clazz, Bundle bundle, String action) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (!TextUtils.isEmpty(action)) {
            intent.setAction(action);
        }
        startActivity(intent);
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
    protected void showSnackBar(@NonNull View view, @NonNull String msg, boolean isDismiss, String action, final BaseActivity.ISnackBarClickEvent iSnackBarClickEvent) {
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
        public void clickEvent();
    }
}
