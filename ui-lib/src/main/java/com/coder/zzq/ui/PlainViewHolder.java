package com.coder.zzq.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlainViewHolder implements IPlainViewHolder {

    private View mRootView;
    private SparseArray<ViewCaster> mIdToView;

    public PlainViewHolder(View rootView) {
        mRootView = rootView;
        mIdToView = new SparseArray<>();
    }

    private <T extends View> T findView(@IdRes int viewId, Class<T> clazz) {
        ViewCaster viewCaster = mIdToView.get(viewId, null);
        if (viewCaster == null) {
            viewCaster = new ViewCaster(mRootView.findViewById(viewId));
            mIdToView.put(viewId, viewCaster);
        }

        return viewCaster.toView(clazz);
    }

    private View findView(@IdRes int viewId) {
        return findView(viewId, View.class);
    }

    @Override
    public void setText(@IdRes int viewId, CharSequence text) {
        TextView view = findView(viewId, TextView.class);
        view.setText(text);
    }

    @Override
    public void setText(@IdRes int viewId, @StringRes int text) {
        TextView view = findView(viewId, TextView.class);
        view.setText(text);
    }

    @Override
    public void setImg(@IdRes int viewId, Drawable drawable) {
        ImageView imageView = findView(viewId, ImageView.class);
        imageView.setImageDrawable(drawable);
    }

    @Override
    public void setImg(@IdRes int viewId, @DrawableRes int drawableRes) {
        ImageView imageView = findView(viewId, ImageView.class);
        imageView.setImageResource(drawableRes);

    }

    @Override
    public void setImg(@IdRes int viewId, Bitmap bitmap) {
        ImageView imageView = findView(viewId, ImageView.class);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public ViewCaster getView(@IdRes int idRes) {
        View view = findView(idRes);
        return new ViewCaster(view);
    }


    @Override
    public View getViewAsPlain(int idRes) {
        return getView(idRes).asPlainView();
    }

    @Override
    public void setVisibility(@IdRes int viewId, int visibility) {
        findView(viewId).setVisibility(visibility);
    }

    @Override
    public View getRootViewAsPlain() {
        return mRootView;
    }

    @Override
    public <T> T getRootViewAsTypeOf(Class<T> viewType) {
        return viewType.cast(mRootView);
    }

    @Override
    public Context getContext() {
        return mRootView.getContext();
    }

    @Override
    public void setTextColorRes(int viewId, @ColorRes int colorRes) {
        TextView textView = findView(viewId, TextView.class);
        textView.setTextColor(ContextCompat.getColor(textView.getContext(), colorRes));
    }

    @Override
    public void setTextColor(int viewId, @ColorInt int color) {
        TextView textView = findView(viewId, TextView.class);
        textView.setTextColor(color);
    }

    @Override
    public String getText(int viewId) {
        TextView textView = findView(viewId, TextView.class);
        return textView.getText().toString();
    }
}
