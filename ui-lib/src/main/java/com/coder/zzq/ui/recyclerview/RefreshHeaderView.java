package com.coder.zzq.ui.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.coder.zzq.ui.PlainViewHolder;
import com.coder.zzq.ui.R;

import java.text.SimpleDateFormat;

public class RefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {
    private SimpleDateFormat mSimpleDateFormat;
    private String mLastRefreshTime = "无记录";
    private PlainViewHolder mPlainViewHolder;

    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSimpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        mPlainViewHolder = new PlainViewHolder(this);
        LayoutInflater.from(context).inflate(R.layout.android_ui_refresh_header, this, true);

    }

    @Override
    public void onRefresh() {
        mPlainViewHolder.setVisibility(R.id.refreshing_mark, View.VISIBLE);
        mPlainViewHolder.setVisibility(R.id.refreshing_tip,View.VISIBLE);
        mPlainViewHolder.setVisibility(R.id.refresh_mark, View.GONE);
        mPlainViewHolder.setVisibility(R.id.refresh_tip_part,View.GONE);
        return;
    }


    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {


        if (yScrolled <= getHeight() && !isComplete) {
            mPlainViewHolder.setVisibility(R.id.refresh_mark, View.VISIBLE);
            mPlainViewHolder.setVisibility(R.id.refresh_tip_part, View.VISIBLE);
            mPlainViewHolder.setText(R.id.refresh_tip, "下拉可以刷新");
            mPlainViewHolder.setImg(R.id.refresh_mark, R.drawable.android_ui_pull_can_refresh);
            mPlainViewHolder.setVisibility(R.id.refreshing_mark, View.GONE);
            mPlainViewHolder.setVisibility(R.id.refreshing_tip, View.GONE);
            return;
        }

        if (yScrolled > getHeight() && !isComplete) {
            mPlainViewHolder.setVisibility(R.id.refresh_mark, View.VISIBLE);
            mPlainViewHolder.setVisibility(R.id.refresh_tip_part, View.VISIBLE);
            mPlainViewHolder.setText(R.id.refresh_tip, "松开立即刷新");
            mPlainViewHolder.setImg(R.id.refresh_mark, R.drawable.android_ui_release_to_refresh);
            mPlainViewHolder.setVisibility(R.id.refreshing_mark, View.GONE);
            mPlainViewHolder.setVisibility(R.id.refreshing_tip, View.GONE);
            return;
        }


    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {
//        mPlainViewHolder.setText(R.id.refresh_tip, "下拉可以刷新");
//        mPlainViewHolder.setVisibility(R.id.last_refresh_time, View.VISIBLE);
//        mPlainViewHolder.setText(R.id.last_refresh_time, "上次刷新：" + mLastRefreshTime);
//        mPlainViewHolder.setVisibility(R.id.complete_mark, GONE);
//        mPlainViewHolder.setVisibility(R.id.refreshing_mark, GONE);
//        mPlainViewHolder.setVisibility(R.id.to_refresh_mark, VISIBLE);
//        mPlainViewHolder.setImg(R.id.to_refresh_mark, R.drawable.android_ui_pull_can_refresh);
//        mPrepared = false;
    }
}
