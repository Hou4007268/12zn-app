package com.yizhaiyiju.app;

import com.yizhaiyiju.app.ApiHelper;
import r2.e1;

/* loaded from: classes.dex */
public final /* synthetic */ class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2309a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ApiHelper.Callback f2310b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Exception f2311c;

    public /* synthetic */ d(ApiHelper.Callback callback, Exception exc, int i4) {
        this.f2309a = i4;
        this.f2310b = callback;
        this.f2311c = exc;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2309a;
        Exception exc = this.f2311c;
        ApiHelper.Callback callback = this.f2310b;
        switch (i4) {
            case 0:
                ApiHelper.AnonymousClass1.lambda$onResponse$3(callback, exc);
                break;
            case 1:
                ApiHelper.AnonymousClass2.lambda$onResponse$3(callback, exc);
                break;
            case 2:
                ApiHelper.AnonymousClass3.lambda$onResponse$3(callback, exc);
                break;
            case 3:
                ApiHelper.AnonymousClass4.lambda$onResponse$3(callback, exc);
                break;
            case 4:
                ApiHelper.AnonymousClass5.lambda$onResponse$5(callback, exc);
                break;
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                ApiHelper.AnonymousClass6.lambda$onResponse$3(callback, exc);
                break;
            case 6:
                ApiHelper.AnonymousClass7.lambda$onResponse$2(callback, exc);
                break;
            default:
                ApiHelper.AnonymousClass8.lambda$onResponse$2(callback, exc);
                break;
        }
    }
}
