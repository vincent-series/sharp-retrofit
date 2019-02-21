package com.coder.zzq.ui.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.coder.zzq.ui.webview.EasyWebView;

public class TestWebViewActivity extends AppCompatActivity {
    private EasyWebView mEasyWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_view);
        mEasyWebView = findViewById(R.id.easy_web_view);
        mEasyWebView.loadUrl("https://www.jianshu.com/p/c57cb313a461");
        mEasyWebView.defaultPageTitle("小班课");
    }
}
