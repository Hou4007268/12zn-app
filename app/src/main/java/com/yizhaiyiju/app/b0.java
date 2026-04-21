package com.yizhaiyiju.app;

import com.yizhaiyiju.app.DirectChatActivity;

/* loaded from: classes.dex */
public final /* synthetic */ class b0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2294a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DirectChatActivity.AnonymousClass7 f2295b;

    public /* synthetic */ b0(DirectChatActivity.AnonymousClass7 anonymousClass7, int i4) {
        this.f2294a = i4;
        this.f2295b = anonymousClass7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2294a;
        DirectChatActivity.AnonymousClass7 anonymousClass7 = this.f2295b;
        switch (i4) {
            case 0:
                anonymousClass7.lambda$onFailure$0();
                break;
            default:
                anonymousClass7.lambda$onResponse$1();
                break;
        }
    }
}
