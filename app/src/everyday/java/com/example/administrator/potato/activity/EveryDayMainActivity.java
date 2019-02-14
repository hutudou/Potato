package com.example.administrator.potato.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.R;
import com.example.administrator.potato.adapter.IndexPagerAdapter;
import com.example.administrator.potato.fragment.HistoryFragment;
import com.example.administrator.potato.fragment.RecordFragment;
import com.example.administrator.potato.fragment.SecretFragment;
import com.example.administrator.potato.fragment.WeatherFragment;
import com.example.administrator.potato.service.GetLocationService;
import com.example.administrator.potato.utils.BottomNavigationViewUtil;
import com.example.administrator.potato.utils.SharedPreferencesUtil;
import com.example.administrator.potato.utils.ToastMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EveryDayMainActivity extends BaseActivity {

    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @Bind(R.id.navigationView)
    NavigationView navigationView;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private boolean isExit = false;
    private Handler handler;
    private List<Fragment> fragmentList;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day_main);
        ButterKnife.bind(this);
        showColorWelcome();
        initView();
        startAllServices();
        //onCreate只会走一次 所以只有 每日天气 那里的toolBar会被隐藏
        toolbar.setVisibility(View.GONE);
        initData();
    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        stopAllServices();
    }

    private void stopAllServices() {
        stopService(new Intent(mContext, GetLocationService.class));
    }

    //开启app中所有服务
    private void startAllServices() {
        startGetLocationServices();
    }

    private void startGetLocationServices() {
        Intent intent = new Intent(mContext, GetLocationService.class);
        startService(intent);
    }

    //更换颜色时显示颜色专属台词
    private void showColorWelcome() {
        if ((boolean) SharedPreferencesUtil.getData(AppConstant.IS_SHOW_COLOR_WELCOME, false)) {
            switch ((int) SharedPreferencesUtil.getData(AppConstant.CURRENT_APP_THEME, -1)) {
                case R.style.AppBlueTheme:
                    showSnackBar(toolbar, "如果梦想有颜色，那一定是蓝色!!!", true, null, null);
                    break;
                case R.style.AppVioletTheme:
                    showSnackBar(toolbar, "选择紫色，选择一种优雅的人生。。。", true, null, null);
                    break;
                case R.style.AppRedTheme:
                    showSnackBar(toolbar, "我的主题色，欢乐欢乐最欢乐!!!", true, null, null);
                    break;
                case R.style.AppOrangeTheme:
                    showSnackBar(toolbar, "年轻，就该追求无限的活力!!!", true, null, null);
                    break;
                case R.style.AppPinkTheme:
                    showSnackBar(toolbar, "谁还不是个小可爱呢，嘤嘤嘤", true, null, null);
                    break;
                case R.style.AppBlackTheme:
                    showSnackBar(toolbar, "没人可以比我更酷！！！", true, null, null);
                    break;
                default:
                    showSnackBar(toolbar, "选择紫色，选择一种优雅的人生", true, null, null);
                    break;
            }
            SharedPreferencesUtil.saveData(AppConstant.IS_SHOW_COLOR_WELCOME, false);
        }
    }

    @Override
    protected void initView() {
        handler = new Handler();
        initToolBar(toolbar, "EveryDay", false, null, true, R.menu.app_toolbar_menu, new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemRefresh:
                        break;
                }
                return true;
            }
        });

        BottomNavigationViewUtil.disableShiftMode(bottomNavigationView);
        initNavigationView();
        initBottomNavigationView();
        //设置navigationView的子项点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.position:
                        ToastMessage.toastWarn("功能正在开发中...", true);
                        break;
                    case R.id.clearCache:
                        ToastMessage.toastWarn("功能正在开发中...", true);
                        break;
                    case R.id.changeTheme:
                        gotoActivity(ChangeThemeActivity.class, false);
                        break;
                    case R.id.aboutEveryDay:
                        ToastMessage.toastWarn("功能正在开发中...", true);
                        break;
                    case R.id.aboutAuthor:
                        ToastMessage.toastWarn("功能正在开发中...", true);
                        break;
                    case R.id.modifyPassword:
                        ToastMessage.toastWarn("功能正在开发中...", true);
                        break;
                    case R.id.switchUser:
                        ToastMessage.toastWarn("功能正在开发中...", true);
                        break;
                    case R.id.exit:
                        ToastMessage.toastWarn("功能正在开发中...", true);
                        break;
                }
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    toolbar.setVisibility(View.GONE);
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                if (position == 0) {
                    toolbar.setVisibility(View.GONE);
                } else {
                    toolbar.setVisibility(View.VISIBLE);

                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //绑定viewPager
        fragmentList = new ArrayList<>();
        fragmentList.add(WeatherFragment.newInstance("每日天气"));
        fragmentList.add(new HistoryFragment());
        fragmentList.add(RecordFragment.newInstance("每日一记"));
        fragmentList.add(SecretFragment.newInstance("小小树洞"));
        IndexPagerAdapter indexPagerAdapter = new IndexPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(indexPagerAdapter);
    }

    //初始化BottomNavigationView
    private void initBottomNavigationView() {
        //监听item切换事件
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.weather:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.history:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.note:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.secret:
                        viewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });
    }

    private void initNavigationView() {
        //将toolBar和navigation事件关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation_view, R.string.close_navigation_view);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //更改toolBar和navigation时左侧的图标
        toolbar.setNavigationIcon(R.drawable.icon_vector_menu);
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {//物理返回键点击事件  需要在两秒内按两次返回键才能退出
        if (isExit) {
            finish();
        } else {
            isExit = true;
            showSnackBar(toolbar, "再按一次退出App", true, "退出App", new ISnackBarClickEvent() {
                @Override
                public void clickEvent() {
                    finish();
                }
            });
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        }
    }

}
