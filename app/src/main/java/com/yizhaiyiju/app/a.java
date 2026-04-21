package com.yizhaiyiju.app;

import android.content.Context;

/* loaded from: classes.dex */
public final /* synthetic */ class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2285a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Context f2286b;

    public /* synthetic */ a(Context context, int i4) {
        this.f2285a = i4;
        this.f2286b = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2285a;
        Context context = this.f2286b;
        switch (i4) {
            case 0:
                AnnouncementActivity.lambda$checkAndShow$1(context);
                break;
            case 1:
                UpdateHelper.lambda$doCheck$1(context);
                break;
            default:
                UpdateHelper.lambda$doCheck$2(context);
                break;
        }
    }
}
