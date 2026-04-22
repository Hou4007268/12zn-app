package com.yizhaiyiju.app;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;
import s2.r;

/* loaded from: classes.dex */
public class RedeemActivity extends d.s {
    private Button btnRedeem;
    private EditText etCode;
    private TextView tvResult;

    /* renamed from: com.yizhaiyiju.app.RedeemActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements q3.e {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$0(IOException iOException) {
            RedeemActivity.this.btnRedeem.setEnabled(true);
            RedeemActivity.this.btnRedeem.setText("立即兑换");
            RedeemActivity.this.tvResult.setVisibility(0);
            RedeemActivity.this.tvResult.setText("网络错误: " + iOException.getMessage());
            RedeemActivity.this.tvResult.setTextColor(RedeemActivity.this.getColor(R.color.red_soft));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onResponse$1(u2.e eVar) {
            TextView textView;
            RedeemActivity redeemActivity;
            int i4;
            RedeemActivity.this.btnRedeem.setEnabled(true);
            RedeemActivity.this.btnRedeem.setText("立即兑换");
            RedeemActivity.this.tvResult.setVisibility(0);
            if (!eVar.k("success") || !eVar.j("success").f()) {
                String h4 = eVar.k("error") ? eVar.j("error").h() : "兑换失败";
                RedeemActivity.this.tvResult.setText("✗ " + h4);
                textView = RedeemActivity.this.tvResult;
                redeemActivity = RedeemActivity.this;
                i4 = R.color.red_soft;
            } else {
                if (!eVar.k("already_redeemed") || !eVar.j("already_redeemed").f()) {
                    String h5 = eVar.k("message") ? eVar.j("message").h() : "兑换成功";
                    RedeemActivity.this.tvResult.setText("✓ " + h5);
                    RedeemActivity.this.tvResult.setTextColor(RedeemActivity.this.getColor(R.color.primary));
                    RedeemActivity.this.etCode.setText("");
                    Toast.makeText(RedeemActivity.this, "兑换成功！", 0).show();
                    refreshRights();
                    return;
                }
                RedeemActivity.this.tvResult.setText("✓ 该设备已兑换过此码");
                textView = RedeemActivity.this.tvResult;
                redeemActivity = RedeemActivity.this;
                i4 = R.color.primary;
            }
            textView.setTextColor(redeemActivity.getColor(i4));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onResponse$2() {
            RedeemActivity.this.btnRedeem.setEnabled(true);
            RedeemActivity.this.btnRedeem.setText("立即兑换");
            RedeemActivity.this.tvResult.setVisibility(0);
            RedeemActivity.this.tvResult.setText("解析错误");
            RedeemActivity.this.tvResult.setTextColor(RedeemActivity.this.getColor(R.color.red_soft));
        }

        @Override // q3.e
        public void onFailure(q3.d dVar, IOException iOException) {
            RedeemActivity.this.runOnUiThread(new f(this, 6, iOException));
        }

        @Override // q3.e
        public void onResponse(q3.d dVar, q3.h0 h0Var) {
            try {
                RedeemActivity.this.runOnUiThread(new f(this, 5, r.d1(h0Var.f4104g.C()).g()));
            } catch (Exception unused) {
                RedeemActivity.this.runOnUiThread(new h(3, this));
            }
        }
    }

    private void doRedeem() {
        String upperCase = this.etCode.getText().toString().trim().toUpperCase(Locale.ROOT);
        if (upperCase.isEmpty()) {
            Toast.makeText(this, "请输入兑换码", 0).show();
            return;
        }
        this.btnRedeem.setEnabled(false);
        this.btnRedeem.setText("兑换中...");
        this.tvResult.setVisibility(8);
        String string = Settings.Secure.getString(getContentResolver(), "android_id");
        u2.e eVar = new u2.e();
        eVar.i("code", upperCase);
        eVar.i("client_key", string);
        eVar.i("source_page", "app_redeem");
        eVar.i("user_id", ApiHelper.getUserId());
        String bVar = eVar.toString();
        Pattern pattern = q3.v.f4179d;
        q3.f0 f4 = q3.o.f(bVar, q3.o.r("application/json"));
        q3.c0 c0Var = new q3.c0();
        c0Var.d(ApiHelper.getClient() != null ? "https://12zn.com/api/redeem/redeem" : "");
        c0Var.c("POST", f4);
        ApiHelper.getClient().a(c0Var.a()).f(new AnonymousClass1());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        doRedeem();
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_redeem);
        this.etCode = (EditText) findViewById(R.id.et_code);
        this.btnRedeem = (Button) findViewById(R.id.btn_redeem);
        this.tvResult = (TextView) findViewById(R.id.tv_result);
        final int i4 = 0;
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.s0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ RedeemActivity f2377f;

            {
                this.f2377f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                RedeemActivity redeemActivity = this.f2377f;
                switch (i5) {
                    case 0:
                        redeemActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        redeemActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        final int i5 = 1;
        this.btnRedeem.setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.s0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ RedeemActivity f2377f;

            {
                this.f2377f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i5;
                RedeemActivity redeemActivity = this.f2377f;
                switch (i52) {
                    case 0:
                        redeemActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        redeemActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
    }

    private void refreshRights() {
        ApiHelper.apiGet("app/rights?user_id=" + ApiHelper.getUserId(), new ApiHelper.Callback<Object>() {
            @Override public void onSuccess(Object obj) {
                try {
                    org.json.JSONObject json = (org.json.JSONObject) obj;
                    if (json.optBoolean("success") && json.has("data")) {
                        org.json.JSONObject data = json.getJSONObject("data");
                        org.json.JSONObject summary = data.optJSONObject("summary");
                        String memberType = "普通用户";
                        String memberExpires = null;
                        if (summary != null && summary.optBoolean("has_membership", false)) {
                            memberType = summary.optString("membership_type", "普通用户");
                            String expires = summary.optString("membership_expires", null);
                            if (expires != null && expires.length() > 10) {
                                memberExpires = expires.substring(0, 10);
                            } else {
                                memberExpires = expires;
                            }
                        }
                        ApiHelper.saveAuth(RedeemActivity.this,
                            ApiHelper.getAuthToken(), ApiHelper.getUserId(),
                            ApiHelper.getUserPhone(), memberType, memberExpires);
                    }
                } catch (Exception e) { }
            }
            @Override public void onError(String err) { }
        });
    }

}