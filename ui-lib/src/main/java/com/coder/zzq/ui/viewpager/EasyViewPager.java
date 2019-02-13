package com.coder.zzq.ui.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.coder.zzq.ui.R;

public class EasyViewPager extends ViewPager {
    private boolean mIsFixed;

    public EasyViewPager(@NonNull Context context) {
        super(context);
    }

    public EasyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.EasyViewPager);
        mIsFixed = attrArray.getBoolean(R.styleable.EasyViewPager_evp_fixed, false);
        attrArray.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mIsFixed) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mIsFixed) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isFixed() {
        return mIsFixed;
    }

    public void setFixed(boolean fixed) {
        mIsFixed = fixed;
    }

}
