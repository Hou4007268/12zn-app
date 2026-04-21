package com.yizhaiyiju.app;

import com.yizhaiyiju.app.ApiHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2291a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ApiHelper.Callback f2292b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ u2.e f2293c;

    public /* synthetic */ b(ApiHelper.Callback callback, u2.e eVar, int i4) {
        this.f2291a = i4;
        this.f2292b = callback;
        this.f2293c = eVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2291a;
        u2.e eVar = this.f2293c;
        ApiHelper.Callback callback = this.f2292b;
        switch (i4) {
            case 0:
                ApiHelper.AnonymousClass1.lambda$onResponse$1(callback, eVar);
                break;
            default:
                ApiHelper.AnonymousClass5.lambda$onResponse$2(callback, eVar);
                break;
        }
    }
}
