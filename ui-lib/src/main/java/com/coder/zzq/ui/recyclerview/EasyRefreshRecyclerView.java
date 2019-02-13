package com.coder.zzq.ui.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.coder.zzq.ui.R;

public class EasyRefreshRecyclerView extends FrameLayout {
    private EasyRecyclerView mEasyRecyclerView;

    public EasyRefreshRecyclerView(Context context) {
        this(context, null);
    }

    public EasyRefreshRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyRefreshRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.android_ui_easy_refresh_recycler_view, this, true);
        mEasyRecyclerView = findViewById(R.id.easy_recycler_view);
    }


    public EasyRecyclerView getRecyclerView() {
        return mEasyRecyclerView;
    }
}
