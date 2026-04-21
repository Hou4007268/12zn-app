package com.yizhaiyiju.app;

import com.yizhaiyiju.app.ApiHelper;
import java.io.IOException;
import r2.e1;

/* loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2314a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ApiHelper.Callback f2315b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ IOException f2316c;

    public /* synthetic */ e(ApiHelper.Callback callback, IOException iOException, int i4) {
        this.f2314a = i4;
        this.f2315b = callback;
        this.f2316c = iOException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2314a;
        IOException iOException = this.f2316c;
        ApiHelper.Callback callback = this.f2315b;
        switch (i4) {
            case 0:
                ApiHelper.AnonymousClass1.lambda$onFailure$0(callback, iOException);
                break;
            case 1:
                ApiHelper.AnonymousClass2.lambda$onFailure$0(callback, iOException);
                break;
            case 2:
                ApiHelper.AnonymousClass3.lambda$onFailure$0(callback, iOException);
                break;
            case 3:
                ApiHelper.AnonymousClass4.lambda$onFailure$0(callback, iOException);
                break;
            case 4:
                ApiHelper.AnonymousClass5.lambda$onFailure$0(callback, iOException);
                break;
            case e1.CATALOGUE_NAME_FIELD_NUMBER /* 5 */:
                ApiHelper.AnonymousClass6.lambda$onFailure$0(callback, iOException);
                break;
            case 6:
                ApiHelper.AnonymousClass7.lambda$onFailure$0(callback, iOException);
                break;
            default:
                ApiHelper.AnonymousClass8.lambda$onFailure$0(callback, iOException);
                break;
        }
    }
}
