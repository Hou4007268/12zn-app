package com.yizhaiyiju.app;

import android.view.KeyEvent;
import android.widget.TextView;

/* loaded from: classes.dex */
public final /* synthetic */ class p implements TextView.OnEditorActionListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2365a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d.s f2366b;

    public /* synthetic */ p(d.s sVar, int i4) {
        this.f2365a = i4;
        this.f2366b = sVar;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i4, KeyEvent keyEvent) {
        boolean lambda$onCreate$2;
        boolean lambda$onCreate$3;
        int i5 = this.f2365a;
        d.s sVar = this.f2366b;
        switch (i5) {
            case 0:
                lambda$onCreate$3 = ((ChatActivity) sVar).lambda$onCreate$3(textView, i4, keyEvent);
                return lambda$onCreate$3;
            default:
                lambda$onCreate$2 = ((DirectChatActivity) sVar).lambda$onCreate$2(textView, i4, keyEvent);
                return lambda$onCreate$2;
        }
    }
}
