package com.yizhaiyiju.app;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import r2.e1;

/* loaded from: classes.dex */
public class ArticleDetailActivity extends d.s {
    private static final String[] TRUSTED_HOSTS = {"12zn.com", "www.12zn.com"};

    private boolean isTrustedUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Uri uri = Uri.parse(str);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            if (!"https".equalsIgnoreCase(scheme) || host == null) {
                return false;
            }
            String lowerCase = host.toLowerCase(Locale.US);
            for (String allowed : TRUSTED_HOSTS) {
                if (allowed.equals(lowerCase)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private void showBlockedUrlToast() {
        Toast.makeText(this, "仅允许打开 12zn.com 的安全链接", Toast.LENGTH_SHORT).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    private String extractTestName(String str) {
        try {
            String path = Uri.parse(str).getPath();
            return path != null ? mapTestName(path.substring(path.lastIndexOf(47) + 1).replace(".html", "")) : "romance";
        } catch (Exception unused) {
            return "romance";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String extractTestIdFromServices(String str) {
        try {
            String path = Uri.parse(str).getPath();
            return path != null ? mapTestName(path.substring(path.lastIndexOf(47) + 1).replace("test-", "").replace(".html", "")) : "romance";
        } catch (Exception unused) {
            return "romance";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    private String mapTestName(String str) {
        str.getClass();
        switch (str) {
            case "animal":
            case "career":
            case "mental_age":
            case "lucky_color":
            case "lifestyle":
            case "mbti":
            case "name":
            case "movie":
            case "music":
            case "financial":
            case "romance":
                return str;
            case "caishang":
                return "financial";
            case "lucky":
                return "lucky_color";
            case "xinli":
                return "mental_age";
            case "xingming":
                return "name";
            default:
                return "romance";
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        WebView webView = (WebView) findViewById(R.id.web_view);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_article_detail);
        String stringExtra = getIntent().getStringExtra("article_title");
        String stringExtra2 = getIntent().getStringExtra("article_url");
        TextView textView = (TextView) findViewById(R.id.tv_title);
        if (stringExtra == null) {
            stringExtra = "文章";
        }
        textView.setText(stringExtra);
        findViewById(R.id.btn_back).setOnClickListener(new i(0, this));
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        final WebView webView = (WebView) findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        boolean trustedInitialUrl = isTrustedUrl(stringExtra2);
        settings.setJavaScriptEnabled(trustedInitialUrl);
        settings.setDomStorageEnabled(trustedInitialUrl);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        webView.setWebViewClient(new WebViewClient() {
            private boolean handleLink(String url) {
                if (!ArticleDetailActivity.this.isTrustedUrl(url)) {
                    ArticleDetailActivity.this.showBlockedUrlToast();
                    return true;
                }
                return false;
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                progressBar.setVisibility(8);
                if (ArticleDetailActivity.this.isTrustedUrl(str)) {
                    webView.loadUrl("javascript:(function(){try{document.querySelector('header')&&(document.querySelector('header').style.display='none');document.querySelector('nav')&&(document.querySelector('nav').style.display='none');document.querySelector('.topbar')&&(document.querySelector('.topbar').style.display='none');document.querySelector('footer')&&(document.querySelector('footer').style.display='none');document.querySelector('.site-footer')&&(document.querySelector('.site-footer').style.display='none');var chatBtn=document.querySelector('.fab-chat');chatBtn&&(chatBtn.style.display='none');var chatW=document.getElementById('ai-chat-widget');chatW&&(chatW.style.display='none');var fabTop=document.querySelector('.fab-top');fabTop&&(fabTop.style.display='none');var s=document.createElement('style');s.innerHTML='body{padding:16px!important;max-width:100%!important;} img{max-width:100%!important;height:auto!important;}';document.head.appendChild(s);}catch(e){}})()");
                }
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, WebResourceRequest webResourceRequest) {
                return handleLink(webResourceRequest.getUrl().toString());
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                return handleLink(str);
            }
        });
        if (!trustedInitialUrl) {
            showBlockedUrlToast();
            return;
        }
        webView.loadUrl(stringExtra2.trim());
    }
}
