package com.yizhaiyiju.app;

import android.view.View;

/* loaded from: classes.dex */
public final /* synthetic */ class j0 implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2344e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ KuaNumberActivity f2345f;

    public /* synthetic */ j0(KuaNumberActivity kuaNumberActivity, int i4) {
        this.f2344e = i4;
        this.f2345f = kuaNumberActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2344e;
        KuaNumberActivity kuaNumberActivity = this.f2345f;
        switch (i4) {
            case 0:
                kuaNumberActivity.lambda$calculate$3(view);
                break;
            default:
                kuaNumberActivity.lambda$onCreate$1(view);
                break;
        }
    }
}
