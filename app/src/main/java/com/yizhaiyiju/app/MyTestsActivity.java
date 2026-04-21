package com.yizhaiyiju.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/* loaded from: classes.dex */
public class MyTestsActivity extends d.s {
    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        finish();
    }

    @Override // androidx.fragment.app.a0, androidx.activity.ComponentActivity, x.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_list_empty);
        final int i4 = 0;
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.o0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ MyTestsActivity f2364f;

            {
                this.f2364f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i5 = i4;
                MyTestsActivity myTestsActivity = this.f2364f;
                switch (i5) {
                    case 0:
                        myTestsActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        myTestsActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
        ((TextView) findViewById(R.id.tv_title)).setText("我的测试记录");
        ((TextView) findViewById(R.id.tv_empty_icon)).setText("📝");
        ((TextView) findViewById(R.id.tv_empty_text)).setText("暂无测试记录");
        ((TextView) findViewById(R.id.tv_empty_sub)).setText("完成趣味测试后，结果将保存在这里");
        ((TextView) findViewById(R.id.tv_empty_btn)).setText("去测试");
        final int i5 = 1;
        findViewById(R.id.btn_empty_action).setOnClickListener(new View.OnClickListener(this) { // from class: com.yizhaiyiju.app.o0

            /* renamed from: f, reason: collision with root package name */
            public final /* synthetic */ MyTestsActivity f2364f;

            {
                this.f2364f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i52 = i5;
                MyTestsActivity myTestsActivity = this.f2364f;
                switch (i52) {
                    case 0:
                        myTestsActivity.lambda$onCreate$0(view);
                        break;
                    default:
                        myTestsActivity.lambda$onCreate$1(view);
                        break;
                }
            }
        });
    }
}
