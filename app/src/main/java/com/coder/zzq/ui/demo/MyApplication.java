package com.coder.zzq.ui.demo;

import android.app.Application;

import com.coder.zzq.smartshow.core.SmartShow;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SmartShow.init(this);
    }
}
