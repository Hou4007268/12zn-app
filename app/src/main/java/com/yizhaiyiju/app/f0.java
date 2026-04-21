package com.yizhaiyiju.app;

import android.view.View;

/* loaded from: classes.dex */
public final /* synthetic */ class f0 implements View.OnClickListener {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2325e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ FlyingStarActivity f2326f;

    public /* synthetic */ f0(FlyingStarActivity flyingStarActivity, int i4) {
        this.f2325e = i4;
        this.f2326f = flyingStarActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i4 = this.f2325e;
        FlyingStarActivity flyingStarActivity = this.f2326f;
        switch (i4) {
            case 0:
                flyingStarActivity.lambda$onCreate$1(view);
                break;
            default:
                flyingStarActivity.lambda$calculate$3(view);
                break;
        }
    }
}
