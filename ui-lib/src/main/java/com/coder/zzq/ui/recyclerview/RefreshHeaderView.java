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
import java.util.Date;

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
        mPlainViewHolder.setText(R.id.refresh_tip, "正在刷新...");
        mPlainViewHolder.setVisibility(R.id.last_refresh_time, View.GONE);
        mPlainViewHolder.setVisibility(R.id.to_refresh_mark, View.GONE);
        mPlainViewHolder.setVisibility(R.id.refreshing_mark, VISIBLE);

        return;
    }

    private boolean mPrepared;

    @Override
    public void onPrepare() {
        mPlainViewHolder.setText(R.id.refresh_tip, "下拉可以刷新");
        mPlainViewHolder.setVisibility(R.id.last_refresh_time, View.VISIBLE);
        mPlainViewHolder.setText(R.id.last_refresh_time, "上次刷新：" + mLastRefreshTime);
        mPlainViewHolder.setVisibility(R.id.complete_mark, GONE);
        mPlainViewHolder.setVisibility(R.id.refreshing_mark, GONE);
        mPlainViewHolder.setVisibility(R.id.to_refresh_mark, VISIBLE);
        mPlainViewHolder.setImg(R.id.to_refresh_mark, R.drawable.android_ui_pull_can_refresh);
        mPrepared = true;
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {

        if (!mPrepared) {
            mPlainViewHolder.setText(R.id.refresh_tip, "下拉可以刷新");
            mPlainViewHolder.setVisibility(R.id.last_refresh_time, View.VISIBLE);
            mPlainViewHolder.setText(R.id.last_refresh_time, "上次刷新：" + mLastRefreshTime);
            mPlainViewHolder.setVisibility(R.id.complete_mark, GONE);
            mPlainViewHolder.setVisibility(R.id.refreshing_mark, GONE);
            mPlainViewHolder.setVisibility(R.id.to_refresh_mark, VISIBLE);
            mPlainViewHolder.setImg(R.id.to_refresh_mark, R.drawable.android_ui_pull_can_refresh);
            mPrepared = true;
        }

        if (yScrolled <= getHeight() && !isComplete) {

            mPlainViewHolder.setText(R.id.refresh_tip, "下拉可以刷线");
            mPlainViewHolder.setText(R.id.last_refresh_time, "上次刷新：" + mLastRefreshTime);
            mPlainViewHolder.setImg(R.id.to_refresh_mark, R.drawable.android_ui_pull_can_refresh);
            return;
        }

        if (yScrolled > getHeight() && !isComplete) {
            mPlainViewHolder.setText(R.id.refresh_tip, "松开立即刷新");
            mPlainViewHolder.setText(R.id.last_refresh_time, "上次刷新：" + mLastRefreshTime);
            mPlainViewHolder.setImg(R.id.to_refresh_mark, R.drawable.android_ui_release_to_refresh);
            return;
        }


    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        mPlainViewHolder.setText(R.id.refresh_tip, "刷新完成");
        mLastRefreshTime = mSimpleDateFormat.format(new Date());
        mPlainViewHolder.setVisibility(R.id.last_refresh_time, VISIBLE);
        mPlainViewHolder.setText(R.id.last_refresh_time, "更新于：" + mLastRefreshTime);
        mPlainViewHolder.setVisibility(R.id.refreshing_mark, GONE);
        mPlainViewHolder.setVisibility(R.id.complete_mark, VISIBLE);

    }

    @Override
    public void onReset() {
        mPlainViewHolder.setText(R.id.refresh_tip, "下拉可以刷新");
        mPlainViewHolder.setVisibility(R.id.last_refresh_time, View.VISIBLE);
        mPlainViewHolder.setText(R.id.last_refresh_time, "上次刷新：" + mLastRefreshTime);
        mPlainViewHolder.setVisibility(R.id.complete_mark, GONE);
        mPlainViewHolder.setVisibility(R.id.refreshing_mark, GONE);
        mPlainViewHolder.setVisibility(R.id.to_refresh_mark, VISIBLE);
        mPlainViewHolder.setImg(R.id.to_refresh_mark, R.drawable.android_ui_pull_can_refresh);
        mPrepared = false;
    }
}
