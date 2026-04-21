package com.yizhaiyiju.app;

import com.yizhaiyiju.app.DirectChatActivity;

/* loaded from: classes.dex */
public final /* synthetic */ class z implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2407a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DirectChatActivity.AnonymousClass5 f2408b;

    public /* synthetic */ z(DirectChatActivity.AnonymousClass5 anonymousClass5, int i4) {
        this.f2407a = i4;
        this.f2408b = anonymousClass5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2407a;
        DirectChatActivity.AnonymousClass5 anonymousClass5 = this.f2408b;
        switch (i4) {
            case 0:
                anonymousClass5.lambda$onFailure$0();
                break;
            default:
                anonymousClass5.lambda$onResponse$1();
                break;
        }
    }
}
