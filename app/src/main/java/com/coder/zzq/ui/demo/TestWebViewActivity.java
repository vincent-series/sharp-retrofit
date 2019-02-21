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
        StringBuilder sb = new StringBuilder();
        // 拼接一段HTML代码
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> 欢迎您 </title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h2> 欢迎您访问<a href=\"http://www.cctv.com\">"
                + "CCTV</a></h2>");
        sb.append("</body>");
        sb.append("</html>");
//        mEasyWebView.loadUrl("https://www.jianshu.com/p/c57cb313a461");
        mEasyWebView.loadHtmlText(sb.toString());
        mEasyWebView.defaultPageTitle("小班课");
    }
}
