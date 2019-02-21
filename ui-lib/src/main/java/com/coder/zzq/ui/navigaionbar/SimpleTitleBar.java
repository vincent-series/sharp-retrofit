package com.coder.zzq.ui.navigaionbar;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coder.zzq.ui.R;

public class SimpleTitleBar extends RelativeLayout {
    private ImageView mBarNavIcon;
    private TextView mBarTitle;
    @DrawableRes
    private int mIconRes;
    public SimpleTitleBar(Context context) {
        this(context,null);
    }

    public SimpleTitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.android_ui_simple_title_bar,this,true);
        mBarNavIcon = (ImageView) findViewById(R.id.bar_nav_icon);
        mBarTitle = (TextView) findViewById(R.id.bar_title);
    }


    public void setOnNavIconClickListener(OnClickListener listener){
        mBarNavIcon.setOnClickListener(listener);
    }

    public void setNavIcon(@DrawableRes int iconRes){
        mIconRes = iconRes;
        mBarNavIcon.setImageResource(mIconRes);
    }
    @DrawableRes
    public int getNavIcon(){
        ;return mIconRes;
    }

    public void setTitle(CharSequence title){
        mBarTitle.setText(title);
    }

    public void setTitleSizeSp(float sp){
        mBarTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,sp);
    }

    public void setTitleColorRes(@ColorRes int colorRes){
//        setTitleColor(ResUtils.getColor(colorRes));
    }

    public void setTitleColor(@ColorInt int color){
        mBarTitle.setTextColor(color);
    }
}
