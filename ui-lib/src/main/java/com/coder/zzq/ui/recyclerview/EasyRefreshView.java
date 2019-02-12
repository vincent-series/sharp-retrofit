package com.coder.zzq.ui.recyclerview;

import android.content.Context;
import android.util.AttributeSet;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

public class EasyRefreshView extends SwipeToLoadLayout {
    private SwipeLoadMoreTrigger
    public EasyRefreshView(Context context) {
        this(context, null);
    }

    public EasyRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addRecyclerView();
    }

    private void addRecyclerView() {

    }
}
