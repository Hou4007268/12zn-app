package com.yizhaiyiju.app;

import android.view.View;

/* loaded from: classes.dex */
public final /* synthetic */ class p0 implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2367e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ NameAnalysisActivity f2368f;

    public /* synthetic */ p0(NameAnalysisActivity nameAnalysisActivity, int i4) {
        this.f2367e = i4;
        this.f2368f = nameAnalysisActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2367e;
        NameAnalysisActivity nameAnalysisActivity = this.f2368f;
        switch (i4) {
            case 0:
                nameAnalysisActivity.lambda$calculate$3(view);
                break;
            default:
                nameAnalysisActivity.lambda$onCreate$1(view);
                break;
        }
    }
}
