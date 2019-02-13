package com.coder.zzq.ui.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小朱先森 on 2018/5/7.
 */

public abstract class BaseAdapter<DataItem> extends RecyclerView.Adapter<EasyViewHolder> {

    private List<DataItem> mData;

    public BaseAdapter() {
        mData = new ArrayList<>();
    }

    @LayoutRes
    public abstract int itemViewLayout(int viewType);

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemViewLayout(viewType), parent, false);
        EasyViewHolder viewHolder = new EasyViewHolder(itemView);
        afterViewHolderCreated(viewHolder);
        return viewHolder;
    }

    protected void afterViewHolderCreated(EasyViewHolder viewHolder) {

    }

    @Override
    public void onBindViewHolder(EasyViewHolder holder, int position) {
        DataItem item = mData.get(position);
        bindDataToViewHolder(holder, position, item);
    }

    protected abstract void bindDataToViewHolder(EasyViewHolder holder, int position, DataItem item);

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
