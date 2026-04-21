package com.yizhaiyiju.app;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes.dex */
public final /* synthetic */ class e0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2317a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f2318b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ KeyEvent.Callback f2319c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f2320d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2321e;

    public /* synthetic */ e0(ImageView imageView, View view, Context context, String str) {
        this.f2319c = imageView;
        this.f2320d = view;
        this.f2321e = context;
        this.f2318b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f2317a;
        String str = this.f2318b;
        Object obj = this.f2321e;
        Object obj2 = this.f2320d;
        KeyEvent.Callback callback = this.f2319c;
        switch (i4) {
            case 0:
                ((FeedbackActivity) callback).lambda$submitFeedback$4(str, (String) obj2, (String) obj);
                break;
            default:
                ShareHelper.lambda$loadQRAndShare$2((ImageView) callback, (View) obj2, (Context) obj, str);
                break;
        }
    }

    public /* synthetic */ e0(FeedbackActivity feedbackActivity, String str, String str2, String str3) {
        this.f2319c = feedbackActivity;
        this.f2318b = str;
        this.f2320d = str2;
        this.f2321e = str3;
    }
}
