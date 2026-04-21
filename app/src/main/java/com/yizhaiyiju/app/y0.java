package com.yizhaiyiju.app;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.yizhaiyiju.app.ApiHelper;
import com.yizhaiyiju.app.TestBillingHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class y0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2403a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Context f2404b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2405c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f2406d;

    public /* synthetic */ y0(Context context, View view, String str) {
        this.f2404b = context;
        this.f2405c = view;
        this.f2406d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2403a;
        Context context = this.f2404b;
        Object obj = this.f2406d;
        Object obj2 = this.f2405c;
        switch (i4) {
            case 0:
                TestBillingHelper.AnonymousClass1.lambda$onSuccess$1((ApiHelper.PayResult) obj2, context, (ImageView) obj);
                break;
            default:
                ShareHelper.doShare(context, (View) obj2, (String) obj);
                break;
        }
    }

    public /* synthetic */ y0(ApiHelper.PayResult payResult, Context context, ImageView imageView) {
        this.f2405c = payResult;
        this.f2404b = context;
        this.f2406d = imageView;
    }
}
