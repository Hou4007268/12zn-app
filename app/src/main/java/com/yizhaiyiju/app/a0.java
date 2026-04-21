package com.yizhaiyiju.app;

import com.yizhaiyiju.app.DirectChatActivity;

/* loaded from: classes.dex */
public final /* synthetic */ class a0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2287a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DirectChatActivity.AnonymousClass6 f2288b;

    public /* synthetic */ a0(DirectChatActivity.AnonymousClass6 anonymousClass6, int i4) {
        this.f2287a = i4;
        this.f2288b = anonymousClass6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2287a;
        DirectChatActivity.AnonymousClass6 anonymousClass6 = this.f2288b;
        switch (i4) {
            case 0:
                anonymousClass6.lambda$onFailure$0();
                break;
            default:
                anonymousClass6.lambda$onResponse$1();
                break;
        }
    }
}
