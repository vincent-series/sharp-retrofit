package com.coder.zzq.ui.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.coder.zzq.ui.PlainViewHolder;
import com.coder.zzq.ui.R;

public class LoadMoreFooterView extends RelativeLayout implements SwipeTrigger, SwipeLoadMoreTrigger {
    private PlainViewHolder mPlainViewHolder;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPlainViewHolder = new PlainViewHolder(this);
        LayoutInflater.from(context).inflate(R.layout.android_ui_load_more_footer, this, true);
    }


    @Override
    public void onLoadMore() {
        mPlainViewHolder.setVisibility(R.id.loading_more_tip, View.VISIBLE);
        mPlainViewHolder.setVisibility(R.id.loading_more_mark, View.VISIBLE);
        mPlainViewHolder.setVisibility(R.id.load_more_tip, View.GONE);
        mPlainViewHolder.setVisibility(R.id.load_more_mark, View.GONE);
        mPlainViewHolder.setText(R.id.loading_more_tip, "正在加载更多...");
    }

    @Override
    public void onPrepare() {
        mPlainViewHolder.setVisibility(R.id.loading_more_tip, View.GONE);
        mPlainViewHolder.setVisibility(R.id.loading_more_mark, View.GONE);
        mPlainViewHolder.setVisibility(R.id.load_more_tip, View.VISIBLE);
        mPlainViewHolder.setVisibility(R.id.load_more_mark, View.VISIBLE);
        mPlainViewHolder.setText(R.id.load_more_tip, "上拉可以加载");
        mPlainViewHolder.setImg(R.id.load_more_mark, R.drawable.android_ui_release_to_refresh);
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (yScrolled >= -getHeight() && !isComplete) {
            mPlainViewHolder.setVisibility(R.id.loading_more_tip, View.GONE);
            mPlainViewHolder.setVisibility(R.id.loading_more_mark, View.GONE);
            mPlainViewHolder.setVisibility(R.id.load_more_tip, View.VISIBLE);
            mPlainViewHolder.setVisibility(R.id.load_more_mark, View.VISIBLE);
            mPlainViewHolder.setText(R.id.load_more_tip, "上拉可以加载");
            mPlainViewHolder.setImg(R.id.load_more_mark, R.drawable.android_ui_release_to_refresh);
            return;
        }

        if (yScrolled < -getHeight() && !isComplete) {
            mPlainViewHolder.setVisibility(R.id.loading_more_tip, View.GONE);
            mPlainViewHolder.setVisibility(R.id.loading_more_mark, View.GONE);
            mPlainViewHolder.setVisibility(R.id.load_more_tip, View.VISIBLE);
            mPlainViewHolder.setVisibility(R.id.load_more_mark, View.VISIBLE);
            mPlainViewHolder.setText(R.id.load_more_tip, "释放立即加载");
            mPlainViewHolder.setImg(R.id.load_more_mark, R.drawable.android_ui_pull_can_refresh);
            return;
        }

    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        mPlainViewHolder.setVisibility(R.id.loading_more_tip, View.GONE);
        mPlainViewHolder.setVisibility(R.id.loading_more_mark, View.GONE);
        mPlainViewHolder.setVisibility(R.id.load_more_tip, View.VISIBLE);
        mPlainViewHolder.setVisibility(R.id.load_more_mark, View.VISIBLE);
        mPlainViewHolder.setText(R.id.load_more_tip, "加载完成");
        mPlainViewHolder.setImg(R.id.load_more_mark, R.drawable.android_ui_complete);
    }

    @Override
    public void onReset() {

    }
}
