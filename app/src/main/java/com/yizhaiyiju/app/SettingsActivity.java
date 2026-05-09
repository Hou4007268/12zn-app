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

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(TextView textView, View view) {
        long nextDays = 7;
        long currentDays = this.chatPrefs.getLong("auto_clear_days", 7L);
        if (currentDays == 7) {
            nextDays = 3;
        } else if (currentDays == 3) {
            nextDays = 1;
        }
        this.chatPrefs.edit().putLong("auto_clear_days", nextDays).apply();
        textView.setText(nextDays + "天");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3(View view) {
        this.chatPrefs.edit().remove("messages").putLong("last_clear_time", System.currentTimeMillis()).apply();
        Toast.makeText(this, "聊天记录已清空", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
        this.chatPrefs = getSharedPreferences("chat_history", 0);
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsActivity.this.lambda$onCreate$0(view);
            }
        });
        findViewById(R.id.item_redeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsActivity.this.lambda$onCreate$1(view);
            }
        });
        TextView textView = (TextView) findViewById(R.id.tv_auto_clear_value);
        textView.setText(this.chatPrefs.getLong("auto_clear_days", 7L) + "天");
        findViewById(R.id.item_auto_clear).setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { lambda$onCreate$2(textView, v); } });
        findViewById(R.id.item_clear_chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsActivity.this.lambda$onCreate$3(view);
            }
        });
        findViewById(R.id.item_change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsActivity.this.lambda$onCreate$4(view);
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.tv_version);
        try {
            textView2.setText("v" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (Exception unused) {
            textView2.setText("v?.?.?");
        }
        findViewById(R.id.item_check_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsActivity.this.lambda$onCreate$5(view);
            }
        });
    }
}
