package com.coder.zzq.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

public interface IPlainViewHolder {
    void setText(@IdRes int viewId, CharSequence text);

    void setText(@IdRes int viewId, @StringRes int text);


    void setImg(@IdRes int viewId, Drawable drawable);

    void setImg(@IdRes int viewId, @DrawableRes int drawableRes);

    void setImg(@IdRes int viewId, Bitmap bitmap);


    ViewCaster getView(@IdRes int idRes);

    void setVisibility(@IdRes int viewId, int visibility);

    View getRootView();

    <T> T parseRootView(Class<T> viewType);

    Context getContext();

    void setTextColorRes(int viewId, @ColorRes int colorRes);

    void setTextColor(int viewId, @ColorInt int color);
}
