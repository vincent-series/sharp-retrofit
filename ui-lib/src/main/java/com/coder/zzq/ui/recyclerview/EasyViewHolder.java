package com.coder.zzq.ui.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.coder.zzq.ui.IPlainViewHolder;
import com.coder.zzq.ui.PlainViewHolder;
import com.coder.zzq.ui.ViewCaster;

/**
 * Created by 小朱先森 on 2018/5/7.
 */

public class EasyViewHolder extends RecyclerView.ViewHolder implements IPlainViewHolder {
    private PlainViewHolder mPlainViewHolder;
    private View.OnClickListener mOnItemClickListener;


    public EasyViewHolder(View itemView) {
        super(itemView);
        mPlainViewHolder = new PlainViewHolder(itemView);
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        itemView.setOnClickListener(mOnItemClickListener);
    }


    @Override
    public void setText(int viewId, CharSequence text) {
        mPlainViewHolder.setText(viewId, text);
    }

    @Override
    public void setText(int viewId, int text) {
        mPlainViewHolder.setText(viewId, text);
    }

    @Override
    public void setImg(int viewId, Drawable drawable) {
        mPlainViewHolder.setImg(viewId, drawable);
    }

    @Override
    public void setImg(int viewId, int drawableRes) {
        mPlainViewHolder.setImg(viewId, drawableRes);
    }

    @Override
    public void setImg(int viewId, Bitmap bitmap) {
        mPlainViewHolder.setImg(viewId, bitmap);
    }

    @Override
    public ViewCaster getView(int idRes) {
        return mPlainViewHolder.getView(idRes);
    }

    @Override
    public void setVisibility(int viewId, int visibility) {
        mPlainViewHolder.setVisibility(viewId, visibility);
    }

    @Override
    public View getRootView() {
        return mPlainViewHolder.getRootView();
    }

    @Override
    public <T> T parseRootView(Class<T> viewType) {
        return mPlainViewHolder.parseRootView(viewType);
    }

    @Override
    public Context getContext() {
        return mPlainViewHolder.getContext();
    }

    @Override
    public void setTextColorRes(int viewId, int colorRes) {
        mPlainViewHolder.setTextColorRes(viewId, colorRes);
    }

    @Override
    public void setTextColor(int viewId, int color) {
        mPlainViewHolder.setTextColor(viewId, color);
    }
}
