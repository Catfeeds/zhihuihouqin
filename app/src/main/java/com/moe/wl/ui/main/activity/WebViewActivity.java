package com.moe.wl.ui.main.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.moe.wl.R;

public class WebViewActivity extends Base2Activity {

    private WebView webView;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_web_view);
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.wb);
        String result = getIntent().getStringExtra("Result");
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        webView.requestFocusFromTouch();
        //加载需要显示的网页
        webView.loadUrl(result);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != "") {
                    view.loadUrl(url);
                }
                return false;
            }
        });
    }
}
