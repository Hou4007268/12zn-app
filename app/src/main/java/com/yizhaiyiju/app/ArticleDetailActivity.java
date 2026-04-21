package com.yizhaiyiju.app;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import r2.e1;

/* loaded from: classes.dex */
public class ArticleDetailActivity extends d.s {
    /* JADX INFO: Access modifiers changed from: private */
    public String extractTestId(String str) {
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
    public String extractTestName(String str) {
        String path;
        try {
            path = Uri.parse(str).getPath();
        } catch (Exception unused) {
            return "趣味测试";
        }
        if (path == null) {
            return "趣味测试";
        }
        char c5 = 1;
        String replace = path.substring(path.lastIndexOf(47) + 1).replace(".html", "").replace("test-", "");
        switch (replace.hashCode()) {
            case -1975751648:
                if (replace.equals("mianxiang")) {
                    c5 = '\b';
                    break;
                }
                c5 = 65535;
                break;
            case -1702588822:
                if (replace.equals("superpower")) {
                    c5 = 4;
                    break;
                }
                c5 = 65535;
                break;
            case -1602175165:
                if (replace.equals("ta-aini")) {
                    c5 = 14;
                    break;
                }
                c5 = 65535;
                break;
            case -1413116420:
                if (replace.equals("animal")) {
                    break;
                }
                c5 = 65535;
                break;
            case -880993774:
                if (replace.equals("taohua")) {
                    c5 = '\t';
                    break;
                }
                c5 = 65535;
                break;
            case -776367032:
                if (replace.equals("wuxing")) {
                    c5 = 5;
                    break;
                }
                c5 = 65535;
                break;
            case -251464070:
                if (replace.equals("caishang")) {
                    c5 = 11;
                    break;
                }
                c5 = 65535;
                break;
            case 3345098:
                if (replace.equals("mbti")) {
                    c5 = 0;
                    break;
                }
                c5 = 65535;
                break;
            case 69445217:
                if (replace.equals("fengshui")) {
                    c5 = '\f';
                    break;
                }
                c5 = 65535;
                break;
            case 103324392:
                if (replace.equals("lucky")) {
                    c5 = 2;
                    break;
                }
                c5 = 65535;
                break;
            case 114059738:
                if (replace.equals("xinli")) {
                    c5 = '\n';
                    break;
                }
                c5 = 65535;
                break;
            case 115915212:
                if (replace.equals("ziwei")) {
                    c5 = 6;
                    break;
                }
                c5 = 65535;
                break;
            case 307734095:
                if (replace.equals("qianshi")) {
                    c5 = '\r';
                    break;
                }
                c5 = 65535;
                break;
            case 335048840:
                if (replace.equals("dream-career")) {
                    c5 = 3;
                    break;
                }
                c5 = 65535;
                break;
            case 630129599:
                if (replace.equals("xingming")) {
                    c5 = 7;
                    break;
                }
                c5 = 65535;
                break;
            default:
                c5 = 65535;
                break;
        }
        switch (c5) {
            case 0:
                return "MBTI性格测试";
            case 1:
                return "守护动物测试";
            case 2:
                return "缘分测试";
            case 3:
                return "梦想职业测试";
            case 4:
                return "超能力测试";
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                return "五行测试";
            case 6:
                return "紫微斗数";
            case 7:
                return "姓名测试";
            case '\b':
                return "面相测试";
            case '\t':
                return "桃花运测试";
            case '\n':
                return "心理年龄测试";
            case 11:
                return "财商测试";
            case '\f':
                return "风水测试";
            case '\r':
                return "前世测试";
            case 14:
                return "TA爱你吗";
            default:
                return replace.concat("测试");
        }
        return "趣味测试";
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
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDefaultFontSize(16);
        webView.setWebViewClient(new WebViewClient() { // from class: com.yizhaiyiju.app.ArticleDetailActivity.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code restructure failed: missing block: B:79:0x0144, code lost:
            
                if (r8.equals("caiyun") == false) goto L20;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            private boolean handleLink(java.lang.String r8) {
                /*
                    Method dump skipped, instructions count: 578
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.yizhaiyiju.app.ArticleDetailActivity.AnonymousClass1.handleLink(java.lang.String):boolean");
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                progressBar.setVisibility(8);
                webView.loadUrl("javascript:(function(){try{document.querySelector('header')&&(document.querySelector('header').style.display='none');document.querySelector('nav')&&(document.querySelector('nav').style.display='none');document.querySelector('.topbar')&&(document.querySelector('.topbar').style.display='none');document.querySelector('footer')&&(document.querySelector('footer').style.display='none');document.querySelector('.site-footer')&&(document.querySelector('.site-footer').style.display='none');var chatBtn=document.querySelector('.fab-chat');chatBtn&&(chatBtn.style.display='none');var chatW=document.getElementById('ai-chat-widget');chatW&&(chatW.style.display='none');var fabTop=document.querySelector('.fab-top');fabTop&&(fabTop.style.display='none');var s=document.createElement('style');s.innerHTML='body{padding:16px!important;max-width:100%!important;} img{max-width:100%!important;height:auto!important;}';document.head.appendChild(s);}catch(e){}})()");
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
        if (stringExtra2 == null || stringExtra2.isEmpty()) {
            return;
        }
        webView.loadUrl(stringExtra2);
    }
}
