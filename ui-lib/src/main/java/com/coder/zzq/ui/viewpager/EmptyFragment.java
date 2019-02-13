package com.coder.zzq.ui.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coder.zzq.ui.R;

public class EmptyFragment extends Fragment {
    private static final String ARG_TIP = "tip";
    private Bundle mArgs;
    private TextView mTipView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_android_ui, container, false);
        mTipView = view.findViewById(R.id.tip);
        if (mArgs != null) {
            mTipView.setText(mArgs.getString(ARG_TIP));
        }
        return view;
    }


    public static EmptyFragment newInstance(String tip) {
        EmptyFragment emptyFragment = new EmptyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TIP, tip);
        return emptyFragment;
    }
}
