package com.example.administrator.potato.recycler.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.administrator.potato.holder.BaseRecyclerViewHolder;

import java.util.List;

public abstract class CommonRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T, BaseRecyclerViewHolder> {

    private final int mItemLayoutId;

    public CommonRecyclerViewAdapter(Context context, int itemLayoutId) {
        super(context);
        this.mItemLayoutId = itemLayoutId;
    }

    public CommonRecyclerViewAdapter(Context context, int itemLayoutId, List<T> list) {
        super(context, list);
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public BaseRecyclerViewHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(parent, mItemLayoutId);
    }

    @Override
    public void bindCustomViewHolder(BaseRecyclerViewHolder holder, int position) {
        convert(holder, getItem(position), position);
    }

    @Override
    public int getCustomViewType(int position) {
        return 0;
    }

    public abstract void convert(BaseRecyclerViewHolder holder, T item, int position);
}
