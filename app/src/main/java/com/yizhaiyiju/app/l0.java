package com.yizhaiyiju.app;

import android.view.View;

/* loaded from: classes.dex */
public final /* synthetic */ class l0 implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2352e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ MarriageMatchActivity f2353f;

    public /* synthetic */ l0(MarriageMatchActivity marriageMatchActivity, int i4) {
        this.f2352e = i4;
        this.f2353f = marriageMatchActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2352e;
        MarriageMatchActivity marriageMatchActivity = this.f2353f;
        switch (i4) {
            case 0:
                marriageMatchActivity.lambda$calculate$3(view);
                break;
            default:
                marriageMatchActivity.lambda$onCreate$1(view);
                break;
        }
    }
}
