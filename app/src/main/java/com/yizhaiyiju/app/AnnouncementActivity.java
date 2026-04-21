package com.yizhaiyiju.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import s2.r;

/* loaded from: classes.dex */
public class AnnouncementActivity extends d.s {
    public static void checkAndShow(Context context) {
        new Thread(new a(context, 0)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$checkAndShow$1(Context context) {
        try {
            q3.c0 c0Var = new q3.c0();
            c0Var.d("https://12zn.com/api/announcements");
            c0Var.c("GET", null);
            q3.h0 g4 = ApiHelper.getClient().a(c0Var.a()).g();
            if (!g4.C()) {
                return;
            }
            u2.e g5 = r.d1(g4.f4104g.C()).g();
            if (!g5.k("success") || !g5.j("success").f() || !g5.k("data")) {
                return;
            }
            u2.b j4 = g5.j("data");
            j4.getClass();
            if (j4 instanceof u2.d) {
                return;
            }
            u2.e eVar = (u2.e) g5.f4744a.get("data");
            String h4 = eVar.k("version") ? eVar.j("version").h() : "";
            if (h4.isEmpty()) {
                return;
            }
            int i4 = 0;
            if (h4.equals(context.getSharedPreferences("app_prefs", 0).getString("last_read_announcement", ""))) {
                return;
            }
            String h5 = eVar.k("title") ? eVar.j("title").h() : "更新内容";
            String h6 = eVar.k("date") ? eVar.j("date").h() : "";
            u2.a aVar = eVar.k("items") ? (u2.a) eVar.f4744a.get("items") : new u2.a();
            String[] strArr = new String[aVar.f4742a.size()];
            while (true) {
                ArrayList arrayList = aVar.f4742a;
                if (i4 >= arrayList.size()) {
                    Intent intent = new Intent(context, (Class<?>) AnnouncementActivity.class);
                    intent.putExtra("version", h4);
                    intent.putExtra("title", h5);
                    intent.putExtra("date", h6);
                    intent.putExtra("items", strArr);
                    intent.addFlags(268435456);
                    context.startActivity(intent);
                    return;
                }
                strArr[i4] = ((u2.b) arrayList.get(i4)).h();
                i4++;
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(String str, View view) {
        if (str != null) {
            getSharedPreferences("app_prefs", 0).edit().putString("last_read_announcement", str).apply();
        }
        finish();
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_announcement);
        String stringExtra = getIntent().getStringExtra("version");
        String stringExtra2 = getIntent().getStringExtra("title");
        String stringExtra3 = getIntent().getStringExtra("date");
        String[] stringArrayExtra = getIntent().getStringArrayExtra("items");
        TextView textView = (TextView) findViewById(R.id.tv_version_label);
        TextView textView2 = (TextView) findViewById(R.id.tv_title);
        TextView textView3 = (TextView) findViewById(R.id.tv_date);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.items_container);
        if (stringExtra != null) {
            textView.setText("v".concat(stringExtra));
        }
        if (stringExtra2 != null) {
            textView2.setText(stringExtra2);
        }
        if (stringExtra3 != null) {
            textView3.setText(stringExtra3);
        }
        if (stringArrayExtra != null) {
            for (String str : stringArrayExtra) {
                TextView textView4 = new TextView(this);
                textView4.setText(str);
                textView4.setTextSize(15.0f);
                textView4.setTextColor(getColor(R.color.text_primary));
                textView4.setPadding(0, 0, 0, 16);
                linearLayout.addView(textView4);
            }
        }
        findViewById(R.id.btn_dismiss).setOnClickListener(new j(this, 4, stringExtra));
    }
}
