package com.example.administrator.potato.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.potato.R;

/**
 * 作者:土豆
 * 创建日期:2018/9/11
 * 邮箱:1401552353@qq.com
 */

public class TabLayoutFragmentFir extends android.support.v4.app.Fragment {
    private View mView;

    public static TabLayoutFragmentFir newsInstance(){
        return new TabLayoutFragmentFir();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_tablayout_fir,null);
        return mView;
    }
}
