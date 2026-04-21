package com.yizhaiyiju.app;

import android.view.View;

/* loaded from: classes.dex */
public final /* synthetic */ class g0 implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2330e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f2331f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ d.s f2332g;

    public /* synthetic */ g0(d.s sVar, int i4, int i5) {
        this.f2330e = i5;
        this.f2332g = sVar;
        this.f2331f = i4;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2330e;
        int i5 = this.f2331f;
        d.s sVar = this.f2332g;
        switch (i4) {
            case 0:
                ((FortuneActivity) sVar).lambda$onCreate$1(i5, view);
                break;
            case 1:
                ((GenericTestActivity) sVar).lambda$showQuestion$4(i5, view);
                break;
            default:
                ((WuxingTestActivity) sVar).lambda$showQuestion$4(i5, view);
                break;
        }
    }
}
