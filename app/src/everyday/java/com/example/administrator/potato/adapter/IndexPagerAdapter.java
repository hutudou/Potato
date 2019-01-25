package com.example.administrator.potato.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者 Administrator
 * 时间 2019/1/24
 */

public class IndexPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager manager;
    private List<Fragment> mList;

    public IndexPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.manager = fm;
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
