package com.coder.zzq.ui.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coder.zzq.ui.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 小朱先森 on 2018/5/7.
 */

public abstract class BaseRecyclerViewAdapter<HeaderData, BodyDataItem, FooterData> extends RecyclerView.Adapter<EasyViewHolder> {
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_NORMAL_BODY = 1;
    public static final int ITEM_TYPE_FOOTER = 2;
    private HeaderData mHeaderData;
    private List<BodyDataItem> mBodyData;
    private FooterData mFooterData;

    public BaseRecyclerViewAdapter() {
        mBodyData = new ArrayList<>();
    }

    @LayoutRes
    private int itemViewLayout(int viewType) {
        switch (viewType) {
            case ITEM_TYPE_HEADER:
                return provideHeaderViewLayout();
            case ITEM_TYPE_FOOTER:
                return provideFooterViewLayout();
            default:
                return provideBodyViewLayout(viewType);
        }
    }

    @LayoutRes
    private int provideBodyViewLayout(int viewType) {
        switch (viewType) {
            case ITEM_TYPE_NORMAL_BODY:
                return provideNormalBodyViewLayout();
            default:
                return provideSpecialBodyViewLayout(viewType);
        }
    }

    @LayoutRes
    protected int provideSpecialBodyViewLayout(int viewType) {
        return R.layout.android_ui_empty_view;
    }

    @LayoutRes
    protected abstract int provideNormalBodyViewLayout();

    protected int provideFooterViewLayout() {
        return R.layout.android_ui_empty_view;
    }

    @LayoutRes
    protected int provideHeaderViewLayout() {
        return R.layout.android_ui_empty_view;
    }

    @LayoutRes
    protected int provideBodyViewType(int position) {
        return ITEM_TYPE_NORMAL_BODY;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && withHeader()) {
            return ITEM_TYPE_HEADER;
        } else if ((position == getItemCount() - 1) && withFooter()) {
            return ITEM_TYPE_FOOTER;
        } else {
            return provideBodyViewType(position);
        }
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemViewLayout(viewType), parent, false);
        EasyViewHolder viewHolder = new EasyViewHolder(itemView);
        afterViewHolderCreated(viewHolder);
        return viewHolder;
    }

    protected void afterViewHolderCreated(EasyViewHolder viewHolder) {

    }

    @Override
    public void onBindViewHolder(EasyViewHolder holder, int position) {
        if (position == 0 && withHeader()) {
            if (mHeaderData != null) {
                bindHeaderData(holder, mHeaderData);
            }
        } else if (position == getItemCount() - 1 && withFooter()) {
            if (mFooterData != null) {
                bindFooterData(holder, mFooterData);
            }
        } else {
            bindBodyData(holder, position);
        }
    }

    private void bindBodyData(EasyViewHolder holder, int position) {
        int bodyPos = position;
        if (withHeader()) {
            bodyPos = position - 1;
        }
        bindData(holder, position, bodyPos, mBodyData.get(bodyPos));
    }

    protected abstract void bindData(EasyViewHolder holder, int globalPos, int bodyPos, BodyDataItem bodyDataItem);

    protected void bindFooterData(EasyViewHolder viewHolder, FooterData footerData) {

    }

    protected void bindHeaderData(EasyViewHolder viewHolder, HeaderData headerData) {

    }

    @Override
    public int getItemCount() {
        return mBodyData.size() + (withHeader() ? 1 : 0) + (withFooter() ? 1 : 0);
    }

    protected boolean withHeader() {
        return false;
    }

    protected boolean withFooter() {
        return false;
    }

    public HeaderData getHeaderData() {
        return mHeaderData;
    }

    public void setHeaderData(HeaderData headerData) {
        if (withHeader()) {
            mHeaderData = headerData;
            notifyItemChanged(0);
        }
    }

    public List<BodyDataItem> getBodyData() {
        return mBodyData;
    }

    public void setBodyData(List<BodyDataItem> bodyData) {
        mBodyData.clear();
        if (bodyData != null) {
            mBodyData.addAll(bodyData);
        }
        notifyDataSetChanged();
    }

    public void setBodyData(BodyDataItem[] bodyData) {
        List<BodyDataItem> bodyDataItems = bodyData == null ? null : Arrays.asList(bodyData);
        setBodyData(bodyDataItems);
    }

    public void appendBodyData(List<BodyDataItem> bodyData) {
        if (bodyData != null && !bodyData.isEmpty()) {
            mBodyData.addAll(bodyData);
            notifyItemRangeInserted(mBodyData.size(), bodyData.size());
        }
    }

    public void appendBodyData(BodyDataItem[] bodyData) {
        List<BodyDataItem> bodyDataItems = bodyData == null ? null : Arrays.asList(bodyData);
        appendBodyData(bodyDataItems);
    }

    public FooterData getFooterData() {
        return mFooterData;
    }

    public void setFooterData(FooterData footerData) {
        if (withFooter()) {
            mFooterData = footerData;
            notifyItemChanged(getItemCount() - 1);
        }
    }
}
