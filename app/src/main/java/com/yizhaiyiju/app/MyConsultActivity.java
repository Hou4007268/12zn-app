package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/* loaded from: classes.dex */
public class MyConsultActivity extends d.s {
    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        startActivity(new Intent(this, (Class<?>) ConsultActivity.class));
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_list_empty);
        final int i4 = 0;
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.n0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ MyConsultActivity f2360f;

            {
                this.f2360f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                MyConsultActivity myConsultActivity = this.f2360f;
                switch (i5) {
                    case 0:
                        myConsultActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        myConsultActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        ((TextView) findViewById(R.id.tv_title)).setText("我的咨询记录");
        ((TextView) findViewById(R.id.tv_empty_icon)).setText("📋");
        ((TextView) findViewById(R.id.tv_empty_text)).setText("暂无咨询记录");
        ((TextView) findViewById(R.id.tv_empty_sub)).setText("预约咨询服务后，记录将显示在这里");
        ((TextView) findViewById(R.id.tv_empty_btn)).setText("去预约咨询");
        final int i5 = 1;
        findViewById(R.id.btn_empty_action).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.n0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ MyConsultActivity f2360f;

            {
                this.f2360f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i5;
                MyConsultActivity myConsultActivity = this.f2360f;
                switch (i52) {
                    case 0:
                        myConsultActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        myConsultActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
    }
}
