package com.yizhaiyiju.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.yizhaiyiju.app.ApiHelper;

/* loaded from: classes.dex */
public class PaymentActivity extends d.s {
    private Handler handler = new Handler();
    private boolean isPaid = false;
    private LinearLayout layoutSuccess;
    private String orderNo;
    private Runnable pollRunnable;
    private ProgressBar progressBar;
    private TextView tvAmount;
    private TextView tvStatus;
    private TextView tvSubject;
    private WebView wvQr;

    private void createOrder(String str) {
        this.progressBar.setVisibility(0);
        this.tvStatus.setText("正在创建订单...");
        ApiHelper.createOrder(str, new ApiHelper.Callback<ApiHelper.PayResult>() { // from class: com.yizhaiyiju.app.PaymentActivity.1
            @Override // com.yizhaiyiju.app.ApiHelper.Callback
            public void onError(String str2) {
                PaymentActivity.this.progressBar.setVisibility(8);
                PaymentActivity.this.tvStatus.setText("创建订单失败: " + str2);
                Toast.makeText(PaymentActivity.this, str2, 0).show();
            }

            @Override // com.yizhaiyiju.app.ApiHelper.Callback
            public void onSuccess(ApiHelper.PayResult payResult) {
                PaymentActivity.this.progressBar.setVisibility(8);
                PaymentActivity.this.orderNo = payResult.orderNo;
                PaymentActivity.this.tvSubject.setText(payResult.subject);
                PaymentActivity.this.tvAmount.setText("¥" + payResult.amount);
                PaymentActivity.this.tvStatus.setText("请扫描二维码支付");
                PaymentActivity.this.showQRCode(payResult.qrUrl);
                PaymentActivity.this.startPolling();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showQRCode(String str) {
        this.wvQr.getSettings().setJavaScriptEnabled(true);
        this.wvQr.setWebViewClient(new WebViewClient());
        this.wvQr.loadDataWithBaseURL(null, new StringBuilder().append("<!DOCTYPE html><html><head><meta charset='utf-8'><meta name='viewport' content='width=device-width,initial-scale=1'><style>body{margin:0;display:flex;justify-content:center;align-items:center;height:100vh;background:#fefcf8;}canvas{border:8px solid #fff;border-radius:12px;box-shadow:0 2px 12px rgba(0,0,0,0.1);}</style></head><body><canvas id='qr' width='200' height='200'></canvas><script>var QRCode=function(){function c(a,b){this.a=a;this.b=b}var d=[[-1,2,3,-1],[2,4,5,6],[3,5,7,8],[-1,6,8,9]];c.prototype.encode=function(a){var b=this.a,e=this.b,f=[],g,h,i,j,k,l,m,n,o,p;for(g=0;g<b;g++)f[g]=[];for(g=0;g<a.length&&g<b*b;g++){h=Math.floor(g/b);i=g%b;j=a.charCodeAt(g)%10;f[h][i]=j%2}return f};return c}();var url='")
                .append(str.replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;"))
                .append("';var c=document.getElementById('qr'),x=c.getContext('2d');x.fillStyle='#fefcf8';x.fillRect(0,0,200,200);var s=200,n=25,cs=s/n;function hash(s){var h=0;for(var i=0;i<s.length;i++)h=((h<<5)-h)+s.charCodeAt(i)|0;return Math.abs(h)}var h=hash(url);for(var r=0;r<n;r++)for(var col=0;col<n;col++){var bit=((h>>(r*n+col)%31)&1)||((r+col+h)%3===0);if(r<3&&col<3||r<3&&col>=n-3||r>=n-3&&col<3)bit=(r%2===0&&col%2===0)||((r+col)%3===0);if(bit){x.fillStyle='#3d3530';x.fillRect(col*cs+1,r*cs+1,cs-2,cs-2)}}</script></body></html>").toString(), "text/html", "UTF-8", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSuccess() {
        this.wvQr.setVisibility(8);
        this.tvStatus.setVisibility(8);
        this.layoutSuccess.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPolling() {
        Runnable runnable = new Runnable() { // from class: com.yizhaiyiju.app.PaymentActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (PaymentActivity.this.isPaid) {
                    return;
                }
                ApiHelper.checkPayStatus(PaymentActivity.this.orderNo, new ApiHelper.Callback<String>() { // from class: com.yizhaiyiju.app.PaymentActivity.2.1
                    @Override // com.yizhaiyiju.app.ApiHelper.Callback
                    public void onError(String str) {
                        PaymentActivity.this.handler.postDelayed(PaymentActivity.this.pollRunnable, 3000L);
                    }

                    @Override // com.yizhaiyiju.app.ApiHelper.Callback
                    public void onSuccess(String str) {
                        if (!"paid".equals(str) && !"TRADE_SUCCESS".equals(str)) {
                            PaymentActivity.this.handler.postDelayed(PaymentActivity.this.pollRunnable, 3000L);
                        } else {
                            PaymentActivity.this.isPaid = true;
                            PaymentActivity.this.showSuccess();
                            refreshRights();
                        }
                    }
                });
            }
        };
        this.pollRunnable = runnable;
        this.handler.postDelayed(runnable, 3000L);
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_payment);
        final int i4 = 0;
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.q0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ PaymentActivity f2371f;

            {
                this.f2371f = PaymentActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                PaymentActivity paymentActivity = this.f2371f;
                switch (i5) {
                    case 0:
                        paymentActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        paymentActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        this.wvQr = (WebView) findViewById(R.id.wv_qr);
        this.tvSubject = (TextView) findViewById(R.id.tv_subject);
        this.tvAmount = (TextView) findViewById(R.id.tv_amount);
        this.tvStatus = (TextView) findViewById(R.id.tv_status);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.layoutSuccess = (LinearLayout) findViewById(R.id.layout_success);
        final int i5 = 1;
        findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.q0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ PaymentActivity f2371f;

            {
                this.f2371f = PaymentActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i5;
                PaymentActivity paymentActivity = this.f2371f;
                switch (i52) {
                    case 0:
                        paymentActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        paymentActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        String stringExtra = getIntent().getStringExtra("product_id");
        String stringExtra2 = getIntent().getStringExtra("product_name");
        String stringExtra3 = getIntent().getStringExtra("product_price");
        if (stringExtra2 != null) {
            this.tvSubject.setText(stringExtra2);
        }
        if (stringExtra3 != null) {
            this.tvAmount.setText("¥".concat(stringExtra3));
        }
        createOrder(stringExtra);
    }

    @Override // d.s, androidx.fragment.app.a0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.pollRunnable);
    }

    private void refreshRights() {
        ApiHelper.apiGet("app/rights", new ApiHelper.Callback<Object>() {
            @Override public void onSuccess(Object obj) {
                try {
                    org.json.JSONObject json = (org.json.JSONObject) obj;
                    if (json.has("rights")) {
                        org.json.JSONObject rights = json.getJSONObject("rights");
                        String memberType = "普通用户";
                        String memberExpires = null;
                        if (rights.optBoolean("is_member", false)) {
                            String type = rights.optString("member_type", "");
                            if ("yearly".equals(type)) memberType = "年度会员";
                            else if ("monthly".equals(type)) memberType = "月度会员";
                            else if ("trial".equals(type)) memberType = "体验用户";
                            memberExpires = rights.optString("expires_at", null);
                        }
                        ApiHelper.saveAuth(PaymentActivity.this,
                            ApiHelper.getAuthToken(), ApiHelper.getUserId(),
                            ApiHelper.getUserPhone(), memberType, memberExpires);
                    }
                } catch (Exception e) { }
            }
            @Override public void onError(String err) { }
        });
    }

}