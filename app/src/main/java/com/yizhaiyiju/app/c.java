package com.yizhaiyiju.app;

import com.yizhaiyiju.app.ApiHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2301a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ApiHelper.Callback f2302b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f2303c;

    public /* synthetic */ c(ApiHelper.Callback callback, String str, int i4) {
        this.f2301a = i4;
        this.f2302b = callback;
        this.f2303c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2301a;
        String str = this.f2303c;
        ApiHelper.Callback callback = this.f2302b;
        switch (i4) {
            case 0:
                callback.onError(str);
                break;
            case 1:
                callback.onError(str);
                break;
            case 2:
                callback.onSuccess(str);
                break;
            case 3:
                callback.onSuccess(str);
                break;
            default:
                callback.onSuccess(str);
                break;
        }
    }
}
