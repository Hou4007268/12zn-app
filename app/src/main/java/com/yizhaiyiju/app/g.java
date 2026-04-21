package com.yizhaiyiju.app;

import com.yizhaiyiju.app.ApiHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2327a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ApiHelper.Callback f2328b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ q3.h0 f2329c;

    public /* synthetic */ g(ApiHelper.Callback callback, q3.h0 h0Var, int i4) {
        this.f2327a = i4;
        this.f2328b = callback;
        this.f2329c = h0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2327a;
        q3.h0 h0Var = this.f2329c;
        ApiHelper.Callback callback = this.f2328b;
        switch (i4) {
            case 0:
                ApiHelper.AnonymousClass3.lambda$onResponse$1(callback, h0Var);
                break;
            case 1:
                ApiHelper.AnonymousClass4.lambda$onResponse$1(callback, h0Var);
                break;
            case 2:
                ApiHelper.AnonymousClass5.lambda$onResponse$1(callback, h0Var);
                break;
            default:
                ApiHelper.AnonymousClass6.lambda$onResponse$1(callback, h0Var);
                break;
        }
    }
}
