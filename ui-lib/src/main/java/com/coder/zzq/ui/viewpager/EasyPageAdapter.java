package com.coder.zzq.ui.viewpager;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class EasyPageAdapter<DATA> extends PagerAdapter {
    private List<View> mCachedPageViews = new ArrayList<>();
    private List<DATA> mPageData = new ArrayList<>();

    @Override
    public int getCount() {
        return mPageData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View pageView = null;
        if (mCachedPageViews.isEmpty()) {
            pageView = LayoutInflater.from(container.getContext()).inflate(providePageLayout(), container, false);
        } else {
            pageView = mCachedPageViews.remove(0);
        }
        updateData(container, pageView, position, mPageData);
        container.addView(pageView);
        return pageView;
    }

    protected abstract void updateData(ViewGroup container, View pageView, int position, List<DATA> pageData);

    @LayoutRes
    protected abstract int providePageLayout();

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View pageView = (View) object;
        container.removeView(pageView);
        mCachedPageViews.add(pageView);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public void setData(DATA[] data) {
        List<DATA> dataList = data == null ? null : Arrays.asList(data);
        setData(dataList);
    }

    public void setData(List<DATA> data) {
        mPageData.clear();
        if (data != null) {
            mPageData.addAll(data);
        }
        notifyDataSetChanged();
    }
}
