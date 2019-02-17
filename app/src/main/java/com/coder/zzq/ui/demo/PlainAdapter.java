package com.coder.zzq.ui.demo;

import android.widget.TextView;

import com.coder.zzq.ui.recyclerview.BaseRecyclerViewAdapter;
import com.coder.zzq.ui.recyclerview.EasyViewHolder;

public class PlainAdapter extends BaseRecyclerViewAdapter<String, String, String> {
    @Override
    protected int provideNormalBodyViewLayout() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    protected void onBindBodyData(EasyViewHolder holder, int globalPos, int bodyPos, String s, int itemType) {
        holder.getRootViewAsTypeOf(TextView.class).setText(s);
    }

    @Override
    protected boolean withHeader() {
        return true;
    }

    @Override
    protected int provideHeaderViewLayout() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    protected void onBindHeaderData(EasyViewHolder viewHolder, String o) {
        viewHolder.getRootViewAsTypeOf(TextView.class).setText(o);
    }

    @Override
    protected boolean withFooter() {
        return true;
    }

    @Override
    protected int provideFooterViewLayout() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    protected void onBindFooterData(EasyViewHolder viewHolder, String o) {
        viewHolder.getRootViewAsTypeOf(TextView.class).setText(o);
    }
}
