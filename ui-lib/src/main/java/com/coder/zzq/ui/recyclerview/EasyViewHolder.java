package com.coder.zzq.ui.recyclerview;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coder.zzq.ui.ViewCaster;

/**
 * Created by 小朱先森 on 2018/5/7.
 */

public class EasyViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mIdToView;

    private ViewCaster mViewCaster;

    private View.OnClickListener mOnItemClickListener;


    public EasyViewHolder(View itemView) {
        super(itemView);
        mIdToView = new SparseArray<>();
        mViewCaster = new ViewCaster();
    }

    private <T extends View> T findView(@IdRes int viewId,Class<T> clazz){
        T view = (T) mIdToView.get(viewId,null);
        if (view == null){
            view = (T) itemView.findViewById(viewId);
            mIdToView.put(viewId,view);
        }

        return view;
    }

    private View findView(@IdRes int viewId){
        View view = mIdToView.get(viewId,null);
        if (view == null){
            view = itemView.findViewById(viewId);
            mIdToView.put(viewId,view);
        }

        return view;

    }



    public void setText(@IdRes int viewId,CharSequence text) {
        TextView view  = findView(viewId,TextView.class);
        view.setText(text);
    }

    public void setText(@IdRes int viewId, @StringRes int text) {
        TextView view  = findView(viewId,TextView.class);
        view.setText(text);
    }


    public void setImg(@IdRes int viewId,Drawable drawable){
        ImageView imageView = findView(viewId,ImageView.class);
        imageView.setImageDrawable(drawable);
    }

    public void setImg(@IdRes int viewId, @DrawableRes int drawableRes){
        ImageView imageView = findView(viewId,ImageView.class);
        imageView.setImageResource(drawableRes);
    }

    public void setImg(@IdRes int viewId,Bitmap bitmap){
        ImageView imageView = findView(viewId,ImageView.class);
        imageView.setImageBitmap(bitmap);
    }


    public ViewCaster getView(@IdRes int idRes){
        View view = findView(idRes);
        mViewCaster.setView(view);
        return mViewCaster;
    }


    public void setOnItemClickListener(View.OnClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
        itemView.setOnClickListener(mOnItemClickListener);
    }




}
