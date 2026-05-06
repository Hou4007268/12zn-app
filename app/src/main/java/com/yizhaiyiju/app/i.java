package com.yizhaiyiju.app;

import android.view.View;

/* loaded from: classes.dex */
public final /* synthetic */ class i implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2337e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2338f;

    public /* synthetic */ i(int i4, Object obj) {
        this.f2337e = i4;
        this.f2338f = obj;
    }

    @Override
    public final void onClick(View view) {
        if (this.f2337e == 5) {
            ((ServiceDetailActivity) this.f2338f).lambda$onCreate$0(view);
            return;
        }
        throw new IllegalStateException("Unused synthetic click case: " + this.f2337e);
    }
}
