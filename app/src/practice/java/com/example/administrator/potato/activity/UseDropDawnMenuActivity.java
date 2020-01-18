package com.example.administrator.potato.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.potato.R;
import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author potato
 */
public class UseDropDawnMenuActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @Bind(R.id.text)
    TextView text;
    private String[] constellation = {"白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
    private String[] zodiac = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    private String selectConstellation;
    private String selectZodiac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_drop_dawn_menu);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initToolBar(toolbar, "使用DropDownMenu", true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String[] defaultTitle = {"星座", "生肖"};
        //Menu的个数
        dropDownMenu.setmMenuCount(2);
        //Menu展开list数量太多是只显示的个数
        dropDownMenu.setmShowCount(12);
        //是否显示展开list的选中项
        dropDownMenu.setShowCheck(true);
        //Menu的文字大小
        dropDownMenu.setmMenuTitleTextSize(16);
        //Menu的文字颜色
        dropDownMenu.setmMenuTitleTextColor(ContextCompat.getColor(mContext, R.color.black));
        //Menu展开list的文字大小
        dropDownMenu.setmMenuListTextSize(16);
        dropDownMenu.setShowDivider(true);
        //Menu展开list的文字颜色
        dropDownMenu.setmMenuListTextSize(ContextCompat.getColor(mContext, R.color.black));
        //Menu的背景颜色
        dropDownMenu.setmMenuBackColor(ContextCompat.getColor(mContext, R.color.white));
        //Menu按下的背景颜色
        dropDownMenu.setmMenuPressedBackColor(ContextCompat.getColor(mContext, R.color.bg_color));
        //Menu展开list的勾选图片
        dropDownMenu.setmCheckIcon(R.drawable.ico_make);
        //Menu默认状态的箭头
        dropDownMenu.setmUpArrow(R.drawable.arrow_up);
        //Menu按下状态的箭头
        dropDownMenu.setmDownArrow(R.drawable.arrow_down);
        //默认未选择任何过滤的Menu title
        dropDownMenu.setDefaultMenuTitle(defaultTitle);
        dropDownMenu.setmArrowMarginTitle(20);
        dropDownMenu.setMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onSelected(View view, int i, int i1) {
                switch (i1) {
                    case 0:
                        selectConstellation = constellation[i];
                        break;
                    case 1:
                        selectZodiac = zodiac[i];
                        break;
                    default:
                        break;
                }
                text.setText(String.format("你选择的星座是:%s\n你选择的生肖是:%s", selectConstellation, selectZodiac));
            }
        });
        List<String[]> items = new ArrayList<>();
        items.add(constellation);
        items.add(zodiac);
        dropDownMenu.setmMenuItems(items);
        dropDownMenu.setIsDebug(false);

    }

    @Override
    protected void initData() {

    }
}
