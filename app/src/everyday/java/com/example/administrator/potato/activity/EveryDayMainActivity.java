package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.potato.R;
import com.example.administrator.potato.fragment.HistoryFragment;
import com.example.administrator.potato.utils.BottomNavigationViewUtil;
import com.example.administrator.potato.utils.ToastMessage;

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
    private boolean isNeed = true;
    private boolean isExit = false;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void initView() {
        handler = new Handler();
        initToolBar(toolbar, "EveryDay", false, null, true, R.menu.app_toolbar_menu, new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemRefresh:
                       /* if (isNeed) {
                            toolbar.setBackgroundColor(Color.RED);
                            isNeed = false;
                        } else {
                            toolbar.setBackgroundColor(Color.parseColor("#66b3ff"));
                            isNeed = true;
                        }*/
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
                    case R.id.scan:
                        ToastMessage.toastWarn("功能正在开发中...", true);
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
    }

    //初始化BottomNavigationView
    private void initBottomNavigationView() {
        //监听item切换事件
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.weather:
                        ToastMessage.toastNormal("每日天气");
                        return true;
                    case R.id.history:
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.linearFragment, new HistoryFragment());
                        transaction.commit();
                        return true;
                    case R.id.note:
                        ToastMessage.toastNormal("每日一记");
                        return true;
                    case R.id.secret:
                        ToastMessage.toastWarn("功能正在完善中...", true);
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
            showSnackBar(toolbar, "再按一次退出App", true, null, null);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        }
    }
}
