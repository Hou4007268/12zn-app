package com.yizhaiyiju.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/* loaded from: classes.dex */
public class SettingsActivity extends d.s {
    private SharedPreferences chatPrefs;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        startActivity(new Intent(this, (Class<?>) RedeemActivity.class));
    }

    private String formatAutoClearText(long days) {
        return days <= 0 ? "永久" : days + "天";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(TextView textView, View view) {
        long currentDays = this.chatPrefs.getLong("auto_clear_days", 0L);
        long nextDays = currentDays == 7 ? 30L : currentDays == 30 ? 0L : 7L;
        this.chatPrefs.edit().putLong("auto_clear_days", nextDays).apply();
        textView.setText(formatAutoClearText(nextDays));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3(View view) {
        this.chatPrefs.edit().remove("messages").putLong("last_clear_time", System.currentTimeMillis()).apply();
        Toast.makeText(this, "聊天记录已清空", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$4(View view) {
        Intent intent = new Intent(this, (Class<?>) LoginActivity.class);
        intent.putExtra("mode", "reset");
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$5(View view) {
        UpdateHelper.checkUpdateManual(this);
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
        final int i4 = 0;
        this.chatPrefs = getSharedPreferences("chat_history", 0);
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.u0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ SettingsActivity f2385f;

            {
                this.f2385f = SettingsActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                SettingsActivity settingsActivity = this.f2385f;
                switch (i5) {
                    case 0:
                        settingsActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        settingsActivity.lambda$onCreate$1(view);
                        break;
                    case 2:
                        settingsActivity.lambda$onCreate$3(view);
                        break;
                    case 3:
                        settingsActivity.lambda$onCreate$4(view);
                        break;
                    default:
                        settingsActivity.lambda$onCreate$5(view);
                        break;
                }
            }
        });
        final int i5 = 1;
        findViewById(R.id.item_redeem).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.u0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ SettingsActivity f2385f;

            {
                this.f2385f = SettingsActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i5;
                SettingsActivity settingsActivity = this.f2385f;
                switch (i52) {
                    case 0:
                        settingsActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        settingsActivity.lambda$onCreate$1(view);
                        break;
                    case 2:
                        settingsActivity.lambda$onCreate$3(view);
                        break;
                    case 3:
                        settingsActivity.lambda$onCreate$4(view);
                        break;
                    default:
                        settingsActivity.lambda$onCreate$5(view);
                        break;
                }
            }
        });
        TextView textView = (TextView) findViewById(R.id.tv_auto_clear_value);
        textView.setText(formatAutoClearText(this.chatPrefs.getLong("auto_clear_days", 0L)));
        findViewById(R.id.item_auto_clear).setOnClickListener(new j(this, 8, textView));
        final int i6 = 2;
        findViewById(R.id.item_clear_chat).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.u0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ SettingsActivity f2385f;

            {
                this.f2385f = SettingsActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i6;
                SettingsActivity settingsActivity = this.f2385f;
                switch (i52) {
                    case 0:
                        settingsActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        settingsActivity.lambda$onCreate$1(view);
                        break;
                    case 2:
                        settingsActivity.lambda$onCreate$3(view);
                        break;
                    case 3:
                        settingsActivity.lambda$onCreate$4(view);
                        break;
                    default:
                        settingsActivity.lambda$onCreate$5(view);
                        break;
                }
            }
        });
        final int i7 = 3;
        findViewById(R.id.item_change_password).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.u0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ SettingsActivity f2385f;

            {
                this.f2385f = SettingsActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i7;
                SettingsActivity settingsActivity = this.f2385f;
                switch (i52) {
                    case 0:
                        settingsActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        settingsActivity.lambda$onCreate$1(view);
                        break;
                    case 2:
                        settingsActivity.lambda$onCreate$3(view);
                        break;
                    case 3:
                        settingsActivity.lambda$onCreate$4(view);
                        break;
                    default:
                        settingsActivity.lambda$onCreate$5(view);
                        break;
                }
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.tv_version);
        try {
            textView2.setText("v" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (Exception unused) {
            textView2.setText("v?.?.?");
        }
        final int i8 = 4;
        findViewById(R.id.item_check_update).setOnClickListener(new View.OnClickListener() { // from class: com.yizhaiyiju.app.u0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ SettingsActivity f2385f;

            {
                this.f2385f = SettingsActivity.this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i8;
                SettingsActivity settingsActivity = this.f2385f;
                switch (i52) {
                    case 0:
                        settingsActivity.lambda$onCreate$0(view);
                        break;
                    case 1:
                        settingsActivity.lambda$onCreate$1(view);
                        break;
                    case 2:
                        settingsActivity.lambda$onCreate$3(view);
                        break;
                    case 3:
                        settingsActivity.lambda$onCreate$4(view);
                        break;
                    default:
                        settingsActivity.lambda$onCreate$5(view);
                        break;
                }
            }
        });
    }
}
