package com.coder.zzq.ui.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coder.zzq.ui.BuildConfig;
import com.coder.zzq.ui.R;

import java.util.ArrayList;

/**
 * @author zhuzhiqiang
 */
public class EasyWebView extends FrameLayout implements View.OnClickListener {
    public static final int PROGRESS_WEB_VIEW_FINISH_LOAD = 100;
    private ProgressBar mProgressBar;
    private WebView mWebView;
    private String mCurrentUrl = "";
    private int mProgress;
    private String mMainUrl;
    private boolean mIsWebViewNeverLoaded = true;
    private WebViewListener mWebViewListener;

    private Activity mHostActivity;
    private ImageView mGoBackView;
    private ImageView mCloseView;
    private ImageView mRefreshView;
    private TextView mPageTitle;
    private View mToolbar;

    private boolean mEnableTakeOrSelectPhoto;
    private int mRequestCodeForTakeOrSelectPhoto;

    private ValueCallback<Uri[]> mValueCallbackForLollipopAndAbove;
    private WebChromeClient.FileChooserParams mFileChooserParams;

    private ValueCallback<Uri> mValueCallbackForBelowLollipop;

    public EasyWebView(Context context) {
        super(context);
        init();
    }

    public EasyWebView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EasyWebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.android_ui_web_view, this, true);
        mProgressBar = findViewById(R.id.progress_bar_web_load);
        mGoBackView = findViewById(R.id.icon_go_back);
        mCloseView = findViewById(R.id.icon_close);
        mRefreshView = findViewById(R.id.icon_refresh);
        mRefreshView.setOnClickListener(this);
        mGoBackView.setOnClickListener(this);
        mCloseView.setOnClickListener(this);
        mProgressBar.setMax(PROGRESS_WEB_VIEW_FINISH_LOAD);
        mToolbar = findViewById(R.id.title_bar);
        mPageTitle = findViewById(R.id.page_title);
        mWebView = findViewById(R.id.web_view);
        initWebView();
    }

    private void initWebView() {
        //Android 4.4 及以上开启Chrome调试
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && BuildConfig.DEBUG) {
            mWebView.setWebContentsDebuggingEnabled(true);
        }
        WebSettings webSettings = mWebView.getSettings();
        //开启javascript支持
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("UTF-8");

        // 支持缩放
        webSettings.setSupportZoom(true);
        //可以访问content uri
        webSettings.setAllowContentAccess(true);
        //可以访问文件系统
        webSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowUniversalAccessFromFileURLs(true);
            webSettings.setAllowFileAccessFromFileURLs(true);
        }
        //Android 5.0 以上需主动开启 http/https 混合加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.getSettings().setBlockNetworkLoads(false);
        mWebView.getSettings().setBlockNetworkImage(false);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                if (TextUtils.isEmpty(mCurrentUrl)) {
                    mCurrentUrl = view.getUrl();
                    return;
                }
                if (!mCurrentUrl.equals(view.getUrl()) && mWebViewListener != null) {
                    mWebViewListener.onWebUrlChanged(EasyWebView.this, view.getUrl());
                }
                if (!mCurrentUrl.equals(view.getUrl()) && isHtmlText(view.getUrl()) && isMainUrl(view.getUrl())) {
                    refresh();
                }
                mCurrentUrl = view.getUrl();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d("zzq", "on Progress :" + newProgress + "| url:" + view.getUrl());
                mProgress = newProgress;
                mIsWebViewNeverLoaded = false;
                mProgressBar.setProgress(newProgress);
                mProgressBar.setVisibility(newProgress < PROGRESS_WEB_VIEW_FINISH_LOAD ? VISIBLE : GONE);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                mValueCallbackForLollipopAndAbove = filePathCallback;
                mFileChooserParams = fileChooserParams;
                selectPic();
                return true;
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mValueCallbackForBelowLollipop = uploadMsg;
                selectPic();
            }

        });


    }

    private void selectPic() {
        ensureActivityHasSet();
        Intent selectPicIntent = new Intent(Intent.ACTION_GET_CONTENT);
        selectPicIntent.addCategory(Intent.CATEGORY_OPENABLE);
        selectPicIntent.putExtra("return-data", true);
        selectPicIntent.setType("image/*");
        selectPicIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        mHostActivity.startActivityForResult(Intent.createChooser(selectPicIntent, "选取照片"),
                mRequestCodeForTakeOrSelectPhoto);
    }

    public WebView getWebView() {
        return mWebView;
    }

    public boolean isFinish() {
        return mProgress == PROGRESS_WEB_VIEW_FINISH_LOAD;
    }

    public boolean isWebViewNeverLoaded() {
        return mIsWebViewNeverLoaded;
    }

    public String getCurrentUrl() {
        return mCurrentUrl;
    }

    /**
     * 加载Url的兼容方法，部分机型，两次加载url一致时，无法刷新
     */
    public void loadUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return;
        }
        setMainUrl(deleteUrlParams(url));
        if (getCurrentUrl().equals(url)) {
            mWebView.reload();
        } else {
            mWebView.loadUrl(url);
        }
    }

    private void setMainUrl(String url) {
        if (mMainUrl != null) {
            mMainUrl = url;
        }
    }

    public void loadHtmlText(CharSequence htmlText) {
        setMainUrl(htmlText.toString());
        if (htmlText.equals(mMainUrl)) {
            refresh();
        } else {
            mWebView.loadDataWithBaseURL(null, htmlText.toString(), "text/html", "utf-8", null);
        }
    }

    public void loadHtmlText(@StringRes int htmlText) {
        loadHtmlText(getContext().getString(htmlText));
    }

    @SuppressLint("JavascriptInterface")
    public void setObjInvokedByJs(Object obj, String name) {
        mWebView.addJavascriptInterface(obj, name);
    }

    public void invokeJsMethod(String method) {
        mWebView.loadUrl("javascript:" + method);
    }

    public boolean canGoBack() {
        return mWebView.canGoBack();
    }

    public void goBack() {
        mWebView.goBack();
    }

    public void refresh() {
        mWebView.reload();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.icon_go_back) {
            processOnGoBackClick();
        } else if (v.getId() == R.id.icon_close) {
            processOnCloseClick();
        } else if (v.getId() == R.id.icon_refresh) {
            refresh();
        }
    }

    private void processOnCloseClick() {
        finishHostActivity();
    }

    private void processOnGoBackClick() {
        String url = deleteUrlParams(mCurrentUrl);
        if (url.equals(mMainUrl) || !mWebView.canGoBack()) {
            mWebViewListener.onMainUrlExit(mHostActivity);
        } else {
            mWebView.goBack();
        }
    }

    private boolean isMainUrl(String url) {
        if (url.equals("about:blank") || url.equals("data:text/html;charset=utf-8;base64,")) {
            return true;
        }
        url = deleteUrlParams(url);
        return url.equals(mMainUrl);
    }

    private boolean isHtmlText(String url) {
        return "about:blank".equals(url);
    }

    private String deleteUrlParams(String url) {
        return url.contains("?") ? url.substring(0, url.indexOf("?")) : url;
    }

    private void finishHostActivity() {
        ensureActivityHasSet();
        mHostActivity.finish();
    }

    public void onHostActivityResult(int requestCode, int resultCode, Intent data) {
        if (!mEnableTakeOrSelectPhoto || requestCode != mRequestCodeForTakeOrSelectPhoto) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mValueCallbackForLollipopAndAbove != null) {
                if (null != data) {
                    ArrayList<String> resultList = data
                            .getStringArrayListExtra("data");

                    mValueCallbackForLollipopAndAbove.onReceiveValue(
                            mFileChooserParams.parseResult(resultCode, data));
                    mValueCallbackForLollipopAndAbove = null;
                } else {
                    mValueCallbackForLollipopAndAbove.onReceiveValue(null);
                }
            }
        } else {
            if (null == mValueCallbackForBelowLollipop) return;
            if (null == data) {
                mValueCallbackForBelowLollipop.onReceiveValue(null);
                mValueCallbackForBelowLollipop = null;
            } else {
                Uri result = data == null || resultCode != Activity.RESULT_OK ? null
                        : data.getData();
                mValueCallbackForBelowLollipop.onReceiveValue(result);
                mValueCallbackForBelowLollipop = null;
            }
        }

    }

    public void onHostActivityDestroyed() {
        mHostActivity = null;
        if (mWebView != null) {
            mWebView.destroy();
        }
    }

    private void ensureActivityHasSet() {
        if (mHostActivity == null) {
            throw new IllegalStateException("you has not invoke method: hostActivity(Activity activity)");
        }
    }

    public EasyWebView showToolBar(boolean show) {
        mToolbar.setVisibility(show ? VISIBLE : GONE);
        return this;
    }

    public EasyWebView takeOrSelectPhotoEnable(boolean enable, int requestCode) {
        mEnableTakeOrSelectPhoto = enable;
        mRequestCodeForTakeOrSelectPhoto = requestCode;
        return this;
    }

    public EasyWebView showToolbar(boolean show) {
        mToolbar.setVisibility(show ? VISIBLE : GONE);
        return this;
    }

    public EasyWebView defaultPageTitle(CharSequence pageTitle) {
        mPageTitle.setText(pageTitle);
        return this;
    }

    public EasyWebView setWebViewListener(WebViewListener webViewListener) {
        mWebViewListener = webViewListener;
        return this;
    }

    public EasyWebView setHostActivity(Activity activity) {
        mHostActivity = activity;
        return this;
    }


    public abstract class WebViewListener {
        public abstract void onWebUrlChanged(EasyWebView myWebView, String url);

        public void onMainUrlExit(Activity hostActivity) {
            hostActivity.finish();
        }
    }
}
