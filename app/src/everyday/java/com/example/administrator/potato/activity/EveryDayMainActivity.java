package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.potato.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "EveryDay", false, null, true, R.menu.app_toolbar_menu, new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        initNavigationView();
        initBottomNavigationView();
    }

    //初始化BottomNavigationView
    private void initBottomNavigationView() {
        //监听item切换事件
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.test1:
                        ToastMessage.toastNormal("第一部分");
                        return true;
                    case R.id.test2:
                        ToastMessage.toastNormal("第二部分");
                        return true;
                    case R.id.test3:
                        ToastMessage.toastNormal("第三部分");
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
}
