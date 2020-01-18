package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.potato.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 * @date 2020/1/18
 */
public class IMusicMainActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigationView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imusic_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText(getString(R.string.main_title));
        initNavigationView();
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化侧边栏
     */
    private void initNavigationView() {
        //将toolBar和navigation事件关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //更改toolBar和navigation时左侧的图标
        toolbar.setNavigationIcon(R.drawable.icon_vector_menu);
        //设置头部view
        View headView = navigationView.getHeaderView(0);
        if (headView != null) {
            TextView textNickName = headView.findViewById(R.id.textNickName);
//            textNickName.setText((String) SharedPreferencesUtil.getData(AppConstant.NICKNAME, "EveryDay用户"));
            TextView textIntroduce = headView.findViewById(R.id.textIntroduce);
//            textIntroduce.setText((String) SharedPreferencesUtil.getData(AppConstant.INTRODUCE, "欢迎使用EveryDay,祝您用得开心!!!"));
        }
    }
}
