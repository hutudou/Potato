package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.utils.SharedPreferencesUtil;
import com.example.administrator.potato.utils.ToastMessage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeThemeActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textCurrentTheme)
    TextView textCurrentTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_theme);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "设置主题", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //获得当前主题名字
        if ((int) SharedPreferencesUtil.getData(AppConstant.CURRENT_APP_THEME, -1) != -1) {
            switch ((int) SharedPreferencesUtil.getData(AppConstant.CURRENT_APP_THEME, -1)) {
                case R.style.AppVioletTheme:
                    textCurrentTheme.setText("当前主题-----淡雅紫");
                    break;
                case R.style.AppBlueTheme:
                    textCurrentTheme.setText("当前主题-----梦想蓝");
                    break;
                case R.style.AppRedTheme:
                    textCurrentTheme.setText("当前主题-----欢乐红");
                    break;
                case R.style.AppOrangeTheme:
                    textCurrentTheme.setText("当前主题-----活力橙");
                    break;
                case R.style.AppPinkTheme:
                    textCurrentTheme.setText("当前主题-----少女粉");
                    break;
                case R.style.AppBlackTheme:
                    textCurrentTheme.setText("当前主题-----炫酷黑");
                    break;
            }
        } else {
            showSnackBar(toolbar, "程序内部异常,请重启程序试试...", true, null, null);
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.viewBlue, R.id.viewViolet, R.id.viewRed, R.id.viewOrange, R.id.viewPink, R.id.viewBlack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.viewBlue://梦想蓝
                if (isThemeHasSelected(R.style.AppBlueTheme)) {
                    ToastMessage.toastWarn("该主题已经使用了哦，请选择其他主题...", false);
                    return;
                }
                setTheme(R.style.AppBlueTheme);
                //保存用户选择的主题
                SharedPreferencesUtil.saveData(AppConstant.CURRENT_APP_THEME, R.style.AppBlueTheme);
                SharedPreferencesUtil.saveData(AppConstant.IS_SHOW_COLOR_WELCOME, true);
                finish();
                break;
            case R.id.viewViolet://淡雅紫
                if (isThemeHasSelected(R.style.AppVioletTheme)) {
                    ToastMessage.toastWarn("该主题已经使用了哦，请选择其他主题...", false);
                    return;
                }
                setTheme(R.style.AppVioletTheme);
                //保存用户选择的主题
                SharedPreferencesUtil.saveData(AppConstant.CURRENT_APP_THEME, R.style.AppVioletTheme);
                SharedPreferencesUtil.saveData(AppConstant.IS_SHOW_COLOR_WELCOME, true);
                finish();
                break;
            case R.id.viewRed://欢乐红
                if (isThemeHasSelected(R.style.AppRedTheme)) {
                    ToastMessage.toastWarn("该主题已经使用了哦，请选择其他主题...", false);
                    return;
                }
                setTheme(R.style.AppRedTheme);
                //保存用户选择的主题
                SharedPreferencesUtil.saveData(AppConstant.CURRENT_APP_THEME, R.style.AppRedTheme);
                SharedPreferencesUtil.saveData(AppConstant.IS_SHOW_COLOR_WELCOME, true);
                finish();
                break;
            case R.id.viewOrange://活力橙
                if (isThemeHasSelected(R.style.AppOrangeTheme)) {
                    ToastMessage.toastWarn("该主题已经使用了哦，请选择其他主题...", false);
                    return;
                }
                setTheme(R.style.AppOrangeTheme);
                //保存用户选择的主题
                SharedPreferencesUtil.saveData(AppConstant.CURRENT_APP_THEME, R.style.AppOrangeTheme);
                SharedPreferencesUtil.saveData(AppConstant.IS_SHOW_COLOR_WELCOME, true);
                finish();
                break;
            case R.id.viewPink://少女粉
                if (isThemeHasSelected(R.style.AppPinkTheme)) {
                    ToastMessage.toastWarn("该主题已经使用了哦，请选择其他主题...", false);
                    return;
                }
                setTheme(R.style.AppPinkTheme);
                //保存用户选择的主题
                SharedPreferencesUtil.saveData(AppConstant.CURRENT_APP_THEME, R.style.AppPinkTheme);
                SharedPreferencesUtil.saveData(AppConstant.IS_SHOW_COLOR_WELCOME, true);
                finish();
                break;
            case R.id.viewBlack://炫酷黑
                if (isThemeHasSelected(R.style.AppBlackTheme)) {
                    ToastMessage.toastWarn("该主题已经使用了哦，请选择其他主题...", false);
                    return;
                }
                setTheme(R.style.AppBlackTheme);
                //保存用户选择的主题
                SharedPreferencesUtil.saveData(AppConstant.CURRENT_APP_THEME, R.style.AppBlackTheme);
                SharedPreferencesUtil.saveData(AppConstant.IS_SHOW_COLOR_WELCOME, true);
                finish();
                break;
        }
    }

    //判断选择的主题是不是重复
    private boolean isThemeHasSelected(int theme) {
        if ((int) SharedPreferencesUtil.getData(AppConstant.CURRENT_APP_THEME, -1) == -1) {//值为-1表示用户从来没有设置过主题 所以使用默认的主题来判断
            if (theme == R.style.AppVioletTheme) {
                return true;
            } else {
                return false;
            }
        } else {//否则就从文件中取得值进行对比
            if (theme == (int) SharedPreferencesUtil.getData(AppConstant.CURRENT_APP_THEME, -1)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
