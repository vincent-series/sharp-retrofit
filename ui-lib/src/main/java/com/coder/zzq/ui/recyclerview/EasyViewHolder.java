package com.coder.zzq.ui.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
        ImageView imageView = getView(viewId).toImgView();
        Glide.with(imageView.getContext())
                .load(drawable)
                .into(imageView);
    }

    @Override
    public void setImg(int viewId, int drawableRes) {
        ImageView imageView = getView(viewId).toImgView();
        Glide.with(imageView.getContext())
                .load(drawableRes)
                .into(imageView);
    }

    @Override
    public void setImg(int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId).toImgView();
        Glide.with(imageView.getContext())
                .load(bitmap)
                .into(imageView);
    }

    @Override
    public ViewCaster getView(int idRes) {
        return mPlainViewHolder.getView(idRes);
    }

    @Override
    public View getViewAsPlain(int idRes) {
        return mPlainViewHolder.getViewAsPlain(idRes);
    }

    @Override
    public void setVisibility(int viewId, int visibility) {
        mPlainViewHolder.setVisibility(viewId, visibility);
    }

    @Override
    public View getRootViewAsPlain() {
        return mPlainViewHolder.getRootViewAsPlain();
    }

    @Override
    public <T> T getRootViewAsTypeOf(Class<T> viewType) {
        return mPlainViewHolder.getRootViewAsTypeOf(viewType);
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

    @Override
    public String getText(int viewId) {
        return mPlainViewHolder.getText(viewId);
    }
}
