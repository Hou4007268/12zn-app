package com.yizhaiyiju.app;

import android.view.View;

/* loaded from: classes.dex */
public final /* synthetic */ class l implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2350e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ BaZiActivity f2351f;

    public /* synthetic */ l(BaZiActivity baZiActivity, int i4) {
        this.f2350e = i4;
        this.f2351f = baZiActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2350e;
        BaZiActivity baZiActivity = this.f2351f;
        switch (i4) {
            case 0:
                baZiActivity.lambda$calculate$3(view);
                break;
            default:
                baZiActivity.lambda$onCreate$1(view);
                break;
        }
    }
}
