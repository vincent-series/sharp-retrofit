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

public class PlainViewHolder {

    private View mRootView;
    private SparseArray<View> mIdToView;
    private ViewCaster mViewCaster;

    public PlainViewHolder(View rootView) {
        mRootView = rootView;
        mIdToView = new SparseArray<>();
        mViewCaster = new ViewCaster();
    }

    private <T extends View> T findView(@IdRes int viewId, Class<T> clazz) {
        T view = (T) mIdToView.get(viewId, null);
        if (view == null) {
            view = (T) mRootView.findViewById(viewId);
            mIdToView.put(viewId, view);
        }

        return view;
    }

    private View findView(@IdRes int viewId) {
        View view = mIdToView.get(viewId, null);
        if (view == null) {
            view = mRootView.findViewById(viewId);
            mIdToView.put(viewId, view);
        }

        return view;

    }


    public void setText(@IdRes int viewId, CharSequence text) {
        TextView view = findView(viewId, TextView.class);
        view.setText(text);
    }

    public void setText(@IdRes int viewId, @StringRes int text) {
        TextView view = findView(viewId, TextView.class);
        view.setText(text);
    }


    public void setImg(@IdRes int viewId, Drawable drawable) {
        ImageView imageView = findView(viewId, ImageView.class);
        imageView.setImageDrawable(drawable);
    }

    public void setImg(@IdRes int viewId, @DrawableRes int drawableRes) {
        ImageView imageView = findView(viewId, ImageView.class);
        imageView.setImageResource(drawableRes);
    }

    public void setImg(@IdRes int viewId, Bitmap bitmap) {
        ImageView imageView = findView(viewId, ImageView.class);
        imageView.setImageBitmap(bitmap);
    }


    public ViewCaster getView(@IdRes int idRes) {
        View view = findView(idRes);
        mViewCaster.setView(view);
        return mViewCaster;
    }

    public void setVisibility(@IdRes int viewId, int visibility) {
        findView(viewId).setVisibility(visibility);
    }

    public View getRootView() {
        return mRootView;
    }

    public <T> T parseRootView(Class<T> viewType){
        return viewType.cast(mRootView);
    }

    public Context getContext(){
        return mRootView.getContext();
    }

    public void setTextColorRes(int viewId, @ColorRes int colorRes) {
        TextView textView = findView(viewId,TextView.class);
        textView.setTextColor(ContextCompat.getColor(textView.getContext(),colorRes));
    }

    public void setTextColor(int viewId, @ColorInt int color) {
        TextView textView = findView(viewId,TextView.class);
        textView.setTextColor(color);
    }
}
