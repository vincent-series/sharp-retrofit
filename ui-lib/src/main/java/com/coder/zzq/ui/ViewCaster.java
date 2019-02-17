package com.coder.zzq.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 小朱先森 on 2018/5/7.
 */

public class ViewCaster {
    private View mView;

    public ViewCaster(View view) {
        mView = view;
    }

    public <T> T toView(Class<T> viewType) {
        return viewType.cast(mView);
    }

    public TextView toTextView() {
        return (TextView) mView;
    }

    public ImageView toImgView() {
        return (ImageView) mView;
    }

    public View asPlainView() {
        return mView;
    }
}
